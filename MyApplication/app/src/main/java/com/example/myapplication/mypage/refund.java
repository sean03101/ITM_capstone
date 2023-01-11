package com.example.myapplication.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.UseridData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class refund extends AppCompatActivity {

    private DatabaseReference mDatabase;
    String user_id;
    private ArrayList arrayList;
    String point_before;
    String result;
    Button button13;
    EditText point_refund;
    private Intent intent_point;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.refund);

        point_refund = findViewById(R.id.editText);
        button13 = (Button) findViewById(R.id.button13);

        user_id = UseridData.getInstance().getUser_id();
        arrayList = new ArrayList<>();

        intent_point = getIntent();
        point_before = intent_point.getExtras().getString("User point");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        button13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (point_refund.getText().toString().length() == 0){
                    Toast.makeText(refund.this, "포인트를 입력해주세요!", Toast.LENGTH_LONG).show();
                }

                else{

                    int temp = Integer.parseInt(point_refund.getText().toString());
                    temp = Integer.parseInt(point_before) - temp;

                    if(temp < 0){
                        Toast.makeText(refund.this, "보유 포인트가 부족합니다. 다시 입력해주세요", Toast.LENGTH_LONG).show();
                    }

                    else{
                        result = Integer.toString(temp);
                        Map<String, Object> tempmap = new HashMap<>();
                        System.out.println("User/" + user_id + "/Category/point");
                        tempmap.put("User/" + user_id + "/Category/point", result);
                        System.out.println(result);
                        mDatabase.updateChildren(tempmap);

                        Intent intent = new Intent(refund.this, Point.class);
                        Toast.makeText(refund.this, "환급이 완료되었습니다.", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }

                }

            }
        });
    }
}
