package com.example.myapplication.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CategoryActivity;
import com.example.myapplication.Chat;
import com.example.myapplication.GroupInfoActivity;
import com.example.myapplication.HomeActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SearchStudyActivity;
import com.example.myapplication.UserCategoryModel;
import com.example.myapplication.UserInfoModel;
import com.example.myapplication.UseridData;
import com.example.myapplication.ui.page.RecyclerViewAdapter;
import com.example.myapplication.ui.page.RecylcerViewModel;
import com.example.myapplication.ui.viewpage.viewpage1;
import com.example.myapplication.ui.viewpage.viewpage2;
import com.example.myapplication.ui.viewpage.viewpage3;
import com.example.myapplication.ui.viewpage.viewpage4;
import com.example.myapplication.ui.viewpage.viewpage5;
import com.example.myapplication.ui.viewpage.viewpage6;
import com.example.myapplication.ui.viewpage.viewpage7;
import com.example.myapplication.ui.viewpage.viewpage8;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FragmentHome extends Fragment {
    //객체 선언
    private Button button2;
    private TextView textView1;
    private Spinner spinner1;
    private static final String[] search_keyword = new String[]{"▼","스터디 제목", "회사", "스터디 카페","스터디 위치"};
    private Button search_study_button;
    private EditText search_study_edittext;
    private ArrayList<UserCategoryModel> categoryList;
    private DatabaseReference mDatabase;
    private String user_id = "";
    private RecyclerView recyclerView, recyclerView1, recyclerView2, recyclerView3, recyclerView4, recyclerView5, recyclerView6, recyclerView7;
    private RecyclerView.Adapter adapter, adapter1, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7;
    private ArrayList<RecylcerViewModel> arrayList, arrayList1, arrayList2, arrayList3, arrayList4, arrayList5, arrayList6, arrayList7;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    HomeActivity activity;

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        activity =(HomeActivity) getActivity();
    }

    @Override
    public void onDetach() {

        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragement_home, container, false);
        //XML, java 연결
        //XML이 메인에 직접 붙으면 true, 프래그먼트에 붙으면 false

        mDatabase = FirebaseDatabase.getInstance().getReference();

        UserCategoryModel userCategoryModel = new UserCategoryModel();


        user_id = UseridData.getInstance().getUser_id();
        mDatabase.child("User").child(user_id).child("Category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int a = 1;
                switch (a){
                    case 1 :
                        if(String.valueOf(dataSnapshot.child("checkbox_1").getValue()) == "true"){
                            recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_studylist); //아디연결
                            recyclerView.setHasFixedSize(true);  //성능강화

                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                            arrayList = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)
                            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));

                            database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
                            databaseReference = database.getReference("Study");
                            databaseReference.orderByChild("study_category_high").equalTo("경영/사무").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                                    arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                                    for(DataSnapshot snapshot1 : snapshot.getChildren()){ //반복문으로 데이터 리스트 추출
                                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                                        arrayList.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                                    }
                                    adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침


                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });

                            adapter = new RecyclerViewAdapter(arrayList, getActivity());
                            recyclerView.setAdapter(adapter); //리사이클러뷰에 어뎁터 연결

                        }
                    case 2 :
                        if(String.valueOf(dataSnapshot.child("checkbox_2").getValue()) == "true"){
                            recyclerView1 = (RecyclerView) v.findViewById(R.id.recyclerView_studylist1); //아디연결
                            recyclerView1.setHasFixedSize(true);  //성능강화

                            recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
                            arrayList1 = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)

                            database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
                            databaseReference = database.getReference("Study");
                            databaseReference.orderByChild("study_category_high").equalTo("마케팅/광고/홍보").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                                    arrayList1.clear(); //기존 배열리스트가 존재하지 않게 초기화
                                    for(DataSnapshot snapshot1 : snapshot.getChildren()){ //반복문으로 데이터 리스트 추출
                                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                                        arrayList1.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                                    }
                                    adapter1.notifyDataSetChanged(); //리스트 저장 및 새로고침


                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });

                            adapter1 = new RecyclerViewAdapter(arrayList1, getActivity());
                            recyclerView1.setAdapter(adapter1); //리사이클러뷰에 어뎁터 연결
                        }

                    case 3:
                        if(String.valueOf(dataSnapshot.child("checkbox_3").getValue()) == "true"){
                            recyclerView2 = (RecyclerView) v.findViewById(R.id.recyclerView_studylist2); //아디연결
                            recyclerView2.setHasFixedSize(true);  //성능강화

                            recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
                            arrayList2 = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)

                            database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
                            databaseReference = database.getReference("Study");
                            databaseReference.orderByChild("study_category_high").equalTo("IT/디지털").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                                    arrayList2.clear(); //기존 배열리스트가 존재하지 않게 초기화
                                    for(DataSnapshot snapshot1 : snapshot.getChildren()){ //반복문으로 데이터 리스트 추출
                                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                                        arrayList2.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                                    }
                                    adapter2.notifyDataSetChanged(); //리스트 저장 및 새로고침


                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });

                            adapter2 = new RecyclerViewAdapter(arrayList2, getActivity());
                            recyclerView2.setAdapter(adapter2); //리사이클러뷰에 어뎁터 연결

                        }
                    case 4:
                        if(String.valueOf(dataSnapshot.child("checkbox_4").getValue()) == "true"){
                            recyclerView3 = (RecyclerView) v.findViewById(R.id.recyclerView_studylist3); //아디연결
                            recyclerView3.setHasFixedSize(true);  //성능강화

                            recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));
                            arrayList3 = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)

                            database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
                            databaseReference = database.getReference("Study");
                            databaseReference.orderByChild("study_category_high").equalTo("디자인").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                                    arrayList3.clear(); //기존 배열리스트가 존재하지 않게 초기화
                                    for(DataSnapshot snapshot1 : snapshot.getChildren()){ //반복문으로 데이터 리스트 추출
                                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                                        arrayList3.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                                    }
                                    adapter3.notifyDataSetChanged(); //리스트 저장 및 새로고침


                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });

                            adapter3 = new RecyclerViewAdapter(arrayList3, getActivity());
                            recyclerView3.setAdapter(adapter3); //리사이클러뷰에 어뎁터 연결

                        }
                    case 5:
                        if(String.valueOf(dataSnapshot.child("checkbox_5").getValue()) == "true"){
                            recyclerView4 = (RecyclerView) v.findViewById(R.id.recyclerView_studylist4); //아디연결
                            recyclerView4.setHasFixedSize(true);  //성능강화

                            recyclerView4.setLayoutManager(new LinearLayoutManager(getActivity()));
                            arrayList4 = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)

                            database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
                            databaseReference = database.getReference("Study");
                            databaseReference.orderByChild("study_category_high").equalTo("무역유통").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                                    arrayList4.clear(); //기존 배열리스트가 존재하지 않게 초기화
                                    for(DataSnapshot snapshot1 : snapshot.getChildren()){ //반복문으로 데이터 리스트 추출
                                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                                        arrayList4.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                                    }
                                    adapter4.notifyDataSetChanged(); //리스트 저장 및 새로고침


                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });

                            adapter4 = new RecyclerViewAdapter(arrayList4, getActivity());
                            recyclerView4.setAdapter(adapter4); //리사이클러뷰에 어뎁터 연결
                        }
                    case 6:
                        if(String.valueOf(dataSnapshot.child("checkbox_6").getValue()) == "true"){
                            recyclerView5 = (RecyclerView) v.findViewById(R.id.recyclerView_studylist5); //아디연결
                            recyclerView5.setHasFixedSize(true);  //성능강화

                            recyclerView5.setLayoutManager(new LinearLayoutManager(getActivity()));
                            arrayList5 = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)

                            database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
                            databaseReference = database.getReference("Study");
                            databaseReference.orderByChild("study_category_high").equalTo("영업/고객/서비스").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                                    arrayList5.clear(); //기존 배열리스트가 존재하지 않게 초기화
                                    for(DataSnapshot snapshot1 : snapshot.getChildren()){ //반복문으로 데이터 리스트 추출
                                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                                        arrayList5.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                                    }
                                    adapter5.notifyDataSetChanged(); //리스트 저장 및 새로고침


                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });

                            adapter5 = new RecyclerViewAdapter(arrayList5, getActivity());
                            recyclerView5.setAdapter(adapter5); //리사이클러뷰에 어뎁터 연결
                        }
                    case 7:
                        if(String.valueOf(dataSnapshot.child("checkbox_7").getValue()) == "true"){
                            recyclerView6 = (RecyclerView) v.findViewById(R.id.recyclerView_studylist6); //아디연결
                            recyclerView6.setHasFixedSize(true);  //성능강화

                            recyclerView6.setLayoutManager(new LinearLayoutManager(getActivity()));
                            arrayList6 = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)

                            database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
                            databaseReference = database.getReference("Study");
                            databaseReference.orderByChild("study_category_high").equalTo("연구개발/설계").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                                    arrayList6.clear(); //기존 배열리스트가 존재하지 않게 초기화
                                    for(DataSnapshot snapshot1 : snapshot.getChildren()){ //반복문으로 데이터 리스트 추출
                                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                                        arrayList6.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                                    }
                                    adapter6.notifyDataSetChanged(); //리스트 저장 및 새로고침


                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });

                            adapter6 = new RecyclerViewAdapter(arrayList6, getActivity());
                            recyclerView6.setAdapter(adapter6); //리사이클러뷰에 어뎁터 연결
                        }
                    case 8:
                        if(String.valueOf(dataSnapshot.child("checkbox_8").getValue()) == "true"){
                            recyclerView7 = (RecyclerView) v.findViewById(R.id.recyclerView_studylist7); //아디연결
                            recyclerView7.setHasFixedSize(true);  //성능강화

                            recyclerView7.setLayoutManager(new LinearLayoutManager(getActivity()));
                            arrayList7 = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)

                            database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
                            databaseReference = database.getReference("Study");
                            databaseReference.orderByChild("study_category_high").equalTo("건설/토목").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                                    arrayList7.clear(); //기존 배열리스트가 존재하지 않게 초기화
                                    for(DataSnapshot snapshot1 : snapshot.getChildren()){ //반복문으로 데이터 리스트 추출
                                        RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                                        arrayList7.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                                    }
                                    adapter7.notifyDataSetChanged(); //리스트 저장 및 새로고침


                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });

                            adapter7 = new RecyclerViewAdapter(arrayList7, getActivity());
                            recyclerView7.setAdapter(adapter7); //리사이클러뷰에 어뎁터 연결
                        }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        AdView mAdView;
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { } });


        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Button button1, button2, button3, button4, button5, button6, button7, button8, mypage;


        button1 = (Button) v.findViewById(R.id.button1);
        button2 = (Button) v.findViewById(R.id.button2);
        button3 = (Button) v.findViewById(R.id.button3);
        button4 = (Button) v.findViewById(R.id.button4);
        button5 = (Button) v.findViewById(R.id.button5);
        button6 = (Button) v.findViewById(R.id.button6);
        button7 = (Button) v.findViewById(R.id.button7);
        button8 = (Button) v.findViewById(R.id.button8);
        mypage = (Button) v.findViewById(R.id.mypage);
        Button btn;
        TextView view;
        btn = (Button) v.findViewById(R.id.buttnString);
        view = (TextView) v.findViewById(R.id.location);


        search_study_edittext = v.findViewById(R.id.search_study_edittext);

        search_study_button = (Button)v.findViewById(R.id.search_study_button);







        spinner1 = (Spinner) v.findViewById(R.id.spinner_search);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, search_keyword);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),Integer.toString(position),Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        search_study_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_search = new Intent(getActivity(), SearchStudyActivity.class);
                String temp = spinner1.getSelectedItem().toString() +"-"+ search_study_edittext.getText().toString();
                System.out.println(temp);
                intent_search.putExtra("search_study", temp);
                startActivity(intent_search);


            }
        });

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button1:
                        Intent intent = new Intent(getActivity(), viewpage1.class);
                        startActivity(intent);
                        break;

                    case R.id.button2:
                        Intent intent2 = new Intent(getActivity(), viewpage2.class);
                        startActivity(intent2);
                        break;

                    case R.id.button3:
                        Intent intent3 = new Intent(getActivity(), viewpage3.class);
                        startActivity(intent3);
                        break;
                    case R.id.button4:
                        Intent intent4 = new Intent(getActivity(), viewpage4.class);
                        startActivity(intent4);
                        break;
                    case R.id.button5:
                        Intent intent5 = new Intent(getActivity(), viewpage5.class);
                        startActivity(intent5);
                        break;
                    case R.id.button6:
                        Intent intent6 = new Intent(getActivity(), viewpage6.class);
                        startActivity(intent6);
                        break;
                    case R.id.button7:
                        Intent intent7 = new Intent(getActivity(), viewpage7.class);
                        startActivity(intent7);
                        break;
                    case R.id.button8:
                        Intent intent8 = new Intent(getActivity(), viewpage8.class);
                        startActivity(intent8);
                        break;
                    case R.id.mypage:
                        Intent intent9 = new Intent(getActivity(), com.example.myapplication.mypage.mypage.class);
                        startActivity(intent9);
                        break;


                }

            }
        };

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        mypage.setOnClickListener(onClickListener);


        return v;
    }

    private void setInit() {


    }


}