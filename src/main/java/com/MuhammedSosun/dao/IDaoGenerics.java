package com.MuhammedSosun.dao;

import java.sql.Connection;
import java.util.List;

public interface IDaoGenerics<T> {
    T create(T t);
    T findByid(Integer id);
    T findByName(String name);
    List<T> list();
    T update(int id,T t);
    T delete(int id);
    void chooise();
    default Connection getInterfaceConnection(){
        return null;
    }
}
