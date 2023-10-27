package com.example.food_app;

public class ListElement {

    public String name;
    public String descripcion;
    public String anexo;


    public ListElement(String name, String descripcion, String anexo) {
        this.name = name;
        this.descripcion = descripcion;
        this.anexo = anexo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }




}
