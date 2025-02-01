package br.edu.ifpb.esperanca.ads.marketmanager.inventory.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private Double cost;

    @Column(name = "available_quantity", nullable = false)
    private int availableQuantity;

    @Column(name = "total_quantity", nullable = false)
    private int totalQuantity;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "receiving_id")
    private Receiving receiving;

    public Product() { }

    public Product(Long id, String name, String brand, Double cost, int availableQuantity, int totalQuantity, Supplier supplier, Receiving receiving) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.cost = cost;
        this.availableQuantity = availableQuantity;
        this.totalQuantity = totalQuantity;
        this.supplier = supplier;
        this.receiving = receiving;
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

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Supplier getFornecedor() {
        return supplier;
    }

    public void setFornecedor(Supplier supplier) {
        this.supplier = supplier;
    }

    public Receiving getRecebimento() {
        return receiving;
    }

    public void setRecebimento(Receiving receiving) {
        this.receiving = receiving;
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
