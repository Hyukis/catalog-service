package su.hyuki.catalog.book.domain.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {


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

    @Override
    public String toString() {
        return getClass() + "data { " + isbn + ", " + title + ", " + author + ", " + price + " }";
    }
}
