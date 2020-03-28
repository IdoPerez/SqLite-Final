package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.sqlite.Grades.Subject;
import static com.example.sqlite.Grades.TABLE_GRADES;
import static com.example.sqlite.Student.KEY_ID;
import static com.example.sqlite.Student.TABLE_STUDENTS;

public class Infore extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<String> Students = new ArrayList<>();
    ArrayList<String> ArrayGrades = new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> adp;
    HelperDB hlp;
    SQLiteDatabase db;
    Cursor crsr;
    int itemDelete = -1;
    int Type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infore);

        listView = (ListView) findViewById(R.id.Infor);


        listView.setOnItemClickListener(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        hlp = new HelperDB(this);


    }

    /**
     * The clicked item is the chosen one to delete.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemDelete = position;
        Toast.makeText(this, Students.get(itemDelete)+""+"chose for delete", Toast.LENGTH_SHORT).show();
    }

    /**
     * The method reading from the Grades table the whole information, and present it on listView.
     * @param view
     */
    public void SB(View view) {

        String selection = null;
        String[] selectionArgs = {"1"};
        selection = Student.isActive+"=?";

        Students.clear();
        Type = 1;
        int Key, isActive;
        db = hlp.getReadableDatabase();
        crsr = db.query(TABLE_STUDENTS, null,selection,selectionArgs,null,null,null);

        int col0 = crsr.getColumnIndex(Student.KEY_ID);
        int col1 = crsr.getColumnIndex(Student.NAME);
        int col2 = crsr.getColumnIndex(Student.ADDRESS);
        int col3 = crsr.getColumnIndex(Student.PHONE_NUM);
        int col4 = crsr.getColumnIndex(Student.HOME_NUM);
        int col5 = crsr.getColumnIndex(Student.FATHER_NAME);
        int col6 = crsr.getColumnIndex(Student.MOTHER_NAME);
        int col7 = crsr.getColumnIndex(Student.DAD_NUM);
        int col8 = crsr.getColumnIndex(Student.MOM_NUM);



        crsr.moveToFirst();
        while (!crsr.isAfterLast()){
                Key = crsr.getInt(col0);
                String Name = crsr.getString(col1);
                String Address = crsr.getString(col2);
                String Phone_num = crsr.getString(col3);
                String Home_num = crsr.getString(col4);
                String Father = crsr.getString(col5);
                String Mother = crsr.getString(col6);
                String Dad_num = crsr.getString(col7);
                String Mom_num = crsr.getString(col8);

                String tmp;

                tmp = "" + Key + ". " + Name + ", " + Address + ", " + Phone_num + ", " + Home_num + ", " + Father + ", " + Mother + ", " + Dad_num + ", " + Mom_num;
                Students.add(tmp);
            crsr.moveToNext();
        }
        db.close();
        adp = new ArrayAdapter<String>(this,  R.layout.support_simple_spinner_dropdown_item, Students);
        listView.setAdapter(adp);
    }

    /**
     * The method reading from the Grades table the whole information, and present it on listView.
     * @param view
     */
    public void GB(View view) {

        ArrayGrades.clear();
        Type = 2;
        db = hlp.getReadableDatabase();

        String selection = null;
        String[] selectionArgs = {"1"};
        selection = Grades.isActive+"=?";

        crsr = db.query(TABLE_GRADES, null,selection,selectionArgs,null,null,null);

        int col0 = crsr.getColumnIndex(Grades.KEY_ID);
        int col1 = crsr.getColumnIndex(Grades.NAME);
        int col2 = crsr.getColumnIndex(Grades.Quarter);
        int col3 = crsr.getColumnIndex(Subject);
        int col4 = crsr.getColumnIndex(Grades.GRADE);

        String tmp;
        int Key, isActive;

        crsr.moveToFirst();
        while (!crsr.isAfterLast()){
                Key = crsr.getInt(col0);
                String Name = crsr.getString(col1);
                String Quarter = crsr.getString(col2);
                String Subject = crsr.getString(col3);
                String Grade = crsr.getString(col4);

                tmp = "" + Key + ". " + Name + ", " + Quarter + ", " + Subject + ", " + Grade;
                ArrayGrades.add(tmp);

            crsr.moveToNext();
        }
        db.close();
        adp = new ArrayAdapter<String>(this,  R.layout.support_simple_spinner_dropdown_item, ArrayGrades);
        listView.setAdapter(adp);
    }

    /**
     * convert the clicked item in the listView to not Active, and delete him from the listView.
     * @param view
     */
    public void Delete(View view) {
                if(itemDelete != -1 && !Students.isEmpty() && Type == 1 ) {
                    String tmp = Students.get(itemDelete);
                    Students.remove(itemDelete);
                    ContentValues cv = new ContentValues();
                    int i = 0;
                    String key = "";

                    while (tmp.charAt(i) != '.') {
                        key += tmp.charAt(i);
                        i++;
                    }

                    String[] selectionArgs = {key};
                    cv.put(Student.isActive, 0);
                    db = hlp.getWritableDatabase();
                    db.update(TABLE_STUDENTS, cv, KEY_ID + " = ?", selectionArgs);
                    db.close();
                } else if(Type == 2 && itemDelete != -1 && !ArrayGrades.isEmpty() ){
                    String tmp = ArrayGrades.get(itemDelete);
                    ArrayGrades.remove(itemDelete);
                    ContentValues cv = new ContentValues();
                    String key = "";
            int i = 0;
            while (tmp.charAt(i) != '.') {
                key += tmp.charAt(i);
                i++;
            }

            String[] selectionArgs = {key};
            cv.put(Grades.isActive, 0);
            db = hlp.getWritableDatabase();
            db.update(TABLE_GRADES, cv, Grades.KEY_ID + " = ?", selectionArgs);
            db.close();
        }
        adp.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.Main){
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
            return true;
        }else if (id == R.id.Sort){
            Intent si = new Intent(this, Sort.class);
            startActivity(si);
            return true;
        }
        return true;
    }
}
