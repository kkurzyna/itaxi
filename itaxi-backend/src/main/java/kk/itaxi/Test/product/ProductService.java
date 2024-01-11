package kk.itaxi.Test.product;

import kk.itaxi.Test.product.exception.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator;

    List<Product> getProducts() throws ProductException {
        return productRepository.get();
    }

    Product getProduct(String productId) throws ProductException {
            productValidator.validateProductId(productId);
            return productRepository.get(UUID.fromString(productId));
    }

    void deleteProduct(String productId) throws ProductException {
        productValidator.validateProductId(productId);
        productRepository.delete(UUID.fromString(productId));
    }

    void createProduct(Product product) throws ProductException {
        productValidator.validateProduct(product);
        productRepository.create(product);
    }
}
