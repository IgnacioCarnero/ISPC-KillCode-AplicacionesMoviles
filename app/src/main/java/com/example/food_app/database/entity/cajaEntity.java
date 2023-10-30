package com.example.food_app.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "caja", foreignKeys = @ForeignKey(entity = comandaEntity.class, parentColumns = "id_comanda",
        childColumns = "id_comanda", onDelete = ForeignKey.CASCADE))
public class cajaEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_ingreso")
    @NonNull
    private int id_ingreso;

    @ColumnInfo(name = "id_comanda")
    @NonNull
    private int id_comanda;

    public cajaEntity(int id_ingreso, int id_comanda) {
        this.id_ingreso = id_ingreso;
        this.id_comanda = id_comanda;
    }

    public int getId_ingreso() {
        return id_ingreso;
    }

    public void setId_ingreso(int id_ingreso) {
        this.id_ingreso = id_ingreso;
    }

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }
}
