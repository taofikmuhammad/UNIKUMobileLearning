package com.priludestudio.inovasipembelajaran;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBMateri extends SQLiteAssetHelper {
    private static final String DATABASE_NAME="materi_db.sqlite";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="pemantik";
    private static final String KEY_ID="ID";
    private static final String KEY_ISI ="isi";

    public DBMateri(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //untuk cek apakah data ada isinya atau tidak
    public boolean isNull()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM "+TABLE_NAME+"";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        db.close();
        return icount <= 0;
    }

    //untuk mendapatkan data
    public Materi findMateri()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new
                String[]{KEY_ID, KEY_ISI},null,null,null,null,null);
        Materi u=new Materi();
        if (cursor!=null && cursor.moveToFirst())
        {
            cursor.moveToFirst();
            u.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            u.setIsi(cursor.getString(cursor.getColumnIndex("isi")));
            ;
        }else
        {
            u.setId(0);
            u.setIsi("");
        }
        db.close();
        return u;
    }
}
