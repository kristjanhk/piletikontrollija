package com.example.kristen.piletdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int databaseVersion = 1;
    public String createQuery = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME +
            "(" + TableData.TableInfo.VALID_CODE + " TEXT);";
    public String createQuery2 = "CREATE TABLE " + TableData.TableInfo.TABLE_AUTH +
            "(" + TableData.TableInfo.AUTH_KEY + " TEXT);";

    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, databaseVersion);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(createQuery);
        sdb.execSQL(createQuery2);
        Log.d("Database operations", "Tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void putInformation(DatabaseOperations dop, String code) {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.VALID_CODE, code);
        SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);
        Log.d("Database operations", "One row inserted");
    }

    public Cursor getInformation(DatabaseOperations dop) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.VALID_CODE};
        return SQ.query(TableData.TableInfo.TABLE_NAME, columns, null, null, null, null, null);
    }

    public void putAuthKey(DatabaseOperations dop,  String authKey) {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.AUTH_KEY, authKey);
        SQ.insert(TableData.TableInfo.TABLE_AUTH, null, cv);
        Log.d("Database operations", "Key added");
    }

    public Cursor getAuthKey(DatabaseOperations dop) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.AUTH_KEY};
        return SQ.query(TableData.TableInfo.TABLE_AUTH, columns, null, null, null, null, null);
    }
}