package ua.practise.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ua.practise.service.ProductService;
import ua.practise.vo.Product;

@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = ProductController.class)
class ProductControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ProductService productService;

  @Test
  void getAllProducts_shouldReturnList() throws Exception {
    List<Product> mockProducts =
        List.of(
            new Product(UUID.randomUUID(), "Iphone 13", "Mobile phone", BigDecimal.valueOf(450)),
            new Product(UUID.randomUUID(), "Iphone 14", "Mobile phone", BigDecimal.valueOf(650)));

    when(productService.getProducts()).thenReturn(mockProducts);

    mockMvc
        .perform(get("/api/products").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(2))
        .andExpect(jsonPath("$[0].id").value(mockProducts.getFirst().getId().toString()))
        .andExpect(jsonPath("$[0].name").value(mockProducts.getFirst().getName()));
  }

  @Test
  void getProductById_shouldReturnSingleProduct() throws Exception {
    var uuid = UUID.randomUUID();
    Product mockProduct = new Product(uuid, "Iphone 13", "Mobile phone", BigDecimal.valueOf(450));

    when(productService.getProductsById(String.valueOf(uuid))).thenReturn(mockProduct);

    mockMvc
        .perform(get("/api/products/{id}", uuid).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(uuid.toString()))
        .andExpect(jsonPath("$.name").value(mockProduct.getName()));
  }
}
