package su.hyuki.catalog.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspect")
class BookJsonTests {

  @Autowired
  private JacksonTester<Book> json;


  @Test
  void testSerialize() throws Exception {
    var book = new Book("1234567890", "love story", "hyukis", 9.90);
    var jsonContent = json.write(book);

    assertThat(jsonContent).extractingJsonPathValue("@.isbn")
        .isEqualTo(book.getIsbn());
  }
}