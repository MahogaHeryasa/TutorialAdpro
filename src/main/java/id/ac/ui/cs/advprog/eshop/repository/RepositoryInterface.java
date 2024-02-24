package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface RepositoryInterface<T> {
    T create(T object);
    Iterator<T> findAll();
    void delete(String productId);
    T findById(String id);
    T update(String id, T updated);
}
