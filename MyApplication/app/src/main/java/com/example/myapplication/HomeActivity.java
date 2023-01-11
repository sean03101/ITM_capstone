package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.sample.FragmentAdd;
import com.example.myapplication.sample.FragmentAlarm;
import com.example.myapplication.sample.FragmentChat;
import com.example.myapplication.sample.FragmentDash;
import com.example.myapplication.sample.FragmentHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    //프래그먼트 객체 선언
    FragmentHome fragment1;
    FragmentDash fragment2;
    FragmentAdd fragment3;
    FragmentAlarm fragment4;
    FragmentChat fragment5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        Toolbar toolbar23 = findViewById(R.id.toolbar3);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button btn, mypage;
        TextView location_view;

        btn = (Button) findViewById(R.id.buttnString);
        mypage = (Button) findViewById(R.id.mypage);
        location_view = (TextView) findViewById(R.id.location);

        //프래그먼트 객체 초기화
        fragment1 = new FragmentHome();
        fragment2 = new FragmentDash();
        fragment3 = new FragmentAdd();
        fragment4 = new FragmentAlarm();
        fragment5 = new FragmentChat();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent11 = new Intent(HomeActivity.this, Map.class);
                startActivity(intent11);

            }
        });

        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent11 = new Intent(HomeActivity.this, com.example.myapplication.mypage.mypage.class);
                startActivity(intent11);

            }
        });
        String getString = UserLocationData.getInstance().getUser_location();

        if(getString==null) {
            location_view.setText(getString);
        }
        else{
            String [] getstring_list = getString.split("\\s");
            location_view.setText(getstring_list[2] + " "+ getstring_list[3]);
        }





        //화면에 프래그먼트1 출력
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        //바텀 네비게이션 객체 초기화
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //바텀 네비게이션에 이벤트 추가
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //바텀 네비게이션 버튼에 눌렀을때 화면 바뀌는 기능 추가
                //item.getItemId() 아이템의 아이디를 바로 가져옴
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Toast.makeText(HomeActivity.this, "첫 번째 탭", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                        return true; //return true 밑으로는 작동이 되지 않는다.

                    case R.id.navigation_dashboard:
                        Toast.makeText(HomeActivity.this, "두 번째 탭", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                        return true;

                    case R.id.navigation_add:
                        Toast.makeText(HomeActivity.this, "세 번째 탭", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                        return true;
                    case R.id.navigation_notifications:
                        Toast.makeText(HomeActivity.this, "세 번째 탭", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment4).commit();
                        return true;
                    case R.id.navigation_chatting:
                        Intent intent12 = new Intent(HomeActivity.this, Chat.class);
                        startActivity(intent12);
                        return true;
                }
                return false;
            }
        });

    }
}