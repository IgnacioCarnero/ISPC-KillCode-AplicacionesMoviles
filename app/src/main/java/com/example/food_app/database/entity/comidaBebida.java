package com.example.food_app.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

@Entity(tableName = "comidaBebida" , foreignKeys = @ForeignKey(entity = categoriaEntity.class,
        parentColumns = "id_categoria", childColumns = "id_categoria", onDelete = ForeignKey.CASCADE))

public class comidaBebida {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_comidaBebida")
    private int id_comidaBebida;

    @ColumnInfo(name = "nombre")
    @NonNull
    private String nombre;

    @ColumnInfo(name = "tipo")
    @NonNull
    private String tipo;

    @ColumnInfo(name = "precio")
    @NonNull
    private BigDecimal precio;

    @ColumnInfo(name = "descripcion")
    @NonNull
    private String descripcion;


    @ColumnInfo(name = "id_categoria")
    @NonNull
    private int id_categoria;

    public comidaBebida (@NonNull String nombre, @NonNull String tipo, @NonNull BigDecimal precio, @NonNull String descripcion, int id_categoria) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.id_categoria = id_categoria;
    }

    public int getId_comidaBebida() {
        return id_comidaBebida;
    }

    public void setId_comidaBebida(int id_comidaBebida) {
        this.id_comidaBebida = id_comidaBebida;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getTipo() {
        return tipo;
    }

    public void setTipo(@NonNull String tipo) {
        this.tipo = tipo;
    }

    @NonNull
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(@NonNull BigDecimal precio) {
        this.precio = precio;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}
