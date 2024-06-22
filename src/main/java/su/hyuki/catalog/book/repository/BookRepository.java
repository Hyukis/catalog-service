package su.hyuki.catalog.book.repository;

import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import su.hyuki.catalog.book.domain.entity.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    @Modifying
    @Query("DELETE FROM BOOK WHERE isbn = : isbn")
    @Transactional
    void deleteByIsbn(String isbn);
}
