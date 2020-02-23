package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.sqlite.Grades.TABLE_GRADES;
import static com.example.sqlite.Student.TABLE_STUDENTS;

public class Infore extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<String> Students = new ArrayList<>();
    ArrayList<String> AraayGrades = new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> adp;
    HelperDB hlp;
    SQLiteDatabase db;
    Cursor crsr;
    int itemDelete;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infore);

        listView = (ListView) findViewById(R.id.Infor);
        tv = (TextView) findViewById(R.id.textView2);
        listView.setOnItemClickListener(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        hlp = new HelperDB(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemDelete = position;
        tv.setText(Students.get(position));
    }

    public void SB(View view) {
        Students.clear();
        int Key, isActive;
        db = hlp.getReadableDatabase();
        crsr = db.query(TABLE_STUDENTS, null,null,null,null,null,null);

        int col0 = crsr.getColumnIndex(Student.KEY_ID);
        int col1 = crsr.getColumnIndex(Student.NAME);
        int col2 = crsr.getColumnIndex(Student.ADDRESS);
        int col3 = crsr.getColumnIndex(Student.PHONE_NUM);
        int col4 = crsr.getColumnIndex(Student.HOME_NUM);
        int col5 = crsr.getColumnIndex(Student.FATHER_NAME);
        int col6 = crsr.getColumnIndex(Student.MOTHER_NAME);
        int col7 = crsr.getColumnIndex(Student.DAD_NUM);
        int col8 = crsr.getColumnIndex(Student.MOM_NUM);
        int col9 = crsr.getColumnIndex(Student.isActive);

        String tmp;


        crsr.moveToFirst();
        while (!crsr.isAfterLast()){
            isActive = crsr.getInt(col9);
            if(isActive == 1) {
                Key = crsr.getInt(col0);
                String Name = crsr.getString(col1);
                String Address = crsr.getString(col2);
                String Phone_num = crsr.getString(col3);
                String Home_num = crsr.getString(col4);
                String Father = crsr.getString(col5);
                String Mother = crsr.getString(col6);
                String Dad_num = crsr.getString(col7);
                String Mom_num = crsr.getString(col8);

                tmp = "" + Key + ". " + Name + ", " + Address + ", " + Phone_num + ", " + Home_num + ", " + Father + ", " + Mother + ", " + Dad_num + ", " + Mom_num;
                Students.add(tmp);
            }
            crsr.moveToNext();
        }
        db.close();
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,  R.layout.support_simple_spinner_dropdown_item, Students);
        listView.setAdapter(adp);
    }

    public void GB(View view) {
        AraayGrades.clear();
        db = hlp.getReadableDatabase();
        crsr = db.query(TABLE_GRADES, null,null,null,null,null,null);

        int col0 = crsr.getColumnIndex(Grades.KEY_ID);
        int col1 = crsr.getColumnIndex(Grades.NAME);
        int col2 = crsr.getColumnIndex(Grades.Quarter);
        int col3 = crsr.getColumnIndex(Grades.GRADE);
        int col4 = crsr.getColumnIndex(Grades.isActive);

        String tmp;
        int Key, isActive;

        crsr.moveToFirst();
        while (!crsr.isAfterLast()){
            isActive = crsr.getInt(col4);
            if(isActive == 1) {
                Key = crsr.getInt(col0);
                String Name = crsr.getString(col1);
                String Quarter = crsr.getString(col2);
                String Subject = crsr.getString(col3);
                String Grade = crsr.getString(col4);

                tmp = "" + Key + ". " + Name + ", " + Quarter + ", " + Subject + ", " + Grade;
                AraayGrades.add(tmp);
            }
            crsr.moveToNext();
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,  R.layout.support_simple_spinner_dropdown_item, Students);
        listView.setAdapter(adp);
    }
        /*
    public void Delete(View view) {
        String tmp = Students.get(itemDelete);
        Students.remove(itemDelete);
        ContentValues cv = new ContentValues();
        int i = 0;
        String key = "";

        while(tmp.charAt(i) != '.'){
            key += tmp.charAt(i);
            i++;
        }
        //tv.setText(key);

            cv.put(Student.isActive, 0);
            db = hlp.getWritableDatabase();
            db.update(TABLE_STUDENTS, cv,  + "=?", new String[]{"1"});
            db.close();
            ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Students);
            listView.setAdapter(adp);

    } */
}
