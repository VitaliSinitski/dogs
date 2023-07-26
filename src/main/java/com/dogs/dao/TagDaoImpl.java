package com.dogs.dao;

import com.dogs.entities.Tag;

public class TagDaoImpl extends DaoImpl<Tag, Long> implements TagDao {
    public TagDaoImpl() {
        super(Tag.class);
    }
}
