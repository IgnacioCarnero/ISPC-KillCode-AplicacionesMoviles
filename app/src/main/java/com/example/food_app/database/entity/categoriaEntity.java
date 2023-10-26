package com.example.food_app.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categoria")
public class categoriaEntity {
    @PrimaryKey(autoGenerate = true)
    int id_categoria;

    @ColumnInfo(name = "nombre")
    @NonNull
    String nombre;

    public categoriaEntity(@NonNull String nombre) {
        this.nombre = nombre;
    }


    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }
}
