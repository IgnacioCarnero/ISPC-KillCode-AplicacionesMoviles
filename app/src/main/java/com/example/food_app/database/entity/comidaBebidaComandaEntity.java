package com.example.food_app.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "comida_bebida_comanda", foreignKeys = {
        @ForeignKey(entity = comidaBebida.class, parentColumns = "id_comidaBebida",
                childColumns = "id_comidaBebida", onDelete = ForeignKey.SET_NULL),
        @ForeignKey(entity = comandaEntity.class, parentColumns = "id_comanda",
                childColumns = "id_comanda", onDelete = ForeignKey.SET_NULL)
})
public class comidaBebidaComandaEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_comanda")
    @NonNull
    private int id_comanda;

    @ColumnInfo(name = "id_comidaBebida")
    @NonNull
    private int id_comidaBebida;


    public comidaBebidaComandaEntity(int id_comanda, int id_comidaBebida) {
        this.id_comanda = id_comanda;
        this.id_comidaBebida = id_comidaBebida;
    }

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    public int getId_comidaBebida() {
        return id_comidaBebida;
    }

    public void setId_comidaBebida(int id_comidaBebida) {
        this.id_comidaBebida = id_comidaBebida;
    }
}
