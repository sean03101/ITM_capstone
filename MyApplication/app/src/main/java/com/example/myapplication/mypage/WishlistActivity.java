package com.example.myapplication.mypage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ItemTouchHelperCallback;
import com.example.myapplication.ItemTouchHelperListener;
import com.example.myapplication.R;
import com.example.myapplication.UseridData;
import com.example.myapplication.ui.page.RecylcerViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WishlistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RecylcerViewModel> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        recyclerView = findViewById(R.id.recyclerview_wishlist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<RecylcerViewModel>();

        database = FirebaseDatabase.getInstance();
        user_id = UseridData.getInstance().getUser_id();
        System.out.println("ppp" + user_id);
        databaseReference = database.getReference("User");
        databaseReference.child(user_id).child("Information").child("user_study_wishlist").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot2 : snapshot.getChildren()){ //??????????????? ????????? ????????? ??????
                    RecylcerViewModel recylcerViewModel2 = snapshot2.getValue(RecylcerViewModel.class); //??????????????? ?????? ????????? ????????? ??????
                    arrayList.add(recylcerViewModel2); //?????? ??????????????? ?????????????????? ?????? ????????????????????? ?????? ??????
                }
                adapter.notifyDataSetChanged(); //????????? ?????? ??? ????????????
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        adapter = new WishlistAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper;
        helper = new ItemTouchHelper(new ItemTouchHelperCallback((ItemTouchHelperListener) adapter));
        helper.attachToRecyclerView(recyclerView);

    }

}