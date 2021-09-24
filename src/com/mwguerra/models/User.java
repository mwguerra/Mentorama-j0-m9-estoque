package com.mwguerra.models;

public abstract class User extends Model {
  private String name;
  private UserType type;

  public User(String name, UserType type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserType getType() {
    return type;
  }

  public void setType(UserType type) {
    this.type = type;
  }
}