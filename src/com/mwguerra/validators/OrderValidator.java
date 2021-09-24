package com.mwguerra.validators;

import com.mwguerra.models.Order;

import java.util.Calendar;

public class OrderValidator implements IValidator {
  public OrderValidator validate(Order order) {
    if (order.getQuantity() > order.getStockForProduct().getQuantity()) {
      errors.add("Quantidade indisponível no estoque");
    }

    if (Calendar.getInstance().getTime().after(order.getProduct().getExpirationDate())) {
      errors.add("Produto fora do prazo de validade");
    }

    return this;
  }
}
