package com.example.apktest.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteConnect extends SQLiteOpenHelper {
    public SQLiteConnect(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    //truy van khong tra ve kq: inseat delete creeat..
    public  void  queryData(String query){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(query);
    }
    //truy van tra ve ket qua

    public Cursor getData(String query){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor data = sqLiteDatabase.rawQuery(query,null);
        return data;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
