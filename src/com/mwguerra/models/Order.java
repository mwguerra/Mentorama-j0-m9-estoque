package com.mwguerra.models;

public class Order extends Model {
  private UserCustomer customer;
  private Product product;
  private Integer quantity;
  private Stock stock;

  public Order(UserCustomer customer, Product product, Integer quantity) {
    this.customer = customer;
    this.product = product;
    this.quantity = quantity;
  }

  public UserCustomer getCustomer() {
    return customer;
  }

  public void setCustomer(UserCustomer customer) {
    this.customer = customer;
  }

  public Double getTotalPrice() {
    return quantity * product.getPrice();
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setStockForProduct(Stock stock) {
    this.stock = stock;
  }

  public Stock getStockForProduct() {
    return stock;
  }
}