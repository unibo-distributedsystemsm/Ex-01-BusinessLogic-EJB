package it.distributedsystems.model.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Producer implements Serializable {

    private static final long serialVersionUID = 121212212121212121L;

    protected int id;
    protected String name;
    protected Set<Product> products;

    public Producer() {}

    public Producer(String name) { this.name = name; }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(
            cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            fetch=FetchType.LAZY,
            mappedBy = "producer"
    )
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
