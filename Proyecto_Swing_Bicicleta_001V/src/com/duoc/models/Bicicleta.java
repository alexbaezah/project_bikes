package com.duoc.models;

public class Bicicleta {
    
    //private static BicicletaController bc = new BicicletaController();
    private int ID;
    private String modelo;
    private String talla;
    private String suspension;
    private String transmision;
    private String frenos;
    private int stock;
    private int valor;
    private int categoria;
    private int fabricante;
    
    public Bicicleta(){
    }

    public Bicicleta(int ID, String modelo, String talla, String suspension, String transmision, String frenos, int stock, int valor, int categoria, int fabricante) {
        this.ID = ID;
        this.modelo = modelo;
        this.talla = talla;
        this.suspension = suspension;
        this.transmision = transmision;
        this.frenos = frenos;
        this.stock = stock;
        this.valor = valor;
        this.categoria = categoria;
        this.fabricante = fabricante;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getSuspension() {
        return suspension;
    }

    public void setSuspension(String suspension) {
        this.suspension = suspension;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getFrenos() {
        return frenos;
    }

    public void setFrenos(String frenos) {
        this.frenos = frenos;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getFabricante() {
        return fabricante;
    }

    public void setFabricante(int fabricante) {
        this.fabricante = fabricante;
    }
    
    
    
    
    
}
