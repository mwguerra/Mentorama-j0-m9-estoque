package com.mwguerra.database;

import com.mwguerra.interfaces.IDatabaseTable;
import com.mwguerra.interfaces.IModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DatabaseTable<T extends IModel> implements IDatabaseTable<T> {
  Map<Integer, T> databaseTable = new HashMap<>();

  public List<T> all() {
    List<T> items = new ArrayList<>();

    for (Map.Entry<Integer, T> item: databaseTable.entrySet()) {
      items.add(item.getValue());
    }

    return items;
  }

  public T create(T item) {
    int id = databaseTable.size() + 1;
    item.setId(id);

    databaseTable.put(id, item);

    return item;
  }

  public T find(int id) {
    return databaseTable.get(id);
  }

  public Map<Integer, T> getDatabaseTable() {
    return databaseTable;
  }
}
