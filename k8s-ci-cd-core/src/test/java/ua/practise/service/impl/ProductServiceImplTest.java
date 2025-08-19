package ua.practise.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.practise.repo.ProductRepo;
import ua.practise.vo.Product;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @Mock private ProductRepo productRepo;

  @InjectMocks private ProductServiceImpl productService;

  @Test
  void getProducts_shouldReturnAllProducts() {
    List<Product> mockProducts =
        List.of(
            new Product(UUID.randomUUID(), "Test Product 1", "Test Description 1", BigDecimal.TEN),
            new Product(UUID.randomUUID(), "Test Product 2", "Test Description 2", BigDecimal.TWO));

    when(productRepo.findAll()).thenReturn(mockProducts);

    List<Product> result = productService.getProducts();

    assertEquals(2, result.size());
    assertEquals("Test Product 1", result.getFirst().getName());
    verify(productRepo, times(1)).findAll();
  }

  @Test
  void getProductsById_shouldReturnProduct_whenFound() {
    UUID id = UUID.randomUUID();
    Product mockProduct = new Product(id, "Test Product", "Test Description", BigDecimal.TEN);

    when(productRepo.findById(id)).thenReturn(Optional.of(mockProduct));

    Product result = productService.getProductsById(id.toString());

    assertNotNull(result);
    assertEquals("Test Product", result.getName());
    verify(productRepo, times(1)).findById(id);
  }

  @Test
  void getProductsById_shouldThrow_whenNotFound() {
    UUID id = UUID.randomUUID();

    when(productRepo.findById(id)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> productService.getProductsById(id.toString()));
    verify(productRepo, times(1)).findById(id);
  }
}
