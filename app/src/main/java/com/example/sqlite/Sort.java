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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.sqlite.Student.KEY_ID;
import static com.example.sqlite.Student.NAME;
import static com.example.sqlite.Student.TABLE_STUDENTS;
import static com.example.sqlite.Student.isActive;

public class Sort extends AppCompatActivity implements AdapterView.OnItemClickListener {
    HelperDB hlp;
    Cursor crsr;
    SQLiteDatabase db;
    ArrayList<String> Students;
    ListView lv;
    ArrayAdapter<String> adp;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        hlp = new HelperDB(this);
        Students = new ArrayList<>();

        lv = (ListView) findViewById(R.id.lv);
        tv = findViewById(R.id.textView4);

        lv.setOnItemClickListener(this);
        lv.setChoiceMode(lv.CHOICE_MODE_SINGLE);

        adp = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, Students);
        lv.setAdapter(adp);
    }

    @Override

    /**
     * convert the clicked item in the listView to Active from not Active.
     * @see ConvertActive
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ConvertActive(position);
    }

    /**
     * The method Convert to Active.
     * @param pos
     */
    private void ConvertActive(int pos){

        String name = Students.get(pos);
        Students.remove(pos);

        ContentValues cv = new ContentValues();

        String[] selectionArgs = {name};
        cv.put(Student.isActive, 1);

        db = hlp.getWritableDatabase();
        db.update(TABLE_STUDENTS, cv, Student.NAME + " = ?", selectionArgs);
        db.close();
        adp.notifyDataSetChanged();

        Toast.makeText(this, name+""+"is now active", Toast.LENGTH_SHORT).show();
    }

    /**
     * reading from the Students table and sorting it by name.
     * @param view
     */
    public void SortByName(View view) {
        String[] columns = {Student.NAME};
        String selection = null;
        String[] selectionArgs = {"1"};
        String groupBy = null;
        String having = null;
        String orderBy;
        String limit = null;

        orderBy = Student.NAME;
        selection = Student.isActive+"=?";

        Students.clear();

        db = hlp.getReadableDatabase();
        crsr = db.query(TABLE_STUDENTS, columns, selection,selectionArgs,groupBy,having, orderBy, limit);
        int col0 = crsr.getColumnIndex(Student.NAME);


        crsr.moveToFirst();
        while (!crsr.isAfterLast()){
            String name = crsr.getString(col0);

            String tmp = "" + name;
            Students.add(tmp);

            crsr.moveToNext();
        }
        crsr.close();
        db.close();
        adp.notifyDataSetChanged();
    }

    /**
     * reading from the Students Table and present only the non Active students.
     * @param view
     */
    public void NotActive(View view) {
        String[] columns = {Student.NAME};
        String selection =  Student.isActive+"=?";
        String[] selectionArgs = {"0"};
        String groupBy = null;
        String having = null;
        String orderBy = Student.NAME;
        String limit = null;

        Students.clear();

        db = hlp.getReadableDatabase();
        crsr = db.query(TABLE_STUDENTS, columns, selection,selectionArgs,groupBy,having, orderBy, limit);
        int col0 = crsr.getColumnIndex(Student.NAME);


        crsr.moveToFirst();
        while (!crsr.isAfterLast()){
            String name = crsr.getString(col0);

            String tmp = "" + name;
            Students.add(tmp);

            crsr.moveToNext();
        }
        crsr.close();
        db.close();

        tv.setText("Press on student to convert active");

        if(!Students.isEmpty())
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
        } else if(id == R.id.Infore){
            Intent si = new Intent(this, Infore.class);
            startActivity(si);
            return true;
        }
        return true;
    }
}
