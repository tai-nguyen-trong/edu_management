package com.edu.dao;

import java.util.List;

abstract public class EduSysDAO<EntityType, KeyType> {
    abstract public void insert(EntityType entity);
    abstract public void update(EntityType entity);
    abstract public void delete(KeyType id);
    abstract public EntityType selectById(KeyType id);
    abstract public List<EntityType> selectAll();
    abstract protected List<EntityType> selectBySql(String sql, Object...args);
    //Object...args: Những đối số cần thiết để đưa vào dấu ? trong câu truy vấn
}