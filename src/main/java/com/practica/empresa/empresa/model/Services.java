package com.practica.empresa.empresa.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private LocalDateTime createdAt;

    @Column
    private BigDecimal priceService;

    public Services(String userId, LocalDateTime createdAt, BigDecimal priceService) {
        this.userId = userId;
        this.createdAt = createdAt;
        this.priceService = priceService;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getPriceService() {
        return priceService;
    }

    public void setPriceService(BigDecimal priceService) {
        this.priceService = priceService;
    }
}
