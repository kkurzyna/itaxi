package kk.itaxi.Test.product;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@EqualsAndHashCode
public class Product {
    private final UUID id;
    private final String name;
    private final BigDecimal price;
}
