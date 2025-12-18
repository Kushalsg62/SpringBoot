package com.StockSync.sourav.StockSync.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(columnDefinition = "text")
    private String name;

    @NotBlank(message = "Sku is required")
    @Column(unique = true, columnDefinition = "text")
    private String sku;

    @Positive(message = "Product price must be a positive value")
    private BigDecimal price;

    @Min(value = 0, message = "Stock quantity cannot be less than zero")
    private Integer stockQuantity;

    @Column(columnDefinition = "text")
    private String description;

    private LocalDateTime expiryDate;

    private LocalDateTime updatedAt;

    private final LocalDateTime createdAt = LocalDateTime.now();

    private String imageName;

    private String imageType;

    private String upperLimit;

    private String lowerLimit;

    @Lob
    @Column(name = "image")
    @Basic(fetch = FetchType.EAGER)
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", description='" + description + '\'' +
                ", expiryDate=" + expiryDate +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", category=" + category +
                ", imageName='" + imageName + '\'' +
                ", imageType='" + imageType + '\'' +
                ", imageData=" + (imageData != null ? imageData.length : 0) + " bytes" +
                '}';
    }
}