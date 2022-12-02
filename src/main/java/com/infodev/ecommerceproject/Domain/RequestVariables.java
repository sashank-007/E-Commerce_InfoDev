package com.infodev.ecommerceproject.Domain;

public class RequestVariables {

    User user;
    Product product;
    ProductOrder order;
    String action;
    Boolean useDefaultAddress;
    String deliveryAddress;

    public RequestVariables(User user, Product product, ProductOrder order, String action, Boolean useDefaultAddress, String deliveryAddress) {
        this.user = user;
        this.product = product;
        this.order = order;
        this.action = action;
        this.useDefaultAddress = useDefaultAddress;
        this.deliveryAddress = deliveryAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ProductOrder getOrder() {
        return order;
    }

    public Boolean getUseDefaultAddress() {
        return useDefaultAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
}
