package br.edu.ifpb.esperanca.ads.marketmanager.sale.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_sale")
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private Double total;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL) // tirar dúvida
    private List<SaleProduct> products = new ArrayList<>();

    @ManyToOne // Muitas vendas podem ter o mesmo desconto
    @JoinColumn(name = "discount_id") // Cria uma chave estrangeira no banco
    private Discount discount;

    public Sale() {
    }

    public Sale(List<SaleProduct> products) {
        this.date = LocalDateTime.now();;
        this.products = products;
        this.total = calcularTotal();
        this.discount = new Discount();
    }

    public Double calcularTotal(){
        return products.stream()
                .mapToDouble(SaleProduct::getTotal) // Pega o total de cada produto
                .sum();
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
        this.total = calcularTotal();
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
