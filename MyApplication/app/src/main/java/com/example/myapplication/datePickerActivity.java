package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class datePickerActivity extends AppCompatActivity {

    private int mYear = 0, mMonth = 0, mDay = 0;

    String study_date;
    private DatabaseReference mDatabase;
    String study_primary_key;
    EditText edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_date_picker);

        edit1 = findViewById(R.id.edit1);

        Calendar calendar = new GregorianCalendar();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        System.out.println("bnm"+mMonth);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePicker datePicker = findViewById(R.id.vDatePicker);

        datePicker.init(mYear, mMonth, mDay, mOnDateChangedListener);

        System.out.println("bnm"+mMonth);
        Intent intent = getIntent();// 인텐트 받아오기
        study_primary_key = intent.getStringExtra("Study Primary Key");

        System.out.println(study_primary_key);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }


    public void mOnClick(View v) {

        study_date =  Integer.toString(mYear) + "-" + (Integer.toString(mMonth+1)) + "-" + Integer.toString(mDay);

        System.out.println(study_date);
        mDatabase.child("DateList").child(study_primary_key).child(study_date).setValue(edit1.getText().toString());
        Toast.makeText(datePickerActivity.this, "일정 추가", Toast.LENGTH_LONG).show();

        Intent intent_datepicker2 = new Intent(this, StudydetailActivity.class);
        intent_datepicker2.putExtra("Study Primary Key", study_primary_key);
        startActivity(intent_datepicker2);
        finish();

    }


    DatePicker.OnDateChangedListener mOnDateChangedListener = new DatePicker.OnDateChangedListener() {

        @Override
        public void onDateChanged(DatePicker datePicker, int yy, int mm, int dd) {
            mYear = yy;
            mMonth = mm;
            mDay = dd;
        }
    };
}