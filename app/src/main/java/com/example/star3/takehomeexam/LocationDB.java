package com.example.star3.takehomeexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by star3 on 2018-04-19.
 */

public class LocationDB extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "my_database";
    // 테이블과 데이터베이스 업데이트를 위해 이 숫자를 토글 한다.
    private static final int DATABASE_VERSION = 1;
    // 생성하자고 하는 테이블 이름
    public static final String TABLE_NAME = "my_table";
    // 일부 예제 필드
    public static final String NAME = "name";
    public static final String CONTENTS = "contents";
    public static final String POS_X = "x";
    public static final String POS_Y = "y";

    public LocationDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Log.d(this.getClass().getName(), "=====OnCreate Mehod Start.======");
        // Log.d(this.getClass().getName(), "=====Create Table " + TABLE_NAME + ".======");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + NAME + " VARCHAR(255), " + CONTENTS + " VARCHAR(255), " + POS_X + " REAL, " + POS_Y + " REAL);");
        //db.execSQL("CREATE TABLE my_database.db3 ( name VARCHAR(255), contents VARCHAR(255), x REAL, y REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // TODO Auto-generated method stub


        // 업그레이드 되면 이전 데이블을 제거 한다.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // 테이블의 새 인스턴스를 생성한다.
        onCreate(db);
    }
}