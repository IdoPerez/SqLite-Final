package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.sqlite.Grades.GRADE;
import static com.example.sqlite.Grades.Quarter;
import static com.example.sqlite.Grades.Subject;
import static com.example.sqlite.Grades.TABLE_GRADES;
import static com.example.sqlite.Grades.isActive;
import static com.example.sqlite.Student.ADDRESS;
import static com.example.sqlite.Student.DAD_NUM;
import static com.example.sqlite.Student.FATHER_NAME;
import static com.example.sqlite.Student.HOME_NUM;
import static com.example.sqlite.Student.KEY_ID;
import static com.example.sqlite.Student.MOM_NUM;
import static com.example.sqlite.Student.MOTHER_NAME;
import static com.example.sqlite.Student.NAME;
import static com.example.sqlite.Student.PHONE_NUM;
import static com.example.sqlite.Student.TABLE_STUDENTS;


public class HelperDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbProj.db";
    private static final int DATABASE_VERSION = 1;
    private String strCreate, strDelete;

    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Students
        strCreate ="CREATE TABLE "+ TABLE_STUDENTS;
        strCreate += " ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate += " "+NAME+" TEXT,";
        strCreate += " "+ADDRESS+" TEXT,";
        strCreate += " "+PHONE_NUM+" TEXT,";
        strCreate += " "+HOME_NUM+" TEXT,";
        strCreate += " "+FATHER_NAME+" TEXT,";
        strCreate += " "+DAD_NUM+" TEXT,";
        strCreate += " "+MOTHER_NAME+" TEXT,";
        strCreate += " "+MOM_NUM+" TEXT,";
        strCreate += " "+Student.isActive+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);

        //GRADES:
        strCreate ="CREATE TABLE "+ TABLE_GRADES;
        strCreate += " ("+Grades.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate += " "+Grades.NAME+" TEXT,";
        strCreate += " "+ Quarter+" TEXT,";
        strCreate += " "+ Subject+" TEXT,";
        strCreate += " "+GRADE+" INTEGER,";
        strCreate += " "+Grades.isActive+" INTEGER";
        strCreate+=");";

        db.execSQL(strCreate);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        strDelete = "DROP TABLE IF EXISTS "+TABLE_GRADES;
        db.execSQL(strDelete);


        strDelete = "DROP TABLE IF EXISTS "+TABLE_GRADES;
        db.execSQL(strDelete);

        onCreate(db);

    }
}
