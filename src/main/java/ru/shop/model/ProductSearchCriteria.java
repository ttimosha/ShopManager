package ru.shop.model;

public class ProductSearchCriteria {
    private String brand;
    private String typeOfProduct;
    private int priceLess;
    private int priceMore;
    private String size;
    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public int getPriceLess() {
        return priceLess;
    }

    public void setPriceLess(int priceLess) {
        this.priceLess = priceLess;
    }

    public int getPriceMore() {
        return priceMore;
    }

    public void setPriceMore(int priceMore) {
        this.priceMore = priceMore;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
