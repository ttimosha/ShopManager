package ru.shop.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authority", schema = "shop")
public class Authority implements GrantedAuthority {
    private int id;
    private String authority;
    public Authority() {
    }
    public Authority(String authority) { this.authority = authority; }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "authority", nullable = false, length = 20)
    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority = (Authority) o;

        if (id != authority.id) return false;
        if (authority != null ? !authority.equals(authority.authority) : authority.authority != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        return result;
    }

}
