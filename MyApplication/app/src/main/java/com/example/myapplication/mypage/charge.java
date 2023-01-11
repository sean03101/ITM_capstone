package com.example.myapplication.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.UseridData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class charge extends AppCompatActivity {

    private DatabaseReference mDatabase;
    String user_id;
    private ArrayList arrayList;
    String point_before;
    String result;
    Button button13;
    EditText point_charge;
    private Intent intent_point;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.charge);

        point_charge = findViewById(R.id.editText);
        button13 = (Button) findViewById(R.id.button13);
        user_id = UseridData.getInstance().getUser_id();
        arrayList = new ArrayList<>();

        intent_point = getIntent();
        point_before = intent_point.getExtras().getString("User point");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        System.out.println(point_before);


        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (point_charge.getText().toString().length() == 0) {

                    Toast.makeText(charge.this, "포인트를 입력해주세요!", Toast.LENGTH_LONG).show();

                } else {
                    int temp = Integer.parseInt(point_charge.getText().toString());
                    temp = temp + Integer.parseInt(point_before);
                    result = Integer.toString(temp);
                    Map<String, Object> tempmap = new HashMap<>();
                    System.out.println("User/" + user_id + "/Category/point");
                    tempmap.put("User/" + user_id + "/Category/point_temp", result);
                    System.out.println(result);
                    mDatabase.updateChildren(tempmap);

                    Intent intent = new Intent(charge.this, Point.class);
                    Toast.makeText(charge.this, "충전 검토중입니다.", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }

            }
        });
    }
}