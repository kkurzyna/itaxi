package kk.itaxi.Test.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

import kk.itaxi.Test.product.exception.ProductException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private final BigDecimal PRODUCT_PRICE = BigDecimal.valueOf(10.99);
    private final String PRODUCT_NAME = "prod";
    private final UUID PRODUCT_ID = UUID.randomUUID();
    private final UUID PRODUCT_ID_2 = UUID.randomUUID();
    private final String INCORRECT_PRODUCT_ID = "incoorect ID";

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductValidator productValidator;
    @InjectMocks
    private ProductService underTest;

    @Test
    void shouldGetProducts() throws Exception {
        var product = Product.builder()
                .id(PRODUCT_ID)
                .name(PRODUCT_NAME)
                .price(PRODUCT_PRICE)
                .build();
        var product2 = Product.builder()
                .id(PRODUCT_ID_2)
                .name(PRODUCT_NAME + 2)
                .price(PRODUCT_PRICE)
                .build();
        var products = List.of(product, product2);

        given(productRepository.get()).willReturn(products);

        var result = underTest.getProducts();

        assertEquals(products, result);
    }

    @Test
    void shouldGetProductById() throws Exception {
        Product product = Product.builder()
                .id(PRODUCT_ID)
                .name(PRODUCT_NAME)
                .price(PRODUCT_PRICE)
                .build();

        given(productRepository.get(PRODUCT_ID)).willReturn(product);

        var result = underTest.getProduct(PRODUCT_ID.toString());

        assertEquals(product, result);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        Product product = Product.builder()
                .name(PRODUCT_NAME)
                .price(PRODUCT_PRICE)
                .build();

        underTest.createProduct(product);

        then(productRepository).should().create(product);
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        underTest.deleteProduct(PRODUCT_ID.toString());

        then(productRepository).should().delete(PRODUCT_ID);
    }


    @Test
    void shouldThrowExceptionForIncorrectProductIdWhenDelete() throws Exception {
        willThrow(ProductException.class).given(productValidator).validateProductId(INCORRECT_PRODUCT_ID);

        assertThrows(ProductException.class, () -> {
            underTest.deleteProduct(INCORRECT_PRODUCT_ID);
        });
    }

    @Test
    void shouldThrowExceptionForIncorrectProductIdWhenGetById() throws Exception {
        willThrow(ProductException.class).given(productValidator).validateProductId(INCORRECT_PRODUCT_ID);

        assertThrows(ProductException.class, () -> {
            underTest.getProduct(INCORRECT_PRODUCT_ID);
        });
    }

    @Test
    void shouldThrowExceptionForIncorrectProductWhenCreateProduct() throws Exception {
        var incorrectProduct = Product.builder().build();

        willThrow(ProductException.class).given(productValidator).validateProduct(incorrectProduct);

        assertThrows(ProductException.class, () -> {
            underTest.createProduct(incorrectProduct);
        });
    }
}