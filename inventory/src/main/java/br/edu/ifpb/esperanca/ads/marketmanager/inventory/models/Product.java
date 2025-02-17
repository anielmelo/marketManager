package br.edu.ifpb.esperanca.ads.marketmanager.inventory.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private Double cost;

    @Column(name = "available_quantity", nullable = false)
    private int availableQuantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supplier_id")
    @JsonBackReference
    private Supplier supplier;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Replacement> replacements;

    public Product() { }

    public Product(Long id, String name, String brand, Double cost, int availableQuantity, Supplier supplier, List<Replacement> replacements) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.cost = cost;
        this.availableQuantity = availableQuantity;
        this.supplier = supplier;
        this.replacements = replacements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Replacement> getReplacement() {
        return replacements;
    }

    public void setReplacement(List<Replacement> replacements) {
        this.replacements = replacements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
