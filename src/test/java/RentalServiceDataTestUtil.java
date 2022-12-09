import lombok.experimental.UtilityClass;
import model.Book;
import model.Catalog;
import model.Rental;
import model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class RentalServiceDataTestUtil {

    public static Rental buildRental() {
        return new Rental(buildBook(2L, "ref 2", "name 2"), buildUser(), LocalDate.now());
    }

    public static Book buildBook(Long id, String name, String ref) {
        return new Book(id, name, ref);
    }

    public static List<Book> buildBookList() {
        // Should return a mutable list
        return new ArrayList(Arrays.asList(buildBook(1L, "ref 1", "name 1"), buildBook(2L, "ref 2", "name 2" )));
    }


    public static Catalog buildCatalog() {
        return Catalog.builder()
                .books(buildBookList())
                .rentals(new ArrayList<>(Arrays.asList(buildRental())))
                .build();
    }

    public static User buildUser() {
        return User.builder()
                .id(1L)
                .address("address")
                .name("username")
                .rentedBooks(buildBookList())
                .build();
    }
}
