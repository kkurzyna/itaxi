package kk.itaxi.Test.product;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import kk.itaxi.Test.product.exception.ProductException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

class ProductValidatorTest {

    private static final String INVALID_PRODUCT_ID = "incorrect ID";
    private static final String VALID_PRODUCT_ID = UUID.randomUUID().toString();
    private static final String PRODUCT_NAME = "test product";
    private static final BigDecimal PRODUCT_PRICE = BigDecimal.valueOf(10.99);

    private ProductValidator underTest = new ProductValidator();

    @Test
    void shouldThrowOnInvalidProductId() {
        assertThrows(ProductException.class, () -> {
           underTest.validateProductId(INVALID_PRODUCT_ID);
        });
    }

    @Test
    void shouldNotThrowOnValidProductId() {
        assertDoesNotThrow(() -> {
            underTest.validateProductId(VALID_PRODUCT_ID);
        });
    }

    @Test
    void shouldThrowOnInvalidProductName() {
        assertThrows(ProductException.class, () -> {
            underTest.validateProduct(Product.builder().price(PRODUCT_PRICE).build());
        });
        assertThrows(ProductException.class, () -> {
            underTest.validateProduct(Product.builder().name("").price(PRODUCT_PRICE).build());
        });
    }

    @Test
    void shouldThrowOnInvalidProductPrice() {
        assertThrows(ProductException.class, () -> {
            underTest.validateProduct(Product.builder().name(PRODUCT_NAME).price(BigDecimal.ZERO).build());
        });
        assertThrows(ProductException.class, () -> {
            underTest.validateProduct(Product.builder().name(PRODUCT_NAME).price(BigDecimal.valueOf(-1)).build());
        });
    }

    @Test
    void shouldNotThrowOnValidProduct() {
        assertDoesNotThrow(() -> {
            underTest.validateProduct(Product.builder().name(PRODUCT_NAME).price(PRODUCT_PRICE).build());
        });
    }
}