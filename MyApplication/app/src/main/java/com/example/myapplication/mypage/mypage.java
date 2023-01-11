package com.example.myapplication.mypage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.GroupChatsFragment;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.UseridData;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

public class mypage extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1000;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private DatabaseReference mDatabase;

    private static final int REQUEST_CODE = 0;
    private ImageView imageView;
    private TextView et_name;
    private String user_email;


    GroupChatsFragment GroupChatsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        GroupChatsFragment = new GroupChatsFragment();
        Button button, button4, button6, button7, button2,button5, button1;
        button6 = (Button) findViewById(R.id.button6);
        button4 = (Button) findViewById(R.id.button4);
        button7 = (Button) findViewById(R.id.button7);
        button2 = (Button) findViewById(R.id.button2);
        button5 = (Button) findViewById(R.id.button5);
        button1 = (Button) findViewById(R.id.button1);
        button = (Button) findViewById(R.id.button);
        et_name = (TextView) findViewById(R.id.et_name);

        imageView = findViewById(R.id.iv_cover_image);
        mAuth =FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        URL url = null;
        try {
            url = new URL(user.getPhotoUrl().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Glide.with(getApplication()).load(url).into(imageView);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("User").child(UseridData.getInstance().getUser_id()).child("Information").child("user_email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                user_email = snapshot.getValue(String.class);
                et_name.setText(user_email + "   /   경영/사무");
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.line11,GroupChatsFragment).commit();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(mypage.this, WishlistActivity.class);
                startActivity(intent1);
            }
        });


        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9 = new Intent(mypage.this, Rule.class);
                startActivity(intent9);
            }
        });


        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent13 = new Intent(mypage.this, Point.class);
                startActivity(intent13);
            }
        });

        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent14 = new Intent(mypage.this, notice.class);
                startActivity(intent14);
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("알림", "구글 LOGOUT");
                mAuth.signOut();
                finish();
                Intent intent11 = new Intent(mypage.this, MainActivity.class);
                startActivity(intent11);
                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다", Toast.LENGTH_SHORT).show();
            }
        });


        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button4) {
                    mAuth.signOut();
                    finish();
                    startActivity(new Intent(mypage.this, MainActivity.class));
                }
                //회원탈퇴를 클릭하면 회원정보를 삭제한다. 삭제전에 컨펌창을 하나 띄워야 겠다.
                if (v == button7) {
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(mypage.this);
                    alert_confirm.setMessage("정말 계정을 삭제 할까요?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    user.delete()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(mypage.this, "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
                                                    finish();
                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                }
                                            });
                                }
                            }
                    );
                    alert_confirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(mypage.this, "취소", Toast.LENGTH_LONG).show();
                        }
                    });
                    alert_confirm.show();
                }
            }
        });


    }
}