package com.mwguerra.models;

import java.util.Date;

public class Product extends Model {
  private String description;
  private Double price;
  private Date expirationDate;

  public Product(String description, Double price, Date expirationDate) {
    this.description = description;
    this.price = price;
    this.expirationDate = expirationDate;
  }

  public String getDescription() {
    return description;
  }

  public Double getPrice() {
    return price;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }
}