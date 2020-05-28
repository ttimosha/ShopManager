package ru.shop.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import ru.shop.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.shop.model.ProductSearchCriteria;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(product);
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(product);
    }

    @Override
    public void removeProduct(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = (Product) session.load(Product.class, id);

        if(product!=null){
            session.delete(product);
        }
    }

    @Override
    public Product getProductById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        try {
            Product product = (Product) session.get(Product.class, id);
            return product;
        } catch (Exception e) { }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> listProducts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Product> productList = session.createQuery("from Product").list();
        return productList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> listProductsByUser(int idUser) {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "from Product p where p.seller = :sellerId";
        List<Product> productListByUser = session.createQuery(hql)
                .setParameter("sellerId", idUser)
                .list();
        return productListByUser;
    }

    @Override
    public List<Product> findByCriteria(ProductSearchCriteria productSearchCriteria) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Product.class);
        if (productSearchCriteria.getTypeOfProduct() != null && !productSearchCriteria.getTypeOfProduct().equals("")) {
            criteria.add(Restrictions.ilike("typeOfProduct", productSearchCriteria.getTypeOfProduct(), MatchMode.ANYWHERE));
        }
        if (productSearchCriteria.getBrand() != null && !productSearchCriteria.getBrand().equals("")) {
            criteria.add(Restrictions.ilike("brand", productSearchCriteria.getBrand(), MatchMode.ANYWHERE));
        }
        if (productSearchCriteria.getSize() != null && !productSearchCriteria.getSize().equals("")) {
            criteria.add(Restrictions.ilike("size", productSearchCriteria.getSize(), MatchMode.EXACT));
        }
        if (productSearchCriteria.getCondition()!= null && !productSearchCriteria.getCondition().equals("")){
            criteria.add(Restrictions.ilike("condition", productSearchCriteria.getCondition(), MatchMode.ANYWHERE));
        }
        if (productSearchCriteria.getPriceLess() != 0) {
            criteria.add(Restrictions.le("price", productSearchCriteria.getPriceLess()));
        }
        if (productSearchCriteria.getPriceMore() != 0){
            criteria.add(Restrictions.ge("price", productSearchCriteria.getPriceMore()));
        }
        List<Product> productList = criteria.list();
        return productList;
    }

}