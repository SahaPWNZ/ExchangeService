package com.example.javaeetest2.dao;

import java.util.ArrayList;
import java.util.Optional;

public interface CrudDAO <T>{
    ArrayList<T> findAll();
    Optional<T> save (T model);
}
