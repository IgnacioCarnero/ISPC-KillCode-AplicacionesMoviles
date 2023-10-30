package com.example.food_app.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.food_app.database.entity.cajaEntity;

@Dao
public interface CajaDAO {
    @Insert()
    void insertCaja(cajaEntity caja);

    @Update()
    void updateCaja(cajaEntity caja);

    @Delete()
    void deleteCaja(cajaEntity caja);

    @Query("SELECT * FROM caja WHERE id_ingreso = :id_ingreso ")
    cajaEntity getCajaId(int id_ingreso);

}
