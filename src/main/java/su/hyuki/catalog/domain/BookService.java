package su.hyuki.catalog.domain;

import org.springframework.stereotype.Service;
import su.hyuki.catalog.domain.entity.Book;
import su.hyuki.catalog.exception.BookAlreadyExistsException;
import su.hyuki.catalog.exception.BookNotFoundException;
import su.hyuki.catalog.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistsException(book.getIsbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {

        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    var bookToUpdate = Book.builder()
                            .isbn(existingBook.getIsbn())
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .price(book.getPrice())
                            .build();

                    return bookRepository.save(bookToUpdate);
                })
                .orElseGet(() -> addBookToCatalog(book));
    }
}
