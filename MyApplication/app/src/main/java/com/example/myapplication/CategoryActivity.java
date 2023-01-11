package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoryActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usercategory);
        mDatabase =FirebaseDatabase.getInstance().getReference();
        final CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox1);
        final CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox2);
        final CheckBox cb3 = (CheckBox) findViewById(R.id.checkBox3);
        final CheckBox cb4 = (CheckBox) findViewById(R.id.checkBox4);
        final CheckBox cb5 = (CheckBox) findViewById(R.id.checkBox5);
        final CheckBox cb6 = (CheckBox) findViewById(R.id.checkBox6);
        final CheckBox cb7 = (CheckBox) findViewById(R.id.checkBox7);
        final CheckBox cb8 = (CheckBox) findViewById(R.id.checkBox8);


        Button b = (Button) findViewById(R.id.button1);

        Intent intent_last = getIntent();
        String user_id = intent_last.getStringExtra("user_id_last");
        System.out.println(user_id);


        //String user_id = mAuth.getUid();

        UserCategoryModel userCategoryModel = new UserCategoryModel();


        Intent intent = new Intent(this, HomeActivity.class);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                userCategoryModel.checkbox_1 = cb1.isChecked();
                userCategoryModel.checkbox_2 = cb2.isChecked();
                userCategoryModel.checkbox_3 = cb3.isChecked();
                userCategoryModel.checkbox_4 = cb4.isChecked();
                userCategoryModel.checkbox_5 = cb5.isChecked();
                userCategoryModel.checkbox_6 = cb6.isChecked();
                userCategoryModel.checkbox_7 = cb7.isChecked();
                userCategoryModel.checkbox_8 = cb8.isChecked();
                userCategoryModel.location = "위치 등록";
                userCategoryModel.point = "0";


                // 게시글 내용 저장

                mDatabase.child("User").child(user_id).child("Category")
                        .setValue(userCategoryModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
                startActivity(intent);
                finish();

            } // end onClick

        }); // end setOnClickListener




    } // end onCreate()


}