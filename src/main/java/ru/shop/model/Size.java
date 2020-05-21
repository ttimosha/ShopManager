package ru.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "size", schema = "shop")
public class Size {
    private String size;

    @Id
    @Column(name = "size", nullable = false, length = 5)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Size size1 = (Size) o;

        if (size != null ? !size.equals(size1.size) : size1.size != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return size != null ? size.hashCode() : 0;
    }
}
