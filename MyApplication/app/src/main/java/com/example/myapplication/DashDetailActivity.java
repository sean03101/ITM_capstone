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

public class DashDetailActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_detail);

        intent = getIntent();// 인텐트 받아오기
        received_key = intent.getStringExtra("Study Primary Key2"); //Adapter에서 받은 키값 연결

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference();


        Intent intent = getIntent();
        String myData = intent.getStringExtra("sibal");

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

        arrayList = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)
        arrayList2 = new ArrayList<>();

        mDatabase.child("CV").orderByChild("study_primary").equalTo(received_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    study_primary_key = snapshot1.getKey();

                    AddCVModel group = snapshot1.getValue(AddCVModel.class);
                    String study_category_high = group.study_category_high;
                    String study_category_row = group.study_category_low;
                    String study_company = group.study_company;
                    String study_explain = group.study_explain;
                    String study_explain1 = group.study_explain1;
                    String study_name = group.study_name;

                    name.setText(study_name);
                    company.setText(study_category_high);
                    study_category.setText(study_category_row);
                    company2.setText(study_company);
                    study_info1.setText(study_explain);
                    study_info2.setText(study_explain1);
                }






            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}