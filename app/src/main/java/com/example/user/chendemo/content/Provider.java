package com.example.user.chendemo.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by YoungH on 3/24/17.
 */

public class Provider extends ContentProvider {
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }
    @Override
    public String getType(Uri uri) {
        return null;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }
    @Override
    public boolean onCreate() {
        SQLiteDatabase db=this.getContext().openOrCreateDatabase("test_db2.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE t_user (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL)");
        ContentValues values=new ContentValues();
        values.put("name", "liangjh2");
        db.insert("test_user", "id", values);
        db.close();
        return false;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db=this.getContext().openOrCreateDatabase("test_db2.db", Context.MODE_PRIVATE, null);
        Cursor c = db.query("t_user", null, null, null, null, null, null);
        db.close();
        return c;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
       return 0;
    }
}