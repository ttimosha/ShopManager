package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.model.Product;
import ru.shop.dao.ProductDao;
import ru.shop.model.ProductSearchCriteria;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        this.productDao.addProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        this.productDao.updateProduct(product);
    }

    @Override
    @Transactional
    public void removeProduct(int id) {
        this.productDao.removeProduct(id);
    }

    @Override
    @Transactional
    public Product getProductById(int id) {
        return this.productDao.getProductById(id);
    }

    @Override
    @Transactional
    public List<Product> listProducts() {
        return this.productDao.listProducts();
    }

    @Override
    @Transactional
    public List<Product> listProductsByUser(int idUser) {
        return this.productDao.listProductsByUser(idUser);
    }

    @Override
    @Transactional
    public List<Product> findByCriteria(ProductSearchCriteria productSearchCriteria) {
        return this.productDao.findByCriteria(productSearchCriteria);
    }
}
