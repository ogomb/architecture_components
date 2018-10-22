package com.example.ogomb.androidarchitecturecomponents.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.ogomb.androidarchitecturecomponents.database.converter.DateConverter;
import com.example.ogomb.androidarchitecturecomponents.database.entity.User;
import com.example.ogomb.androidarchitecturecomponents.database.dao.UserDao;

@Database(entities = {User.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {
    private static volatile MyDatabase INSTANCE;

    public abstract UserDao userDao();
}
