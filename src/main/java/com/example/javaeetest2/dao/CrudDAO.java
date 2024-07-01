package com.example.javaeetest2.dao;

import java.util.List;

public interface CrudDAO <T>{
    List<T> findAll();
    T save (T model);
}
