package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Ido Perez
 * @since 2.9.03
 * @version 001
 */
public class MainActivity extends AppCompatActivity {
    HelperDB hlp;
    SQLiteDatabase db;
    ContentValues cv;

    EditText Name,Address, Phone_Num, Home_Num,Father,Mother,Mom_Num, Dad_Num, NameGrade, Quarter, Subject,Grade ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.Name);
        Address = (EditText) findViewById(R.id.Address);
        Phone_Num = (EditText) findViewById(R.id.Phone_Num);
        Home_Num = (EditText) findViewById(R.id.Home_Num);
        Father = (EditText) findViewById(R.id.Father);
        Mother = (EditText) findViewById(R.id.Mother);
        Mom_Num = (EditText) findViewById(R.id.Mom_Num);
        Dad_Num = (EditText) findViewById(R.id.Dad_Num);

        //Grades
        NameGrade = (EditText) findViewById(R.id.NameGrades);
        Quarter = (EditText) findViewById(R.id.Quarter);
        Subject = (EditText) findViewById(R.id.Subject);
        Grade = (EditText) findViewById(R.id.Grade);

    }

    /**
     * tThe method input the editText convert him to String and put the values in the Students table.
     * @param view
     */
    public void SB(View view) {
        String st[] = new String[8];
        st[0] = Name.getText().toString();
        st[1] = Address.getText().toString();
        st[2] = Phone_Num.getText().toString();
        st[3] = Home_Num.getText().toString();
        st[4] = Father.getText().toString();
        st[5] = Mother.getText().toString();
        st[6] = Mom_Num.getText().toString();
        st[7] = Dad_Num.getText().toString();

        cv = new ContentValues();
        cv.put(Student.NAME,st[0]);
        cv.put(Student.ADDRESS,st[1]);
        cv.put(Student.PHONE_NUM,st[2]);
        cv.put(Student.HOME_NUM,st[3]);
        cv.put(Student.FATHER_NAME,st[4]);
        cv.put(Student.MOTHER_NAME,st[5]);
        cv.put(Student.MOM_NUM,st[6]);
        cv.put(Student.DAD_NUM,st[7]);
        cv.put(Student.isActive, 1);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.insert(Student.TABLE_STUDENTS, null, cv);
        db.close();

        Name.setText("Name");
        Address.setText("Address");
        Phone_Num.setText("Phone Num");
        Home_Num.setText("Home Num");
        Father.setText("Father");
        Mother.setText("Mother");
        Mom_Num.setText("Mother Num");
        Dad_Num.setText("Father Num");

    }

    /**
     * The method input the editText convert him to String and put the values in the Grades table.
     * @param view
     */
    public void GB(View view) {
        String st[] = new String[4];
        st[0] = NameGrade.getText().toString();
        st[1] = Quarter.getText().toString();
        st[2] = Subject.getText().toString();
        st[3] = Grade.getText().toString();

        cv = new ContentValues();
        cv.put(Grades.NAME,st[0]);
        cv.put(Grades.Quarter ,st[1]);
        cv.put(Grades.Subject,st[2]);
        cv.put(Grades.GRADE,st[3]);
        cv.put(Grades.isActive,1);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.insert(Grades.TABLE_GRADES, null, cv);
        db.close();

        NameGrade.setText("Name");
        Quarter.setText("Quarter");
        Grade.setText("Grade");
        Subject.setText("Subject");
    }

    /**
     * options menu for moving from activities.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.Infore){
            Intent si = new Intent(this, Infore.class);
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
