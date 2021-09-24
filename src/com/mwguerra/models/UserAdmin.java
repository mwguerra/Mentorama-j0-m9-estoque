package com.mwguerra.models;

public class UserAdmin extends User {
  public UserAdmin(String name) {
    super(name, UserType.ADMIN);
  }
}