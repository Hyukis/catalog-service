package su.hyuki.catalog.book.loader;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import su.hyuki.catalog.book.domain.entity.Book;
import su.hyuki.catalog.book.repository.BookRepository;

@Component
@Profile("testdata")
@RequiredArgsConstructor
public class BookDataLoader {

    private final BookRepository bookRepository;

    @EventListener(ApplicationEvent.class)
    public void loadBookTestData() {
        var book1 = new Book("1234567890", "Northern Lights", "Lyra Silverstar", 9.90);
        var book2 = new Book("1234567891", "Polar Journey", "Iorek Polarson", 12.90);
        bookRepository.save(book1);
        bookRepository.save(book2);

    }
}
