package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    HelperDB hlp;
    SQLiteDatabase db;
    ContentValues cv = new ContentValues();
    int x = 0;

    EditText Name,Address, Phone_Num, Home_Num,Father,Mother,Mom_Num, Dad_Num;

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

    }

    public void SB(View view) {
        String st;
        st = Name.getText().toString();
        if (st.indexOf(0) < 'A' && st.indexOf(0) > 'Z' && st != null) {
            for (int i = 0; i < st.length(); i++) {
                if (st.indexOf(i) < 'a' && st.indexOf(i) > 'z') {
                    Name.setError("This name cant be use. Please enter a new name");
                }
            }
        } else{
            Name.setError("This name is not possible");
        }
    }
}
