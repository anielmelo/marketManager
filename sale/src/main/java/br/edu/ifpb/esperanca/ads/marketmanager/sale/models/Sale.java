package br.edu.ifpb.esperanca.ads.marketmanager.sale.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL) // tirar dúvida
    private List<SaleProduct> products;


    public Sale() {
    }

    public Sale(Long id, LocalDateTime date, List<SaleProduct> products) {
        this.id = id;
        this.date = date;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<SaleProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SaleProduct> products) {
        this.products = products;
    }
}
