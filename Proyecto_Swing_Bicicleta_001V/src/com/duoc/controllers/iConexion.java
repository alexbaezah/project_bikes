package com.duoc.controllers;

import java.util.ArrayList;

public interface iConexion<T> {
    
    //DEFINIR LA FIRMA DE LOS METODOS QUE EL RESTO DE LOS CONTROLADORES DEBERAN IMPLEMENTAR
    public boolean create(T t);
    public boolean update(T t);
    public boolean delete(Object key);
    public T read(Object key);
    public ArrayList<T> readAll();
    
}
