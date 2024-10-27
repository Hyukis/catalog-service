package com.yuki.catalogservice.persist;

import com.yuki.catalogservice.domain.entity.Book;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookRepository implements BookRepository {

  private static final Map<String, Book> books = new ConcurrentHashMap<>();

  static {
    String isbn = UUID.randomUUID().toString();

    Book build = Book.builder()
        .author("yuki")
        .title("Learn about CNCF")
        .price(BigDecimal.valueOf(10000.00))
        .isbn(isbn)
        .build();

    books.put(isbn, build);
  }

  @Override
  public Iterator<Book> findAll() {
    return books.values().iterator();
  }

  @Override
  public Optional<Book> findByIsbn(String isbn) {
    return Optional.of(books.get(isbn));
  }

  @Override
  public boolean existsByIsbn(String isbn) {
    return books.get(isbn) != null;
  }

  @Override
  public Book save(Book book) {
    books.put(book.isbn(), book);
    return book;
  }

  @Override
  public void deleteByIsbn(String isbn) {
    books.remove(isbn);
  }
}
