package kk.itaxi.Test.product.dummy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import kk.itaxi.Test.product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

class DummyProductRepositoryTest {

    private static final String PRODUCT_NAME = "PROD";
    private static final BigDecimal PRODUCT_PRICE = BigDecimal.valueOf(25.12);

    private DummyProductRepository underTest = new DummyProductRepository();

    @Test
    void shouldCreateAndDeleteProduct() {
        Product product = Product.builder()
                .name(PRODUCT_NAME)
                .price(PRODUCT_PRICE)
                .build();

        underTest.create(product);
        List<Product> result = underTest.get();

        assertEquals(1, result.size());

        UUID productId = result.get(0).getId();

        underTest.delete(productId);
        result = underTest.get();

        assertTrue(result.isEmpty());
    }

}