package br.edu.ifpb.esperanca.ads.marketmanager.sale.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class SaleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal price;
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "sale_id")  //dúvida
    private Sale sale;

    public SaleProduct() {
    }

    public SaleProduct(Long id, Long productId, int quantity, BigDecimal price, Sale sale) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.sale = sale;
        this.total = calculateTotal();
    }

    private BigDecimal calculateTotal() {
        return total = price.multiply(BigDecimal.valueOf(quantity));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.total = calculateTotal();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        this.total = calculateTotal();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
