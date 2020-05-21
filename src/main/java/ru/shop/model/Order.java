package ru.shop.model;

import javax.persistence.*;

@Entity
@Table(name = "order", schema = "shop")
public class Order {
    private int id;
    private int productId;
    private String street;
    private int house;
    private int apartment;
    private String city;
    private Byte paid;
    private String email;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "street", nullable = false, length = 20)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "house", nullable = false)
    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    @Basic
    @Column(name = "apartment", nullable = false)
    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "paid", nullable = true)
    public Byte getPaid() {
        return paid;
    }

    public void setPaid(Byte paid) {
        this.paid = paid;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (productId != order.productId) return false;
        if (house != order.house) return false;
        if (apartment != order.apartment) return false;
        if (street != null ? !street.equals(order.street) : order.street != null) return false;
        if (city != null ? !city.equals(order.city) : order.city != null) return false;
        if (paid != null ? !paid.equals(order.paid) : order.paid != null) return false;
        if (email != null ? !email.equals(order.email) : order.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + house;
        result = 31 * result + apartment;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (paid != null ? paid.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
