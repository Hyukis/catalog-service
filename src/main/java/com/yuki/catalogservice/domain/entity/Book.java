package com.yuki.catalogservice.domain.entity;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record Book(
    String isbn,
    String title,
    String author,
    BigDecimal price
) {

}
