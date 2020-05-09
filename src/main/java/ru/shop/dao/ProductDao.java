package ru.shop.dao;

import ru.shop.model.ProductEntity;

import java.util.List;

public interface ProductDao {
    public void addProduct(ProductEntity product);

    public void updateProduct(ProductEntity product);

    public void removeProduct(int id);

    public ProductEntity getProductById(int id);

    public List<ProductEntity> listProducts();
}
