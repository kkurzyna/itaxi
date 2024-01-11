package kk.itaxi.Test.product.dummy;

import com.google.common.collect.Lists;
import kk.itaxi.Test.product.Product;
import kk.itaxi.Test.product.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class DummyProductRepository  implements ProductRepository {

    private Map<UUID, Product> dummyDb = new HashMap<>();

    @Override
    public List<Product> get() {
        return Lists.newLinkedList(dummyDb.values());
    }

    @Override
    public Product get(UUID id) {
        return dummyDb.get(id);
    }

    @Override
    public void delete(UUID id) {
        dummyDb.remove(id);
    }

    @Override
    public void create(Product product) {
        Product entity = Product.builder()
                .id(UUID.randomUUID())
                .price(product.getPrice())
                .name(product.getName())
                .build();
        dummyDb.put(entity.getId(), entity);
    }
}
