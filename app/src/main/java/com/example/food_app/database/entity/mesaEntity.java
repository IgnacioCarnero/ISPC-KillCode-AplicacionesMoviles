package com.example.food_app.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mesa")
public class mesaEntity {
    @PrimaryKey(autoGenerate = false)
    int id_mesa;

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }
}
