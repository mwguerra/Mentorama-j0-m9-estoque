package com.mwguerra.models;

import com.mwguerra.interfaces.IModel;

public class Model implements IModel {
  private Integer id;

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }
}
