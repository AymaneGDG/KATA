package exception;

public class BookNotFoundException extends Exception {

    public BookNotFoundException(String name, String reference) {
        super(String.format("The book : %s with reference : %s is out od stock now", reference, name));
    }
}
