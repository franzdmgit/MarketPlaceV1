package com.unicaldasmafe.example.grupo10.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.unicaldasmafe.example.grupo10.database.dao.UserDao;
import com.unicaldasmafe.example.grupo10.util.Constant;

@Database(entities = {User.class}, version = 1)

public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
    private static UserDatabase userDB;

    public static  UserDatabase getInstance(Context context){
        if (userDB == null){
            userDB = buildDatabaseBuilder(context);
        }
        return userDB;
    }

    private static UserDatabase buildDatabaseBuilder(Context context){
        return Room.databaseBuilder(context, UserDatabase.class, Constant.NAME_DATABASE)
                .allowMainThreadQueries().build();

    }

}
