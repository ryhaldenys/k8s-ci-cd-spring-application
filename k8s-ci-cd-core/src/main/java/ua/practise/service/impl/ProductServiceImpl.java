package ua.practise.service.impl;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.practise.repo.ProductRepo;
import ua.practise.service.ProductService;
import ua.practise.vo.Product;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepo productRepo;

  @Override
  public List<Product> getProducts() {
    return productRepo.findAll();
  }

  @Override
  public Product getProductsById(String id) {
    return productRepo.findById(UUID.fromString(id)).orElseThrow();
  }
}
