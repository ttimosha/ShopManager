package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.model.ProductEntity;
import ru.shop.dao.ProductDao;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public void addProduct(ProductEntity product) {
        this.productDao.addProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(ProductEntity product) {
        this.productDao.updateProduct(product);
    }

    @Override
    @Transactional
    public void removeProduct(int id) {
        this.productDao.removeProduct(id);
    }

    @Override
    @Transactional
    public ProductEntity getProductById(int id) {
        return this.productDao.getProductById(id);
    }

    @Override
    @Transactional
    public List<ProductEntity> listProducts() {
        return this.productDao.listProducts();
    }

    @Override
    @Transactional
    public List<ProductEntity> listProductsByUser(int idUser) {
        return this.productDao.listProductsByUser(idUser);
    }
}
