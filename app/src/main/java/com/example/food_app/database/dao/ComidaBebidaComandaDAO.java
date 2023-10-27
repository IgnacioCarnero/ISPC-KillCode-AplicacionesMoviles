package com.example.food_app.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.food_app.database.entity.comidaBebidaComandaEntity;


import java.util.List;
@Dao
public interface ComidaBebidaComandaDAO {
    @Query("SELECT  * FROM comida_bebida_comanda")
    List<comidaBebidaComandaEntity>getId_comanda();

    @Query("SELECT * FROM comida_bebida_comanda  WHERE id_comidaBebida= :comidaBebidaID")
    comidaBebidaComandaEntity getId_comidaBebida(int comidaBebidaID);


    @Insert
    void insertcomidaBebida(comidaBebidaComandaEntity comidaBebidaComandaEntity);

    @Update
    void updatecomidaBebida(comidaBebidaComandaEntity comidaBebidaComandaEntity);

    @Delete
    void deletecomidaBebida(comidaBebidaComandaEntity comidaBebidaComandaEntity);
}





