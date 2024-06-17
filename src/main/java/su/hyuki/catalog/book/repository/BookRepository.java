package su.hyuki.catalog.book.repository;

import su.hyuki.catalog.book.domain.entity.Book;

import java.util.Optional;

public interface BookRepository {

    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);
}
