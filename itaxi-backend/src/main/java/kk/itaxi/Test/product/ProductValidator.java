package kk.itaxi.Test.product;

import kk.itaxi.Test.product.exception.ProductException;
import kk.itaxi.Test.product.exception.ProductExceptionErrorType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ProductValidator {

    void validateProductId(String productId) throws ProductException {
        try {
            UUID.fromString(productId);
        } catch (IllegalArgumentException ex) {
            throw ProductException.builder()
                    .details(String.format("Invalid product id %s", productId))
                    .errorType(ProductExceptionErrorType.VALIDATION)
                    .build();
        }
    }

    void validateProduct(Product product) throws ProductException {
        validateName(product.getName());
        validatePrice(product.getPrice());
    }

    private void validateName(String name) throws ProductException {
        if (name == null || name.isEmpty() ) {
            throw ProductException.builder()
                    .details("Invalid product name - name cannot be empty!")
                    .errorType(ProductExceptionErrorType.VALIDATION)
                    .build();
        }
    }

    private void validatePrice(BigDecimal price) throws ProductException {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw ProductException.builder()
                    .details(String.format("Invalid product price %s", price))
                    .errorType(ProductExceptionErrorType.VALIDATION)
                    .build();
        }
    }

}
