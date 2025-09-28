package org.carturo.ministore.repository;

import java.util.List;

public interface Repository<T> {
    T create(T t);
    T findById(int id);
    List<T> findAll();
    boolean update(T t);
    boolean delete(T t);
    List<T> findByName(String name);
}