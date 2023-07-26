package com.dogs.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Dao <E, ID extends Serializable> {
    E save(E entity);
    Optional<E> findById(ID id);
    List<E> findAll();
    void update(E entity);
    void delete(ID id);
}
