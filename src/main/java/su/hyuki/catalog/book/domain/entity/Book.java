package su.hyuki.catalog.book.domain.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    @Id
    private Long id;

    @NotBlank( message = "The book ISBN must be defined." )
    @Pattern(
            regexp = "^([0-9]{10}|[0-9]{13})",
            message = "The ISBN format must be valid"
    )
    private String isbn;

    @NotBlank( message = "The book title must be defined.")
    // transient private String title;
    private String title;

    @NotBlank( message = "The book author must be defined.")
    private String author;

    @NotNull( message = "The book price must be defined.")
    @Positive( message = "The book price must be greater than zero")
    private Double price;

    @Version
    private int version;

    @CreatedDate
    Instant createdDate;

    @LastModifiedDate
    Instant lastModifiedDate;

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", isbn='" + isbn + '\'' +
            ", title='" + title + '\'' +
            ", author='" + author + '\'' +
            ", price=" + price +
            ", version=" + version +
            ", createdDate=" + createdDate +
            ", lastModifiedDate=" + lastModifiedDate +
            '}';
    }
}
