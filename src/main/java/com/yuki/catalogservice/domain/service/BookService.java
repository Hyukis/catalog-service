package com.yuki.catalogservice.domain.service;

import com.yuki.catalogservice.domain.entity.Book;
import com.yuki.catalogservice.exception.BookAlreadyExistsException;
import com.yuki.catalogservice.exception.BookNotFoundException;
import com.yuki.catalogservice.persist.BookRepository;
import java.util.Iterator;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Iterator<Book> viewBookList() {
    return bookRepository.findAll();
  }

  public Book viewBookDetails(String isbn) {
    return bookRepository.findByIsbn(isbn)
        .orElseThrow(() -> new BookNotFoundException(isbn));
  }

  public Book addBookDetails(Book book) {
    if (bookRepository.existsByIsbn(book.isbn())) {
      throw new BookAlreadyExistsException(book.isbn());
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
              .isbn(existingBook.isbn())
              .title(book.title())
              .author(book.author())
              .price(book.price())
              .build();

          return bookRepository.save(bookToUpdate);
        })
        .orElseGet(() -> addBookDetails(book));
  }
}
