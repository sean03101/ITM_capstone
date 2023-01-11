package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.ui.page.RecylcerViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;


import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.example.myapplication.ParsingModify.TAG;

public class StudydetailActivity extends AppCompatActivity {

    private Intent intent; //인텐트 선언
    private String received_key;

    private ImageView imageView2;
    private TextView study_name;
    private TextView study_category;
    private TextView study_number;
    private TextView study_info1;
    private TextView study_info2;
    private TextView study_explain;
    private TextView location_info;
    private String study_primary_key;
    private TextView study_master;
    String[] study_list_split;
    String study_list_whole;
    ArrayList arrayList;
    ArrayList arrayList2;
    ArrayList arrayList3;
    Button wish_add, datepicker_btn, join_btn;
    private DatabaseReference mDatabase;
    String user_id;
    String user_uid;
    String uid;
    DatabaseReference masterID;

    RecyclerView recyclerView;
    DateAdapter dateadapter;
    SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

    String study_photo_receive;
    String user_location;
    String study_location;
    String isfull;
    String islocal;
    private String groupId;

    public String readDay = null;
    public String str = null;
    public MaterialCalendarView materialCalendarView;
    public Button cha_Btn, del_Btn, save_Btn;
    public TextView diaryTextView, textView2, textView3;
    public EditText contextEditText;
    private ArrayList<UserInfoModel> userList;
    private TextView participantsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studydetail);

        intent = getIntent();// 인텐트 받아오기
        PreferenceManager.getString(this, "Study Primary Key");
        PreferenceManager.getString(this, "Study Photo");
        groupId = PreferenceManager.getString(this, "groupId");
        received_key = PreferenceManager.getString(this, "Study Primary Key"); //Adapter에서 받은 키값 연결
        study_photo_receive = PreferenceManager.getString(this, "Study Photo");
        Log.d("onCreate: ", received_key);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        study_name = (TextView) findViewById(R.id.textView9);
        study_category = (TextView) findViewById(R.id.textView17);
        study_number = (TextView) findViewById(R.id.textView6);
        study_info1 = (TextView) findViewById(R.id.study_company);
        study_info2 = (TextView) findViewById(R.id.online_studycafe);
        study_explain = (TextView) findViewById(R.id.textView4);
        location_info = findViewById(R.id.location_info);
        imageView2 = findViewById(R.id.imageView2);
        study_master = findViewById(R.id.textView8);

        join_btn = findViewById(R.id.button_join);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        masterID = FirebaseDatabase.getInstance().getReference().child("Study").child(received_key).child("study_masterID");

        System.out.println("asdfg" + received_key);

        arrayList = new ArrayList<>(); //스터디모델 객체를 담을 어레이 리스트 (어댑터 쪽으로)
        arrayList2 = new ArrayList<>();
        Log.d("onCreate: ", groupId);
        participantsTv = findViewById(R.id.participantsTv);






        mDatabase.child("Study").orderByChild("study_primary").equalTo(received_key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    arrayList.add(snapshot1.getValue());
                    study_primary_key = snapshot1.getKey();
                }

                Iterator iter = arrayList.iterator(); //Iterator 선언
                while (iter.hasNext()) {//다음값이 있는지 체크
                    System.out.print("zxcv");
                    System.out.println(iter.next()); //값 출력
                }


                study_list_whole = arrayList.get(0).toString();
                study_list_split = study_list_whole.split(", |=");

                for (int i = 0; i < study_list_split.length; i++) {
                    System.out.println("results[" + i + "] = " + study_list_split[i]);
                }

                study_name.setText(study_list_split[1]);
                study_category.setText(study_list_split[27] + " - " + study_list_split[9]);

                study_info1.setText("" + study_list_split[7]); //회사

                if (study_list_split[5].equals("true")) {
                    study_info2.setText("온라인");
                } else {
                    study_info2.setText("오프라인 / " + "스터디 카페 : " + study_list_split[25]); //스터디 위치 & 스터디 카페
                }
                study_explain.setText(study_list_split[15]);
                study_master.setText("방장 : " + "\n" + study_list_split[29].substring(0, study_list_split[29].length() - 0)); //스터디 방장

                if (study_list_split[3].equals("true")) {
                    location_info.setText(study_list_split[21] + "  /  " + "같은 동네만 가능");
                } else {
                    location_info.setText(study_list_split[21] + "  /  " + "전체 지역 가능");
                }

                study_location = study_list_split[21];
                islocal = study_list_split[3];

                Log.d("onDataChange: ", String.valueOf(userList.size()));



            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        userList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Groups");
        ref.child(groupId).child("Participants").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String uid = ""+ds.child("uid").getValue();

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
                    ref.orderByChild("uid").equalTo(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds : dataSnapshot.getChildren()){
                                UserInfoModel userInfoModel = ds.getValue(UserInfoModel.class);

                                userList.add(userInfoModel);
                                Log.d("onDataChange: ", String.valueOf(userList.size()));
                                if (study_list_split[11].equals("true")) {
                                    study_number.setText("스터디 인원 : " + "("+String.valueOf(userList.size())+")/ " + study_list_split[13] + "  \n  " + "모집중");
                                } else {
                                    study_number.setText("스터디 인원 : " + "("+String.valueOf(userList.size())+")/ " + study_list_split[13] + "  \n  " + "모집 완료");
                                }

                                isfull = study_list_split[11];
                            }



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child("Study Images/" + study_photo_receive + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplication()).load(uri).into(imageView2);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });

        Button button_modify = (Button) findViewById(R.id.textView88);
        button_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_uid.equals(uid)) {
                    Intent intent = new Intent(v.getContext(), StudyModifyActivity.class);
                    System.out.println("qweasdzxc" + study_primary_key);
                    System.out.println(study_primary_key.getClass().getName());
                    arrayList.add(study_primary_key);

                    Iterator iter = arrayList.iterator(); //Iterator 선언
                    while (iter.hasNext()) {//다음값이 있는지 체크
                        System.out.print("3232");
                        System.out.println(iter.next()); //값 출력
                    }

                    intent.putExtra("information list", arrayList);
                    v.getContext().startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(StudydetailActivity.this, "스터디장만 수정 가능합니다", Toast.LENGTH_LONG).show();
                }

            }
        });

        Button button_delete = (Button) findViewById(R.id.textView881);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_uid.equals(uid)) {
                    Intent intent_delete = new Intent(v.getContext(), HomeActivity.class);
                    mDatabase.child("Study").child(study_primary_key).removeValue();
                    try {
                        mDatabase.child("DateList").child(study_primary_key).removeValue();
                    } catch (Exception e) {
                    }
                    Toast.makeText(StudydetailActivity.this, "스터디가 삭제되었습니다", Toast.LENGTH_LONG).show();
                    if (!study_photo_receive.equals("default")) {
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        storage.getReference().child("Study Images/" + study_photo_receive + ".jpg").delete();
                    }
                    startActivity(intent_delete);
                    finish();
                } else {
                    Toast.makeText(StudydetailActivity.this, "스터디장만 삭제 가능합니다", Toast.LENGTH_LONG).show();
                }


            }
        });

        user_id = UseridData.getInstance().getUser_id();
        wish_add = findViewById(R.id.button8);


        wish_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_uid.equals(uid)) {
                    Toast.makeText(StudydetailActivity.this, "사용자가 만든 방입니다.", Toast.LENGTH_LONG).show();
                } else {

                    RecylcerViewModel recylcerViewModel = new RecylcerViewModel();

                    recylcerViewModel.setStudy_name(study_list_split[1]);
                    recylcerViewModel.setStudy_explain(study_list_split[15]);
                    recylcerViewModel.setStudy_company(study_list_split[7]);
                    recylcerViewModel.setStudy_studycafe(study_list_split[25]);
                    recylcerViewModel.setStudy_number(study_list_split[13]);
                    recylcerViewModel.setStudy_category_low(study_list_split[9]);
                    recylcerViewModel.setStudy_primary(study_primary_key);
                    recylcerViewModel.setStudy_photo(study_list_split[17]);


                    mDatabase.child("User").child(user_id).child("Information").child("user_study_wishlist")
                            .child(study_primary_key).setValue(recylcerViewModel);
                    Toast.makeText(StudydetailActivity.this, "위시리스트 등록", Toast.LENGTH_LONG).show();
                }
            }
        });


        datepicker_btn = findViewById(R.id.datepicker_btn);
        datepicker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_uid.equals(uid)) {
                    Intent intent_datepicekr = new Intent(StudydetailActivity.this, datePickerActivity.class);
                    intent_datepicekr.putExtra("Study Primary Key", study_primary_key);
                    startActivity(intent_datepicekr);
                    finish();
                } else {
                    Toast.makeText(StudydetailActivity.this, "스터디장만 일정 추가가 가능합니다", Toast.LENGTH_LONG).show();
                }

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_date);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        materialCalendarView = findViewById(R.id.calendarView);
        materialCalendarView.setSelectedDate(CalendarDay.today());


        mDatabase.child("DateList").child(received_key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                dateadapter = new DateAdapter();
                ArrayList<CalendarDay> calendarDayList = new ArrayList<>();

                for (DataSnapshot snapshot3 : snapshot.getChildren()) {

                    dateadapter.setArrayData(snapshot3.getKey() + ":" + snapshot3.getValue() + ":" + study_primary_key+ ":" +"");
                    String[] temp_date_list = snapshot3.getKey().split("-");
                    int temp_year = Integer.parseInt(temp_date_list[0]);
                    int temp_month = Integer.parseInt(temp_date_list[1]);
                    int temp_day = Integer.parseInt(temp_date_list[2]);


                    calendarDayList.add(CalendarDay.from(temp_year, temp_month - 1, temp_day));

                }
                dateadapter.notifyDataSetChanged();

                recyclerView.setAdapter(dateadapter);
                EventDecorator eventDecorator = new EventDecorator(Color.RED, calendarDayList);
                materialCalendarView.addDecorators(eventDecorator);

                ItemTouchHelper helper;
                helper = new ItemTouchHelper(new ItemTouchHelperCallback(dateadapter));
                helper.attachToRecyclerView(recyclerView);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });




        FirebaseDatabase.getInstance().getReference().child("Study").child(received_key).child("study_masterID").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user_uid = snapshot.getValue().toString();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        user_location = UserLocationData.getInstance().getUser_location();
        join_btn.setOnClickListener(new View.OnClickListener() {
                                        List<UserInfoModel> userModels;

                                        @Override
                                        public void onClick(View v) {
                                            if (isfull.equals("true")) {
                                                if ((islocal.equals("true") && !study_location.equals(user_location))) {
                                                    Toast.makeText(StudydetailActivity.this, "지역만 공개된 스터디 입니다.", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    if (user_uid.equals(uid)) {
                                                        Toast.makeText(StudydetailActivity.this, "자신이 만든 방입니다", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        masterID.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                String MID = snapshot.getValue().toString();
                                                                Intent profileIntent = new Intent(StudydetailActivity.this, ProfileActivity.class);
                                                                profileIntent.putExtra("visit_user_id", MID);
                                                                startActivity(profileIntent);

                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });


                                                        Toast.makeText(StudydetailActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();

                                                    }

                                                }
                                            } else {
                                                Toast.makeText(StudydetailActivity.this, "스터디 인원 모집이 마감되었습니다.", Toast.LENGTH_SHORT).show();
                                            }


                                        }
                                    }


        );

    }


}