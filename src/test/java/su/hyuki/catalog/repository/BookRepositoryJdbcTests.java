package su.hyuki.catalog.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;
import su.hyuki.catalog.book.config.DataConfig;
import su.hyuki.catalog.book.domain.entity.Book;
import su.hyuki.catalog.book.repository.BookRepository;
import su.hyuki.catalog.global.annotation.MethodTimer;

@DataJdbcTest
// 데이터 설정을 임포트한다.
@Import(DataConfig.class)
// 테스트 컨테이너를 이용하는 동안, 로컬 데이터베이스 사용을 비활성화한다.
@AutoConfigureTestDatabase(
    replace = Replace.NONE
)
@ActiveProfiles("integration")
public class BookRepositoryJdbcTests {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  // 데이터베이스와 상호 작용을 위한 하위 수준의 객체 (jdbc 공통 하위 모듈)
  private JdbcAggregateTemplate jdbcAggregateTemplate;

  @Test
  @MethodTimer
  void findBookByIsbnWhenExisting() {
    var bookIsbn = "1234561237";
    var book = Book.builder()
        .id(null)
        .isbn(bookIsbn)
        .title("love story")
        .author("hyukis")
        .price(12.90)
        .version(0)
        .createdDate(null)
        .lastModifiedDate(null)
        .build();

    jdbcAggregateTemplate.insert(book);
    Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

    assertThat(actualBook).isPresent();
    assertThat(actualBook.get().getIsbn()).isEqualTo(bookIsbn);
  }
}
