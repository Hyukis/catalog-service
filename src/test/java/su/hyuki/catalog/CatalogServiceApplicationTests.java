package su.hyuki.catalog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import su.hyuki.catalog.book.domain.entity.Book;

@SpringBootTest(
    // 완전한 스프링 웹 어플리케이션 콘텍스트와 임의의 포트를 듣는 서블릿 컨테이너를 로드한다.
    webEnvironment = WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {

  @Autowired
  private WebTestClient webTestClient;

  private static Book expectedBook = null;

  @BeforeAll
  public static void getExpectBook() {
    expectedBook = new Book(null, "1234567890", "love story", "hyukis", 9.90, 0, null, null);
  }

  @Test
  void whenPostRequestThenBookCreated() {
    webTestClient
        .post()
        .uri("/books/" + expectedBook.getIsbn())
        .bodyValue(expectedBook)
        .exchange()
        .expectStatus().isCreated()
        .expectBody(Book.class).value(
            actualBook -> {
              assertThat(actualBook).isNotNull();
              assertThat(actualBook.getIsbn())
                  .isEqualTo(expectedBook.getIsbn());
            }
        );
  }

  @Test
  void whenGetRequestThenFindBook() {
    // then
    webTestClient
        .get()
        .uri("/books/" + expectedBook.getIsbn())
        .exchange()
        .expectStatus().isOk()
        .expectBody(Book.class).value(
            actualBook -> {
              assertThat(actualBook).isNotNull();
              assertThat(actualBook.getIsbn())
                  .isEqualTo(expectedBook.getIsbn());
            }
        );
  }
}
