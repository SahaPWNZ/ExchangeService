package com.example.javaeetest2.dao;

import java.util.ArrayList;

public interface CrudDAO <T>{
    ArrayList<T> findAll();
    T save (T model);
}
