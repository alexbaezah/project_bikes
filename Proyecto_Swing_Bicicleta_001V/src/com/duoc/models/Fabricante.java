
package com.duoc.models;


public class Fabricante {
    
    private int ID;
    private String nombre;
    
    public Fabricante(){
    }
    
    public Fabricante(int ID, String nombre) throws CustomException {
        this.setID(ID);
        this.setNombre(nombre);
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws CustomException {
        if(nombre.isEmpty() || nombre.trim().length() < 4)
        {
            throw new CustomException("Debes ingresar al menos 4 caracteres.");
        }
        else if (nombre.trim().length() > 40)
        {
            throw new CustomException("El nombre excede el máximo de caracteres permitido");
        }
        else
        {
            this.nombre = nombre;
        }
    }   
    
    @Override
    public String toString(){
        return this.nombre;
    }

    
    

    
}
