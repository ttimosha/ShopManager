package ru.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "condition", schema = "shop")
public class Condition {
    private String condition;

    @Id
    @Column(name = "condition", nullable = false, length = 20)
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Condition condition1 = (Condition) o;

        if (condition != null ? !condition.equals(condition1.condition) : condition1.condition != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return condition != null ? condition.hashCode() : 0;
    }
}
