package com.infodev.ecommerceproject.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, unique = true)
    private String productId;
    @Column(nullable = false)
    @NotBlank(message = "Product Name cannot be blank")
    private String productName;
    @Column(nullable = false)
    @NotBlank(message = "Category Name cannot be blank")
    private String category;
    @Column(nullable = false)
    @NotBlank(message = "Product Stock cannot be blank")
    private int stock;
    @Column(nullable = false)
    @NotBlank(message = "Product Price cannot be blank")
    private double price;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductOrder> productOrder;

    public Product() {
    }

    public Product(int id, String productId, String productName, String category, int stock, double price, Set<ProductOrder> productOrder) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.productOrder = productOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Set<ProductOrder> getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(Set<ProductOrder> productOrder) {
        this.productOrder = productOrder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
