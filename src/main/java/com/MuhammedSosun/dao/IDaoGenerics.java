package com.MuhammedSosun.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface IDaoGenerics<T> {
    T create(T t);
    Optional<T> findByid(Integer id);
    Optional<T> findByName(String name);
    List<T> list();
    Optional<T> update(int id,T t);
    Optional<T> delete(int id);
    void chooise();
    default Connection getInterfaceConnection(){
        return null;
    }
}
