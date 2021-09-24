package com.mwguerra.validators;

import com.mwguerra.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface IValidator {
  List<String> errors = new ArrayList<>();

  OrderValidator validate(Order order);

  default boolean hasErrors() {
    return !isValid();
  }

  default boolean isValid() {
    return errors.isEmpty();
  }

  default List<String> getErrors() {
    return errors;
  }
}
