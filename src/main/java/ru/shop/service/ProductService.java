package ru.shop.service;

import ru.shop.model.ProductEntity;

import java.util.List;

public interface ProductService {
    public void addProduct(ProductEntity product);

    public void updateProduct(ProductEntity product);

    public void removeProduct(int id);

    public ProductEntity getProductById(int id);

    public List<ProductEntity> listProducts();
}
