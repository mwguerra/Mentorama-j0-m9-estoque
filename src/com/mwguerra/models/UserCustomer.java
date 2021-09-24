package com.mwguerra.models;

public class UserCustomer extends User {
  public UserCustomer(String name) {
    super(name, UserType.CUSTOMER);
  }
}
