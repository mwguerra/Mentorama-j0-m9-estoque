package com.mwguerra;

import com.mwguerra.database.Database;
import com.mwguerra.models.*;
import com.mwguerra.validators.OrderValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
  static Database database = new Database();

  public static void main(String[] args) throws Exception {
    int option;

    database.seeder();

    do {
      showMenu();
      option = securePrompt(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
      handleMenuOptions(option);
    } while(option != 0);
  }

  private static void showMenu() {
    System.out.println("---------------------------------------------");
    System.out.println("-- Sistema de Controle de Vendas e Estoque --");
    System.out.println("---------------------------------------------");

    System.out.println("1. Cadastrar produto");
    System.out.println("2. Listar produtos cadastrados");
    System.out.println("3. Cadastrar usuário administrador");
    System.out.println("4. Cadastrar usuário cliente");
    System.out.println("5. Listar todos os usuários");
    System.out.println("6. Cadastrar estoque de produtos");
    System.out.println("7. Listar o estoque");
    System.out.println("8. Criar pedidos de venda");
    System.out.println("9. Listar pedidos de venda");
    System.out.println("10. Relatório gerencial");

    System.out.println("0. Sair");
  }

  private static int securePrompt(List<Integer> options) {
    Scanner scanner = new Scanner(System.in);
    int option;
    boolean validOption;

    do {
      System.out.print("-- Digite a opção desejada: ");
      option = scanner.nextInt();
      validOption = options.contains(option);

      if (!validOption) {
        System.out.println("Opção " + option + " inválida.");
      }
    } while(!validOption);

    return option;
  }

  private static void handleMenuOptions(int option) throws ParseException {
    switch (option) {
      case 1: // Cadastrar produto
        createProduct();
        break;
      case 2: // Listar produtos cadastrados
        showAllProducts();
        break;
      case 3: // Cadastrar usuário administrador
        createAdminUser();
        break;
      case 4: // Cadastrar usuário cliente
        createCustomerUser();
        break;
      case 5: // Listar todos os usuários
        showAllUsers();
        break;
      case 6: // Cadastrar estoque de produtos
        createStock();
        break;
      case 7: // Listar o estoque
        showAllStock();
        break;
      case 8: // Criar pedidos de venda
        createOrder();
        break;
      case 9: // Listar pedidos de venda
        showAllOrders();
        break;
      case 10: // Relatório gerencial
        report();
        break;
      case 0: // Sair
        break;
    }
  }

  private static void createProduct() throws ParseException {
    Scanner scanner = new Scanner(System.in);

    System.out.println("-------------------------------------------");
    System.out.println("-- Novo Produto ---------------------------");
    System.out.println("-------------------------------------------");

    System.out.print  ("-- Descrição: ");
    String description = scanner.nextLine();

    System.out.print  ("-- Preço: ");
    Double price = scanner.nextDouble();

    System.out.print  ("-- Data de validade: ");
    String dateString = scanner.next();
    Date expirationDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);

    Product product = new Product(description, price, expirationDate);

    database.products.create(product);
  }

  private static void showAllProducts() {
    List<Product> products = database.products.all();

    System.out.println("-------------------------------------------");
    System.out.println("-- Todos os Produtos Cadastrados ----------");
    System.out.println("-------------------------------------------");

    for(Product product: products) {
      System.out.println("-- ID: " + product.getId());
      System.out.println("-- Descrição: " + product.getDescription());
      System.out.println("-- Preço: " + product.getPrice());
      System.out.println("-- Data de validade: " + product.getExpirationDate());
      System.out.println("-------------------------------------------");
    }
  }

  private static void createAdminUser() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("-------------------------------------------");
    System.out.println("-- Novo Usuário Administrador -------------");
    System.out.println("-------------------------------------------");

    System.out.print  ("-- Nome: ");
    String name = scanner.nextLine();

    UserAdmin admin = new UserAdmin(name);

    database.users.create(admin);
  }

  private static void createCustomerUser() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("-------------------------------------------");
    System.out.println("-- Novo Usuário Cliente -------------------");
    System.out.println("-------------------------------------------");

    System.out.print  ("-- Nome: ");
    String name = scanner.nextLine();

    UserCustomer customer = new UserCustomer(name);

    database.users.create(customer);
  }

  private static void showAllUsers() {
    System.out.println("-------------------------------------------");
    System.out.println("-- Todos os Usuários ----------------------");
    System.out.println("-------------------------------------------");

    for (User user: database.users.all()) {
      System.out.println("-- ID: " + user.getId());
      System.out.println("-- Nome: " + user.getName());
      System.out.println("-- Tipo: " + user.getType());
      System.out.println("-------------------------------------------");
    }
  }

  private static void createStock() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("-------------------------------------------");
    System.out.println("-- Novo Estoque ---------------------------");
    System.out.println("-------------------------------------------");

    System.out.print  ("-- ID do produto: ");
    int productId = scanner.nextInt();

    Product product = database.products.find(productId);
    System.out.println("-- Descrição: " + product.getDescription());
    System.out.println("-- Preço: " + product.getPrice());
    System.out.println("-- Data de validade: " + product.getExpirationDate());

    System.out.print  ("-- Quantidade para estoque: ");
    Integer quantity = scanner.nextInt();

    Stock item = new Stock(product, quantity);

    database.stock.create(item);
  }

  private static void showAllStock() {
    System.out.println("-------------------------------------------");
    System.out.println("-- Estoque de Produtos --------------------");
    System.out.println("-------------------------------------------");

    for (Stock item: database.stock.all()) {
      System.out.println("-- ID: " + item.getId());
      System.out.println("-- Produto: " + item.getProduct().getDescription());
      System.out.println("-- Preço: " + item.getProduct().getPrice());
      System.out.println("-- Quantidade: " + item.getQuantity());
      System.out.println("-------------------------------------------");
    }
  }

  private static void createOrder() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("-------------------------------------------");
    System.out.println("-- Novo Pedido ----------------------------");
    System.out.println("-------------------------------------------");

    System.out.print  ("-- ID do cliente: ");
    int customerId = scanner.nextInt();
    UserCustomer customer = (UserCustomer) database.users.find(customerId);
    System.out.println(">> ID: " + customer.getId());
    System.out.println(">> Nome: " + customer.getName());
    System.out.println(">> Tipo: " + customer.getType());

    System.out.print  ("-- ID do produto: ");
    int productId = scanner.nextInt();
    Product product = database.products.find(productId);
    System.out.println(">> Descrição: " + product.getDescription());
    System.out.println(">> Preço: " + product.getPrice());
    System.out.println(">> Data de validade: " + product.getExpirationDate());

    System.out.print  ("-- Quantidade: ");
    Integer quantity = scanner.nextInt();

    Order order = new Order(customer, product, quantity);
    order.setStockForProduct(database.stock.findByProduct(product));

    OrderValidator validator = new OrderValidator().validate(order);
    if (validator.hasErrors()) {
      for (String error: validator.getErrors()) {
        System.out.println("!! " + error);
      }

      return;
    }

    database.stock.remove(product, quantity);
    database.orders.create(order);
  }

  private static void showAllOrders() {
    System.out.println("-------------------------------------------");
    System.out.println("-- Todos os Pedidos de Venda --------------");
    System.out.println("-------------------------------------------");

    for (Order order: database.orders.all()) {
      System.out.println("-- ID: " + order.getId());
      System.out.println("-- Cliente: " + order.getCustomer());
      System.out.println("-- Produto: " + order.getProduct());
      System.out.println("-- Quantidade: " + order.getQuantity());
      System.out.println("-- Valor Total: " + order.getTotalPrice());
      System.out.println("-------------------------------------------");
    }
  }

  private static void report() {
    List<Order> orders = database.orders.all();

    System.out.println("-------------------------------------------");
    System.out.println("-- Relatório Gerencial --------------------");
    System.out.println("-------------------------------------------");

    String bestCustomer = orders.stream()
      .sorted(Comparator.comparing(Order::getQuantity).reversed())
      .map(order -> order.getCustomer().getName())
      .findFirst()
      .orElse("Ninguém comprou nada ainda");

    String worstCustomer = orders.stream()
      .sorted(Comparator.comparing(Order::getQuantity))
      .map(order -> order.getCustomer().getName())
      .findFirst()
      .orElse("Ninguém comprou nada ainda");

    double average = orders.stream()
      .mapToDouble(Order::getQuantity)
      .average()
      .orElse(Double.NaN);

    System.out.println("-- Cliente que mais fez compras: " + bestCustomer);
    System.out.println("-- Cliente que fez menos compras: " + worstCustomer);
    System.out.println("-- Média de compras: " + average);
  }
}
