import exception.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicesimpl.RentalServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

class RentalServiceTest {

    private RentalServiceImpl rentalServiceImpl;

    @BeforeEach
    void setUp() {
        rentalServiceImpl = new RentalServiceImpl(RentalServiceDataTestUtil.buildCatalog(), RentalServiceDataTestUtil.buildUser());
    }

    @Test
    void user_should_rent_book() throws Exception {
       // When
        rentalServiceImpl.rentBook(RentalServiceDataTestUtil.buildBook(1L, "ref 1", "name 1"),
                RentalServiceDataTestUtil.buildUser());

        // Then
        assertEquals(rentalServiceImpl.getCatalog().getBooks().size(), 1);
    }

    @Test
    void user_should_return_book() throws Exception {
        // When
        rentalServiceImpl.putBackBook(RentalServiceDataTestUtil.buildRental());

        // Then
        // 3 because we instantiate the user service after each test
        assertEquals(rentalServiceImpl.getCatalog().getBooks().size(), 3);
        assertEquals(rentalServiceImpl.getCatalog().getRentals().size(), 0);
    }

    @Test
    void should_throw_book_not_found_exception() {
        // When
        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            rentalServiceImpl.rentBook(RentalServiceDataTestUtil.buildBook(5L, "Book does not exist", "name 5"),
                    RentalServiceDataTestUtil.buildUser());
        });

        String expectedMessage = "The book : Book does not exist with reference : name 5 is out od stock now";
        String actualMessage = exception.getMessage();

        // Then
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_print_rented_books_list() throws Exception {
        // When
        rentalServiceImpl.rentBook(RentalServiceDataTestUtil.buildBook(1L, "ref 1", "name 1"),
                RentalServiceDataTestUtil.buildUser());

        // Then
       String res = rentalServiceImpl.displayBooksByUser(RentalServiceDataTestUtil.buildUser());
       String expectedResult = "\n" +
               "Book name : name 1\t book ref : ref 1\n" +
               "Book name : name 2\t book ref : ref 2";

        // Then
        assertEquals(res, expectedResult);
    }

}
