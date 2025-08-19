package ua.practise.service;

import java.util.List;
import ua.practise.vo.Product;

public interface ProductService {
  List<Product> getProducts();

  Product getProductsById(String id);
}
