package su.hyuki.catalog.book.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import su.hyuki.catalog.book.domain.BookService;
import su.hyuki.catalog.book.domain.entity.Book;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }

    @GetMapping("{isbn}")
    public Book getByIsbn(@Valid @PathVariable String isbn){
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping("{isbn}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody Book book) {
        log.info("{}", book.toString());
        return bookService.addBookToCatalog(book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Valid @PathVariable String isbn){
        bookService.removeBookFromCatalog(isbn);
    }

    @PutMapping("{isbn}")
    public Book put(@Valid @PathVariable String isbn, @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }
}
