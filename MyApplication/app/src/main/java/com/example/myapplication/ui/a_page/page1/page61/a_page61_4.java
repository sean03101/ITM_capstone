package com.example.myapplication.ui.a_page.page1.page61;

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
import com.example.myapplication.ui.a_page.page1.a_RecyclerViewAdapter;
import com.example.myapplication.ui.a_page.page1.a_RecyclerViewModel;
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
public class a_page61_4 extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<a_RecyclerViewModel> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public a_page61_4() {
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
    public static a_page61_4 newInstance(String param1, String param2) {
        a_page61_4 fragment = new a_page61_4();
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


        recyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerView_dashlist); //????????????
        recyclerView.setHasFixedSize(true);  //????????????

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList = new ArrayList<>(); //??????????????? ????????? ?????? ????????? ????????? (????????? ?????????)
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        database = FirebaseDatabase.getInstance(); //?????????????????? ?????????????????? ??????
        databaseReference = database.getReference("Meeting");
        databaseReference.orderByChild("study_category_low").equalTo("????????????/??????/??????").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                //?????????????????? ????????????????????? ???????????? ???????????? ???
                arrayList.clear(); //?????? ?????????????????? ???????????? ?????? ?????????
                for(DataSnapshot snapshot1 : snapshot.getChildren()){ //??????????????? ????????? ????????? ??????
                    a_RecyclerViewModel recylcerViewModel = snapshot1.getValue(a_RecyclerViewModel.class); //??????????????? ?????? ????????? ????????? ??????
                    arrayList.add(recylcerViewModel); //?????? ??????????????? ?????????????????? ?????? ????????????????????? ?????? ??????
                }
                adapter.notifyDataSetChanged(); //????????? ?????? ??? ????????????


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        adapter = new a_RecyclerViewAdapter(arrayList, getActivity());
        recyclerView.setAdapter(adapter); //????????????????????? ????????? ??????






        return rootview;


    }
}