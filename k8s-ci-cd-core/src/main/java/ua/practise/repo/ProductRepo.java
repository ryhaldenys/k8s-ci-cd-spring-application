package ua.practise.repo;

import org.springframework.stereotype.Repository;
import ua.practise.vo.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepo {

    public List<Product> findAll() {
        return products();
    }

    public Optional<Product> findById(UUID id) {
        return products().stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    private static List<Product> products() {
        return List.of(
                new Product(UUID.randomUUID(), "Iphone 13", "Mobile phone", BigDecimal.valueOf(450)),
                new Product(UUID.randomUUID(), "Iphone 14", "Mobile phone", BigDecimal.valueOf(650)),
                new Product(UUID.randomUUID(), "Iphone 15", "Mobile phone", BigDecimal.valueOf(800)),
                new Product(UUID.randomUUID(), "Iphone 16", "Mobile phone", BigDecimal.valueOf(1120))
        );
    }
}
