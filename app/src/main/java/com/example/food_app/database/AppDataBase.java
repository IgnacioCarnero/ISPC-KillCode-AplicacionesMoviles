package com.example.food_app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.food_app.database.dao.CajaDAO;
import com.example.food_app.database.dao.CategoriaDAO;
import com.example.food_app.database.dao.ComandaDAO;
//import com.example.food_app.database.dao.ComidaBebidaComandaDAO;
import com.example.food_app.database.dao.ComidaBebidaDAO;
import com.example.food_app.database.dao.MesaDAO;
import com.example.food_app.database.dao.MozoDAO;
import com.example.food_app.database.entity.cajaEntity;
import com.example.food_app.database.entity.categoriaEntity;
import com.example.food_app.database.entity.comandaEntity;
import com.example.food_app.database.entity.comidaBebida;
import com.example.food_app.database.entity.comidaBebidaComandaEntity;
import com.example.food_app.database.entity.mesaEntity;
import com.example.food_app.database.entity.mozoEntity;
import com.example.food_app.database.utility.bigDecimalConverter;

@Database(entities = {
        cajaEntity.class, categoriaEntity.class, comandaEntity.class, comidaBebidaComandaEntity.class, comidaBebida.class, mesaEntity.class, mozoEntity.class
}, version = 1)
@TypeConverters(bigDecimalConverter.class)
public abstract class AppDataBase extends RoomDatabase {
    public static AppDataBase INSTANCE;
    public abstract CategoriaDAO categoriaDAO();
    public abstract CajaDAO cajaDAO();
    public abstract ComandaDAO comandaDAO();
    //public abstract ComidaBebidaComandaDAO comidaBebidaComandaDAO();
    public abstract ComidaBebidaDAO comidaBebidaDAO();
    public abstract MesaDAO mesaDAO();
    public abstract MozoDAO mozoDAO();


    public static synchronized AppDataBase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDataBase.class, "OrderTablet.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }


}
