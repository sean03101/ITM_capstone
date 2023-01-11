package com.example.myapplication.mypage;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.UseridData;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class Point extends AppCompatActivity {


    private static final int RC_SIGN_IN = 1000;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private DatabaseReference mDatabase;
    Button button11, button12;
    TextView view;
    String user_id;
    private ArrayList arrayList;
    private String user_point;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.point);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("User");

        view = findViewById(R.id.textView14);

        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);

        user_id = UseridData.getInstance().getUser_id();

        System.out.println("qwerasdf" + user_id);
        arrayList = new ArrayList<>();

        mDatabase.child(user_id).child("Category").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    arrayList.add(snapshot1.getValue());
                }

                Iterator iter = arrayList.iterator(); //Iterator 선언
                while(iter.hasNext()) {//다음값이 있는지 체크
                    System.out.print("zxcv");
                    System.out.println(iter.next()); //값 출력
                }

                System.out.println(arrayList.get(9));
                System.out.println(arrayList.get(9).getClass().getName());
                user_point = (String) arrayList.get(9);
                view.setText(user_point + " 포인트");

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent11 = new Intent(Point.this, charge.class);
                intent11.putExtra("User point", user_point);
                startActivity(intent11);
            }
        });


        button12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent12 = new Intent(Point.this, refund.class);
                intent12.putExtra("User point", user_point);
                startActivity(intent12);
            }
        });


    }
}