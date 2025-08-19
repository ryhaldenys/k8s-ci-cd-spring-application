package ua.practise.service;

import ua.practise.vo.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductsById(String id);
}
