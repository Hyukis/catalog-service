package su.hyuki.catalog.book.loader;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import su.hyuki.catalog.book.domain.entity.Book;
import su.hyuki.catalog.book.repository.BookRepository;
import su.hyuki.catalog.global.annotation.MethodTimer;

@Component
@Profile("testdata")
@Slf4j
@RequiredArgsConstructor
public class BookDataLoader {

  private final BookRepository bookRepository;

  @MethodTimer
  @EventListener(ApplicationReadyEvent.class)
  public void loadBookTestData() {
    bookRepository.deleteAll();
    var book1 = new Book(null, "1234567890", "love story", "hyukis", 9.90, 0, null,null);
    var book2 = new Book(null, "1234567891", "Polar Journey", "Iorek Polarson", 12.90, 0, null, null);

    log.info("{}", book1);
    log.info("{}", book2);
    bookRepository.saveAll(List.of(book1, book2));

  }
}
