package com.dogs.mappers;

public interface Mapper<E, D> {
    D mapToDto(E entity);

    E mapToEntity(D dto);
}
