package com.dogs.dao;

import com.dogs.util.HibernateUtil;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class DaoImpl<E, ID extends Serializable> implements Dao<E, ID> {

    private final Class<E> entityClass;

    @Override
    public E save(E entity) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public Optional<E> findById(ID id) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        Optional<E> entity = Optional.ofNullable(em.find(entityClass, id));
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public List<E> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        CriteriaQuery<E> criteria = em.getCriteriaBuilder().createQuery(entityClass);
        criteria.from(entityClass);
        List<E> resultList = em.createQuery(criteria).getResultList();
        em.getTransaction().commit();
        em.close();
        return resultList;
    }

    @Override
    public void update(E entity) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(ID id) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        E e = em.find(entityClass, id);
        em.remove(e);
        em.getTransaction().commit();
        em.close();
    }
}
