package com.example.myapplication.ui.dash_page.page1.page51;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.dash_page.page1.d_RecyclerViewAdapter;
import com.example.myapplication.ui.dash_page.page1.d_RecyclerViewModel;
import com.example.myapplication.ui.page.page1.page1_0;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link page1_0#newInstance} factory method to
 * create an instance of this fragment.
 */
public class d_page51_4 extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<d_RecyclerViewModel> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public d_page51_4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment page1.
     */
    // TODO: Rename and change types and number of parameters
    public static d_page51_4 newInstance(String param1, String param2) {
        d_page51_4 fragment = new d_page51_4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.d_page2, container, false);


        recyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerView_dashlist); //아디연결
        recyclerView.setHasFixedSize(true);  //성능강화

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("CV");
        databaseReference.orderByChild("study_category_low").equalTo("유통/물류/재고").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                for(DataSnapshot snapshot1 : snapshot.getChildren()){ //반복문으로 데이터 리스트 추출
                    d_RecyclerViewModel recylcerViewModel = snapshot1.getValue(d_RecyclerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                    arrayList.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        adapter = new d_RecyclerViewAdapter(arrayList, getActivity());
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어뎁터 연결






        return rootview;


    }
}