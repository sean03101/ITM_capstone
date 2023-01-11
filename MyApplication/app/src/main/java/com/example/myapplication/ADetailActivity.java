package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ADetailActivity extends AppCompatActivity {
    private Intent intent; //인텐트 선언
    private String received_key;

    private ImageView imageView2;
    private TextView name;
    private TextView study_category;
    private TextView study_number;
    private TextView study_info1;
    private TextView study_info2;
    private TextView study_explain;
    private TextView company;
    private TextView company2;
    private TextView islab, select_tv;
    private TextView text1, text2, text3, text4, text5, text6, text7, text8;


    private String study_primary_key;
    private TextView study_master;
    String[] study_list_split;
    String study_list_whole;
    ArrayList arrayList;
    ArrayList arrayList2;
    Button wish_add, datepicker_btn, join_btn;
    private DatabaseReference mDatabase;
    String user_id;
    String user_uid;
    String uid;
    DatabaseReference masterID;

    RecyclerView recyclerView;
    DateAdapter dateadapter;
    SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

    String study_photo_receive;
    private String select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_detail);
        select = PreferenceManager.getString(this, "select");
        intent = getIntent();// 인텐트 받아오기
        received_key = intent.getStringExtra("Study Primary Key3"); //Adapter에서 받은 키값 연결

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference();


        Intent intent = getIntent();
        String myData = intent.getStringExtra("sibal");

        select_tv = findViewById(R.id.select);
        name = (TextView) findViewById(R.id.dashname);
        company = (TextView) findViewById(R.id.textView9);
        company2 = (TextView) findViewById(R.id.textView11);
        study_category = (TextView) findViewById(R.id.textView17);
        study_number = (TextView) findViewById(R.id.textView6);
        study_info1 = (TextView) findViewById(R.id.textView4);
        study_info2 = (TextView) findViewById(R.id.textView2);
        study_explain = (TextView) findViewById(R.id.textView4);
        imageView2 = findViewById(R.id.imageView2);
        study_master = findViewById(R.id.textView8);
        islab = findViewById(R.id.textView5);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        text8 = findViewById(R.id.text8);


        arrayList = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)
        arrayList2 = new ArrayList<>();

        select_tv.setText(select);

        mDatabase.child("Meeting").orderByChild("a_primary").equalTo(received_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    study_primary_key = snapshot1.getKey();

                    AddmeetingModel group = snapshot1.getValue(AddmeetingModel.class);
                    String study_category_high = group.study_category_high;
                    String study_category_row = group.study_category_low;
                    String study_company = group.study_company;
                    String study_explain = group.study_explain;
                    String study_explain1 = group.study_explain2;
                    String study_name = group.a_name;
                    Boolean Study_ishab = group.study_ishab;
                    String study_explain3 = group.study_explain3;
                    String study_explain4 = group.study_explain4;
                    String study_explain5 = group.study_explain5;
                    String study_explain6 = group.study_explain6;
                    String study_explain7 = group.study_explain7;
                    String study_explain8 = group.study_explain8;
                    String study_explain9 = group.study_explain9;
                    String study_explain10 = group.study_explain10;

                    name.setText(study_name);
                    company.setText(study_category_high);
                    study_category.setText(study_category_row);
                    company2.setText(study_company);
                    study_info1.setText(study_explain);
                    study_info2.setText(study_explain1);
                    text1.setText(study_explain3);
                    text2.setText(study_explain4);
                    text3.setText(study_explain5);
                    text4.setText(study_explain6);
                    text5.setText(study_explain7);
                    text6.setText(study_explain8);
                    text7.setText(study_explain9);
                    text8.setText(study_explain10);

                }






            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}