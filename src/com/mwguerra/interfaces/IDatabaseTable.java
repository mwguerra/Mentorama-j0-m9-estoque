package com.mwguerra.interfaces;

import java.util.List;

public interface IDatabaseTable<T> {
  List<T> all();
  T create(T item);
  T find(int id);
}
