package br.edu.ifpb.esperanca.ads.marketmanager.sale.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_saleProduct")
public class SaleProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( nullable = false)
    private Long productId;
    @Column( nullable = false)
    private int quantity;
    private Double total;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    public SaleProduct() {
    }

    public SaleProduct(Long productId, int quantity, Sale sale, Double total) {
        this.productId = productId;
        this.quantity = quantity;
        this.sale = sale;
        this.total = total;
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

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }


}
