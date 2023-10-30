package com.example.food_app.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mozo")
public class mozoEntity {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_mozo")
    int id_mozo;

    @ColumnInfo(name = "nombre")
    @NonNull
    String nombre;

    @ColumnInfo(name = "apellido")
    @NonNull
    String apellido;

    public mozoEntity(int id_mozo, @NonNull String nombre, @NonNull String apellido) {
        this.id_mozo = id_mozo;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getId_mozo() {
        return id_mozo;
    }

    public void setId_mozo(int id_mozo) {
        this.id_mozo = id_mozo;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getApellido() {
        return apellido;
    }

    public void setApellido(@NonNull String apellido) {
        this.apellido = apellido;
    }
}
