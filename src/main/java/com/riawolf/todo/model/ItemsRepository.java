package com.riawolf.todo.model;

import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends CrudRepository<ToDoItem, Integer> {
}
