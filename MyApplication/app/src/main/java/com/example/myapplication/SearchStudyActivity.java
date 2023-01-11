package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.mypage.WishlistAdapter;
import com.example.myapplication.ui.page.RecyclerViewAdapter;
import com.example.myapplication.ui.page.RecylcerViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchStudyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RecylcerViewModel> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    private Intent intent;
    private String received_key;

    // 메인에서 서치할때 받은 분야 검색 파트


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_study);

        intent = getIntent();// 인텐트 받아오기
        received_key = intent.getStringExtra("search_study"); //Adapter에서 받은 키값 연결

        String[] received_key_list = received_key.split("-");

        recyclerView = findViewById(R.id.recycler_search);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<RecylcerViewModel>();

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("Study");

        System.out.println(received_key);
        System.out.println(received_key_list[0].equals("스터디 제목"));

        if (received_key_list[0].equals("회사")) {

            databaseReference.orderByChild("study_company").equalTo(received_key_list[1]).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                    arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) { //반복문으로 데이터 리스트 추출
                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                        arrayList.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                    }
                    adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침


                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(),
                            "검색하신 내용이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            adapter = new RecyclerViewAdapter(arrayList, this);
            recyclerView.setAdapter(adapter);
        } else if (received_key_list[0].equals("스터디 제목")) {
            databaseReference.orderByChild("study_name").equalTo(received_key_list[1]).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                    arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) { //반복문으로 데이터 리스트 추출
                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                        arrayList.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                    }
                    adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침


                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(),
                            "검색하신 내용이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            adapter = new RecyclerViewAdapter(arrayList, this);
            recyclerView.setAdapter(adapter);
        } else if (received_key_list[0].equals("스터디 카페")) {
            databaseReference.orderByChild("study_studycafe").equalTo(received_key_list[1]).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                    arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) { //반복문으로 데이터 리스트 추출
                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                        arrayList.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                    }
                    adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침


                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(),
                            "검색하신 내용이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            adapter = new WishlistAdapter(arrayList, this);
            recyclerView.setAdapter(adapter);
        }

        else if (received_key_list[0].equals("스터디 위치")) {
            databaseReference.orderByChild("study_location").equalTo(received_key_list[1]).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                    arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) { //반복문으로 데이터 리스트 추출
                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                        arrayList.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                    }
                    adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침


                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(),
                            "검색하신 내용이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            adapter = new WishlistAdapter(arrayList, this);
            recyclerView.setAdapter(adapter);
        }


    }
}