package ua.practise.repo;

import org.junit.jupiter.api.Test;
import ua.practise.vo.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ProductRepoTest {

    private final ProductRepo productRepo = new ProductRepo();

    @Test
    void testFindAllReturnsAllProducts() {
        List<Product> products = productRepo.findAll();
        assertThat(products)
                .isNotNull()
                .hasSize(4);
    }

    @Test
    void testFindByIdReturnsEmptyWhenNotExists() {
        UUID randomId = UUID.randomUUID();
        Optional<Product> result = productRepo.findById(randomId);

        assertThat(result).isEmpty();
    }
}
