package com.dogs.dao;

import com.dogs.entities.Tag;
import com.dogs.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class TagDaoImpl extends DaoImpl<Tag, Long> implements TagDao {
    public TagDaoImpl(Class<Tag> entityClass) {
        super(entityClass);
    }
}
