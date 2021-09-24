package com.mwguerra.database.tables;

import com.mwguerra.database.DatabaseTable;
import com.mwguerra.models.Product;
import com.mwguerra.models.Stock;

import java.util.Map;
import java.util.Objects;

public class StockTable extends DatabaseTable<Stock> {
  public Stock findByProduct(Product product) {
    return getDatabaseTable()
      .values()
      .stream()
      .filter(item -> Objects.equals(item.getProduct().getId(), product.getId()))
      .findFirst()
      .orElse(null);
  }

  public void remove(Product product, Integer quantityToRemove) {
    Stock item = findByProduct(product);
    item.setQuantity(item.getQuantity() - quantityToRemove);

    getDatabaseTable().put(item.getId(), item);
  }
}


