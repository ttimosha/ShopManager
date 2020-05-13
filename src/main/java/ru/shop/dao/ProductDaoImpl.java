package ru.shop.dao;

import ru.shop.model.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProduct(ProductEntity product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(product);
    }

    @Override
    public void updateProduct(ProductEntity product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(product);
    }

    @Override
    public void removeProduct(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ProductEntity product = (ProductEntity) session.load(ProductEntity.class, new Integer(id));

        if(product!=null){
            session.delete(product);
        }
    }

    @Override
    public ProductEntity getProductById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        ProductEntity product = (ProductEntity) session.get(ProductEntity.class, new Integer(id));
        return product;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductEntity> listProducts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<ProductEntity> productList = session.createQuery("from ProductEntity").list();
        return productList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProductEntity> listProductsByUser(int idUser) {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "from ProductEntity p where p.seller = :sellerId";
        List<ProductEntity> productListByUser = session.createQuery(hql)
                .setParameter("sellerId", Integer.toString(idUser))
                .list();
        return productListByUser;
    }

}