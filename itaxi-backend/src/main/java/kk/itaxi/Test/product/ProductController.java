package kk.itaxi.Test.product;

import kk.itaxi.Test.product.exception.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> get() throws ProductException {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> get(@PathVariable String productId) throws ProductException {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PostMapping(value = "/products", headers = "content-type=application/json")
    public ResponseEntity<Void> create(@RequestBody Product product) throws ProductException {
        productService.createProduct(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<Void> delete(@PathVariable String productId) throws ProductException {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

}
