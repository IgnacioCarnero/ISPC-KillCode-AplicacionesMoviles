package com.example.food_app;

import java.math.BigDecimal;

public class ListElement {
    private String name;
    private String tipo;
    private BigDecimal precio;
    private String descripcion;

    public ListElement(String name, String tipo, BigDecimal precio, String descripcion) {
        this.name = name;
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}