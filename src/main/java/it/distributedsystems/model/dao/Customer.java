package it.distributedsystems.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 318274891481L;

    protected int id;
    protected String name;
    protected Set<Purchase> purchases;

    public Customer() {}

    public Customer(String name) { this.name = name; }

    public Customer(String name, Set<Purchase> purchases) {
        this.name = name;
        this.purchases = purchases;
    }

    @Column(unique = true)
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(
            cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "customer"
    )
    public Set<Purchase> getPurchases() { return purchases; }

    public void setPurchases(Set<Purchase> purchases) { this.purchases = purchases; }

}