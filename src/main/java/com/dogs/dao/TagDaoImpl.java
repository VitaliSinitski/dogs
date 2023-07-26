package com.dogs.dao;

import com.dogs.entities.Tag;

public class TagDaoImpl extends DaoImpl<Tag, Long> implements TagDao {
    private static final TagDaoImpl INSTANCE = new TagDaoImpl();

    public TagDaoImpl() {
        super(Tag.class);
    }

    public static TagDaoImpl getInstance() {
        return INSTANCE;
    }
}
