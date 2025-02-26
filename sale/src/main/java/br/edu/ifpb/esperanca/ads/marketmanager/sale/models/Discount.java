package br.edu.ifpb.esperanca.ads.marketmanager.sale.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_discount")
public class Discount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( nullable = false)
    private String code;
    @Column( nullable = false)
    private String description;
    @Column( nullable = false)
    private Double value;
    @Column( nullable = false)
    private Double minimumValue;
    private Boolean active;

    public Discount() {
    }

    public Discount(String code, String description, Double value, Double minimumValue) {
        this.code = code;
        this.description = description;
        this.value = value;
        this.minimumValue = minimumValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(Double minimumValue) {
        this.minimumValue = minimumValue;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
