package com.MuhammedSosun.dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface IDaoGenerics<T> {
    T create(T t);

    T findByName(Integer id);

    ArrayList<T> list();

    T update(int id,T t);

    T delete(int id);

    public void chooise();

    default Connection getInterfaceConnection(){
        return null;
    }
}
