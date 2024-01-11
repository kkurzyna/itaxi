package kk.itaxi.Test.product;

import kk.itaxi.Test.product.exception.ProductException;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {

    List<Product> get() throws ProductException;

    Product get(UUID id) throws ProductException;

    void delete(UUID id) throws ProductException;

    void create(Product product) throws ProductException;
}
