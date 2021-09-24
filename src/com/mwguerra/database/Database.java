package com.mwguerra.database;

import com.mwguerra.database.tables.OrdersTable;
import com.mwguerra.database.tables.ProductsTable;
import com.mwguerra.database.tables.StockTable;
import com.mwguerra.database.tables.UsersTable;
import com.mwguerra.models.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Database {
  public OrdersTable orders = new OrdersTable();
  public ProductsTable products = new ProductsTable();
  public StockTable stock = new StockTable();
  public UsersTable users = new UsersTable();

  public void seeder() {
    UserAdmin admin = (UserAdmin) users.create(new UserAdmin("Marcelo Admin"));
    UserCustomer customer1 = (UserCustomer) users.create(new UserCustomer("Cliente 1"));
    UserCustomer customer2 = (UserCustomer) users.create(new UserCustomer("Cliente 2"));
    UserCustomer customer3 = (UserCustomer) users.create(new UserCustomer("Cliente 3"));
    UserCustomer customer4 = (UserCustomer) users.create(new UserCustomer("Cliente 4"));
    UserCustomer customer5 = (UserCustomer) users.create(new UserCustomer("Cliente 5"));
    UserCustomer customer6 = (UserCustomer) users.create(new UserCustomer("Cliente 6"));
    UserCustomer customer7 = (UserCustomer) users.create(new UserCustomer("Cliente 7"));
    UserCustomer customer8 = (UserCustomer) users.create(new UserCustomer("Cliente 8"));
    UserCustomer customer9 = (UserCustomer) users.create(new UserCustomer("Cliente 9"));
    UserCustomer customer10 = (UserCustomer) users.create(new UserCustomer("Cliente 10"));

    Product mobilePhone = products.create(new Product(
      "Celular",
      499.99,
      new GregorianCalendar(2022, Calendar.FEBRUARY, 16).getTime()
    ));
    Product notebook = products.create(new Product(
      "Notebook",
      2500.00,
      new GregorianCalendar(2019, Calendar.FEBRUARY, 16).getTime()
    ));

    stock.create(new Stock(mobilePhone, 300));
    stock.create(new Stock(notebook, 150));

    createNewOrder(customer1, mobilePhone, 2);
    createNewOrder(customer2, mobilePhone, 20);
    createNewOrder(customer3, notebook, 2);
    createNewOrder(customer3, notebook, 4);
    createNewOrder(customer4, mobilePhone, 1);
    createNewOrder(customer5, mobilePhone, 1);
    createNewOrder(customer6, notebook, 6);
    createNewOrder(customer6, mobilePhone, 2);
    createNewOrder(customer6, notebook, 1);
    createNewOrder(customer7, mobilePhone, 15);
    createNewOrder(customer8, mobilePhone, 10);
    createNewOrder(customer9, mobilePhone, 3);
    createNewOrder(customer10, mobilePhone, 4);
  }

  private void createNewOrder(UserCustomer customer, Product product, int quantity) {
    orders.create(new Order(customer, product, quantity));
    stock.remove(product, quantity);
  }
}
