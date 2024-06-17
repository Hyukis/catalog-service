package su.hyuki.catalog.book.exception;

public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(String isbn) {
        super("A book with ISBN " + isbn + " already Exists");
    }
}
