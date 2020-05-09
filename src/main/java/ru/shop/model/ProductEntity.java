package ru.shop.model;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "shop", catalog = "")
public class ProductEntity {
    private int id;
    private String brand;
    private String typeOfProduct;
    private int price;
    private int seller;
    private String size;
    private byte sold;
    private String condition;
    private String description;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Brand", nullable = false, length = 30)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "TypeOfProduct", nullable = false, length = 30)
    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    @Basic
    @Column(name = "Price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Size", nullable = true, length = 10)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "Seller", nullable = false)
    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    @Basic
    @Column(name = "Sold", nullable = false)
    public byte getSold() {
        return sold;
    }

    public void setSold(byte sold) {
        this.sold = sold;
    }

    @Basic
    @Column(name = "Condition", nullable = true, length = 10)
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (sold != that.sold) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (typeOfProduct != null ? !typeOfProduct.equals(that.typeOfProduct) : that.typeOfProduct != null)
            return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (condition != null ? !condition.equals(that.condition) : that.condition != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (typeOfProduct != null ? typeOfProduct.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (int) sold;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
