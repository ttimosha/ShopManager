package ru.shop.service;

import ru.shop.model.Product;
import ru.shop.model.ProductSearchCriteria;

import java.util.List;

public interface ProductService {
    public void addProduct(Product product);

    public void updateProduct(Product product);

    public void removeProduct(int id);

    public Product getProductById(int id);

    public List<Product> listProducts();

    public List<Product> listProductsByUser(int idUser);

    public List<Product> findByCriteria(ProductSearchCriteria productSearchCriteria);
}
