package com.example.food_app.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "comanda", foreignKeys = {
        @ForeignKey(entity = mesaEntity.class,
                parentColumns = "id_mesa",
                childColumns = "id_mesa",
                onDelete = ForeignKey.SET_NULL),
        @ForeignKey(entity = mozoEntity.class,
                parentColumns = "id_mozo",
                childColumns = "id_mozo",
                onDelete = ForeignKey.SET_NULL)
})
public class comandaEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id_comanda")
    private int id_comanda;

    @ColumnInfo(name = "id_mesa")
    @NonNull
    private int id_mesa;

    @ColumnInfo(name = "id_mozo")
    @NonNull
    private int id_mozo;

    @ColumnInfo(name = "fecha_hora")
    @NonNull
    private String fecha_hora;

    @ColumnInfo(name = "extra")
    @NonNull
    private String extra;

    public comandaEntity(int id_comanda, int id_mesa, int id_mozo, @NonNull String fecha_hora, @NonNull String extra) {
        this.id_comanda = id_comanda;
        this.id_mesa = id_mesa;
        this.id_mozo = id_mozo;
        this.fecha_hora = fecha_hora;
        this.extra = extra;
    }

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getId_mozo() {
        return id_mozo;
    }

    public void setId_mozo(int id_mozo) {
        this.id_mozo = id_mozo;
    }

    @NonNull
    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(@NonNull String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    @NonNull
    public String getExtra() {
        return extra;
    }

    public void setExtra(@NonNull String extra) {
        this.extra = extra;
    }
}
