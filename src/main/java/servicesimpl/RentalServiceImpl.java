package servicesimpl;


import exception.BookNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Book;
import model.Catalog;
import model.Rental;
import model.User;
import services.RentalService;

import java.time.LocalDate;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentalServiceImpl implements RentalService {

    private static final Logger logger = LogManager.getLogManager().getLogger(String.valueOf(RentalServiceImpl.class));

    Catalog catalog;
    User user;

    /*
     This method should be trade safe in case several users are calling it in the same time
     */
    @Override
    public synchronized Rental rentBook(Book book, User user) throws BookNotFoundException {
        if (user == null || book == null || catalog == null || catalog.getBooks().isEmpty()) {
            logger.warning("Invalid book renting");
            return null;
        }
        var isBookExist = catalog.getBooks().contains(book);
        if (isBookExist) {
            return setNewRental(book, user);
        } else {
            throw new BookNotFoundException(book.getName(), book.getReference());
        }
    }

    @Override
    public void putBackBook(Rental rental) {
        if (rental == null || rental.getBook() == null || rental.getUser() == null) {
            logger.warning("Invalid rental");
            return;
        }
        catalog.getBooks().add(rental.getBook());
        catalog.getRentals().remove(rental);
        rental.getUser().getRentedBooks().remove(rental.getBook());
    }

    @Override
    public String displayBooksByUser(User user) {
        StringBuilder res = new StringBuilder();
        if (user == null) {
            logger.warning("Invalid user");
            return null;
        }
        for(Book book : user.getRentedBooks()) {
            res.append("\n").append("Book name : ").append(book.getName()).append("\t book ref : ").append(book.getReference());
            System.out.println("Book name : " + book.getName() +  "\t book ref : " + book.getReference());
        }
        return res.toString();
    }

    private Rental setNewRental(Book book, User user) {
        Rental rental = new Rental();
        rental.setRentalDate(LocalDate.now());
        rental.setBook(book);
        rental.setUser(user);
        catalog.getBooks().remove(book);
        catalog.getRentals().add(rental);
        user.getRentedBooks().add(book);
        return rental;
    }
}
