package su.hyuki.catalog;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import su.hyuki.catalog.book.domain.entity.Book;

@SpringBootTest(
    // 완전한 스프링 웹 어플리케이션 콘텍스트와 임의의 포트를 듣는 서블릿 컨테이너를 로드한다.
    webEnvironment = WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void whenGetRequestThenFindBook() {
        var expectBook = new Book(null, "1234567890", "love story", "hyukis", 9.90, 0, null, null);

        webTestClient
            .get()
            .uri("/books/" +expectBook.getIsbn())
            .exchange()
            .expectStatus().isOk()
            .expectBody(Book.class).value(
                actualBook -> {
                  assertThat(actualBook).isNotNull();
                  assertThat(actualBook.getIsbn())
                      .isEqualTo(expectBook.getIsbn());
                }
            );
  }
}
