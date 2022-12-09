package services;

import exception.BookNotFoundException;
import model.Book;
import model.Rental;
import model.User;


public interface RentalService {

    Rental rentBook(Book book, User user) throws BookNotFoundException;

    void putBackBook(Rental rental);

    String displayBooksByUser(User user);
}
