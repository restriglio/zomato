package com.androcid.zomato.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androcid.zomato.dao.classes.PhotoItemDao;
import com.androcid.zomato.dao.classes.RestaurantImageDao;
import com.androcid.zomato.util.MyLg;

import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * Master of DAO (schema version 1000): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1003 ;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        RestaurantImageDao.createTable(db, ifNotExists);
        PhotoItemDao.createTable(db, ifNotExists);
        //TODO ADD HERE
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        RestaurantImageDao.dropTable(db, ifExists);
        PhotoItemDao.dropTable(db, ifExists);
        //TODO ADD HERE
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            MyLg.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, true);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            MyLg.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(RestaurantImageDao.class);
        registerDaoClass(PhotoItemDao.class);
        //TODO ADD HERE
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
