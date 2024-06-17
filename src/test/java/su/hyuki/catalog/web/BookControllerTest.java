package su.hyuki.catalog.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import su.hyuki.catalog.book.domain.BookService;
import su.hyuki.catalog.book.domain.entity.Book;
import su.hyuki.catalog.book.exception.BookNotFoundException;
import su.hyuki.catalog.book.web.BookController;

@WebMvcTest(BookController.class)
class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;

  @Test
  void whenGetBookNotExistingThenShouldReturn404() throws Exception {
    String isbn = "1234567890";

    given(bookService.viewBookDetails(isbn))
        .willThrow(BookNotFoundException.class);
    mockMvc.perform(get("/books/" + isbn))
        .andExpect(status().isNotFound());
  }

  @Test
  void whenSaveBookThenShouldReturnBookEntity() throws Exception {
    var expectedBook = new Book("1234567890", "Love story", "hyukis", 9.90);
    String isbn = "1234567890";
    String content = new ObjectMapper().writeValueAsString(expectedBook);

    given(bookService.viewBookDetails(isbn))
        .willReturn(expectedBook);

    mockMvc.perform(post("/books/" + isbn)
            .contentType(MediaType.APPLICATION_JSON)
            .content(content))
        .andDo(print())
        .andExpect(status().isCreated());
  }
}