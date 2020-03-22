package com.keights.vikran.Network;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.keights.vikran.ResponseModel.UserInfo;

@Database(
        entities = {UserInfo.class},
        version = 1,
        exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract DBAccess dbAccess();
}