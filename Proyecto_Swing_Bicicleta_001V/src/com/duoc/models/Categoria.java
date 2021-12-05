/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duoc.models;


public class Categoria {
    
    private int ID;
    private String descripcion;
    
    public Categoria(){
    }

    public Categoria(int ID, String descripcion) throws CustomException {
        this.ID = ID;
        this.descripcion = descripcion;
        
    }
    
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) throws CustomException {
        String id_caracter = String.valueOf(ID);
        
        for(int i = 0; i < id_caracter.length(); i++)
        {
            if(! Character.isDigit(id_caracter.charAt(i)))
            {
                throw new CustomException("Debe ingresar un código numérico");
            }
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) throws CustomException {
        if(descripcion.isEmpty() || descripcion.trim().length() < 4)
        {
            throw new CustomException("Debes ingresar al menos 4 caracteres.");
        }
        else if (descripcion.trim().length() > 100)
        {
            throw new CustomException("La descripción excede el máximo de caracteres permitidos");
        }
        else
        {
            this.descripcion = descripcion;
        }
    }   
    
    @Override
    public String toString(){
        return this.descripcion;
    }
    
    
    
    
}
