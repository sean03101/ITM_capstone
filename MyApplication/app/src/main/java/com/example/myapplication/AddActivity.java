package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AddActivity extends AppCompatActivity {

    private boolean name_checked=false;

    private TextView et_name;
    private TextView tv_company;
    private TextView tv_studycafe;

    Intent previous_intent;


    private static final int REQUEST_CODE = 0;
    private ImageView imageView;

    ArrayAdapter<CharSequence> adspin1, adspin2; //어댑터를 선언했습니다. adspint1(서울,인천..) adspin2(강남구,강서구..)
    String choice_do = "";
    String choice_se = "";


    private ImageView btn_minus, btn_plus;
    private TextView tv_peopleNumber;

    private int count_user_num = 0;


    private Switch switch_add;
    private Switch switch_onoff;
    private Switch switch_local;

    private TextView et_description;

    private Button btn_make;
    private Button intent_company_btn;
    private Button intent_studycafe_btn;
    private Button check_study_name;


    private DatabaseReference mDatabase;

    String study_name;
    String study_category_high;
    String study_category_low;

    String study_company;
    String study_studycafe;
    String study_number;

    boolean study_isadd;
    boolean study_isonoff;
    boolean study_islocal;

    String study_explain;
    String study_master;

    String study_masterID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String getString = UserLocationData.getInstance().getUser_location();
    String [] getstring_list = getString.split("\\s");
    String study_location = getstring_list[2] + " " + getstring_list[3];
    //String study_location = "마포구 상수동";

    Uri file;
    String study_photo;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();





    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        imageView = findViewById(R.id.iv_cover_image);
        System.out.println("uuo" + UseridData.getInstance().getUser_id());


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        TextView view;
        TextView view2;


        String getString = getIntent().getStringExtra("passData2");
        String getString2 = getIntent().getStringExtra("passData3");


        final Spinner spin1 = (Spinner) findViewById(R.id.spinner);
        final Spinner spin2 = (Spinner) findViewById(R.id.spinner2);


        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adspin1);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adspin1.getItem(i).equals("경영/사무")) {

                    choice_do = "경영/사무";//버튼 클릭시 출력을 위해 값을 넣었습니다.
                    adspin2 = ArrayAdapter.createFromResource(AddActivity.this, R.array.spinner_do_manage, android.R.layout.simple_spinner_dropdown_item);

                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);

                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else if (adspin1.getItem(i).equals("마케팅/광고/홍보")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "마케팅/광고/홍보";
                    adspin2 = ArrayAdapter.createFromResource(AddActivity.this, R.array.spinner_do_marketing, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("IT/디지털")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "IT/디지털";
                    adspin2 = ArrayAdapter.createFromResource(AddActivity.this, R.array.spinner_do_it, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("디자인")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "디자인";
                    adspin2 = ArrayAdapter.createFromResource(AddActivity.this, R.array.spinner_do_design, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("영업/고객/서비스")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "영업/고객/서비스";
                    adspin2 = ArrayAdapter.createFromResource(AddActivity.this, R.array.spinner_do_service, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("무역유통")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "무역유통";
                    adspin2 = ArrayAdapter.createFromResource(AddActivity.this, R.array.spinner_do_trade, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("연구개발/설계")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "연구개발/설계";
                    adspin2 = ArrayAdapter.createFromResource(AddActivity.this, R.array.spinner_do_search, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else if (adspin1.getItem(i).equals("건설/토목")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "건설/토목";
                    adspin2 = ArrayAdapter.createFromResource(AddActivity.this, R.array.spinner_do_construction, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tv_peopleNumber = findViewById(R.id.tv_peopleNumber);

        btn_minus = findViewById(R.id.btn_minus);
        btn_plus = findViewById(R.id.btn_plus);

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count_user_num == 1) {
                    count_user_num = 1;
                } else {
                    count_user_num--;
                }
                tv_peopleNumber.setText(count_user_num + "");
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_user_num++;
                tv_peopleNumber.setText(count_user_num + "");
            }
        });

        et_name = findViewById(R.id.et_name);
        tv_company = findViewById(R.id.study_show_result);
        tv_studycafe = findViewById(R.id.textView5);

        switch_add = findViewById(R.id.switch_add);
        switch_onoff = findViewById(R.id.switch_onoff);
        switch_local = findViewById(R.id.switch_local);

        et_description = findViewById(R.id.et_description);


        previous_intent = getIntent();
        StudyAddData studyAddData_previous = (StudyAddData) previous_intent.getSerializableExtra("Data Object");

        //System.out.println(studyAddData_previous.getStudy_company());

        if (studyAddData_previous != null) {

            et_name.setText(studyAddData_previous.getStudy_name());
            tv_company.setText(studyAddData_previous.getStudy_company());
            tv_studycafe.setText(studyAddData_previous.getStudy_studycafe());

            switch_add.setChecked(studyAddData_previous.isStudy_isadd());
            switch_onoff.setChecked(studyAddData_previous.isStudy_isonoff());
            switch_local.setChecked(studyAddData_previous.isStudy_islocal());

            et_description.setText(studyAddData_previous.getStudy_explain());

            tv_peopleNumber.setText(studyAddData_previous.getStudy_number());


        }

        btn_make = findViewById(R.id.btn_make);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("User").child(UseridData.getInstance().getUser_id()).child("Information").child("user_email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                study_master = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        check_study_name = (Button) findViewById(R.id.check_study_name);
        check_study_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList arrayList_temp = new ArrayList<>();
                study_name = et_name.getText().toString();
                System.out.println("vbn" + study_name.length());
                if (study_name.length() == 0) {
                    Toast.makeText(getApplicationContext(), "스터디 제목을 입력해주세요!", Toast.LENGTH_SHORT).show();//토스메세지 출력
                } else {
                    mDatabase.child("Study").orderByChild("study_name").equalTo(study_name).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            arrayList_temp.clear();
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) { //반복문으로 데이터 리스트 추출
                                RecylcerViewModel recylcerViewModel = snapshot1.getValue(RecylcerViewModel.class); //만들어뒀던 유저 객체에 데이터 담음
                                arrayList_temp.add(recylcerViewModel); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                            }

                            if (arrayList_temp.size() != 0) {
                                Toast.makeText(getApplicationContext(), "이미 존재하는 그룹명입니다.", Toast.LENGTH_SHORT).show();//토스메세지 출력
                            } else {
                                Toast.makeText(getApplicationContext(), "사용하셔도 괜찮은 그룹명입니다.", Toast.LENGTH_SHORT).show();//토스메세지 출력
                                name_checked=true;
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }

            }
        });


        intent_company_btn = (Button) findViewById(R.id.tv_company);
        intent_company_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudyAddData studyAddData = new StudyAddData();

                study_name = et_name.getText().toString();
                study_category_high = choice_do;
                study_category_low = choice_se;

                study_company = tv_company.getText().toString();
                study_studycafe = tv_studycafe.getText().toString();
                study_number = tv_peopleNumber.getText().toString();

                study_isadd = switch_add.isChecked();
                study_isonoff = switch_onoff.isChecked();
                study_islocal = switch_local.isChecked();

                study_explain = et_description.getText().toString();

                studyAddData.setStudy_name(study_name);
                studyAddData.setStudy_category_high(study_category_high);
                studyAddData.setStudy_category_low(study_category_low);

                studyAddData.setStudy_company(study_company);
                studyAddData.setStudy_studycafe(study_studycafe);
                studyAddData.setStudy_number(study_number);

                studyAddData.setStudy_isadd(study_isadd);
                studyAddData.setStudy_isonoff(study_isonoff);
                studyAddData.setStudy_islocal(study_islocal);

                studyAddData.setStudy_explain(study_explain);

                Intent intent11 = new Intent(AddActivity.this, Parsing.class);
                intent11.putExtra("Data Object", studyAddData);
                startActivity(intent11);
            }
        });

        intent_studycafe_btn = (Button) findViewById(R.id.tv_studycafe);
        intent_studycafe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudyAddData studyAddData = new StudyAddData();

                study_name = et_name.getText().toString();
                study_category_high = choice_do;
                study_category_low = choice_se;

                study_company = tv_company.getText().toString();
                study_studycafe = tv_studycafe.getText().toString();
                study_number = tv_peopleNumber.getText().toString();

                study_isadd = switch_add.isChecked();
                study_isonoff = switch_onoff.isChecked();
                study_islocal = switch_local.isChecked();

                study_explain = et_description.getText().toString();

                studyAddData.setStudy_name(study_name);
                studyAddData.setStudy_category_high(study_category_high);
                studyAddData.setStudy_category_low(study_category_low);

                studyAddData.setStudy_company(study_company);
                studyAddData.setStudy_studycafe(study_studycafe);
                studyAddData.setStudy_number(study_number);

                studyAddData.setStudy_isadd(study_isadd);
                studyAddData.setStudy_isonoff(study_isonoff);
                studyAddData.setStudy_islocal(study_islocal);

                studyAddData.setStudy_explain(study_explain);

                Intent intent10 = new Intent(AddActivity.this, Map.class);
                intent10.putExtra("Data Object", studyAddData);
                startActivity(intent10);
            }
        });


        btn_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name_checked==true){
                    study_name = et_name.getText().toString();
                    study_category_high = choice_do;
                    study_category_low = choice_se;

                    study_company = tv_company.getText().toString();
                    study_studycafe = tv_studycafe.getText().toString();
                    study_number = tv_peopleNumber.getText().toString();

                    study_isadd = switch_add.isChecked();
                    study_isonoff = switch_onoff.isChecked();
                    study_islocal = switch_local.isChecked();

                    study_explain = et_description.getText().toString();


                    HashMap new_study = new HashMap<>();

                    new_study.put("study_name", study_name);
                    new_study.put("study_category_high", study_category_high);
                    new_study.put("study_category_low", study_category_low);
                    new_study.put("study_company", study_company);
                    new_study.put("study_studycafe", study_studycafe);
                    new_study.put("study_number", study_number);
                    new_study.put("study_isadd", study_isadd);
                    new_study.put("study_isonoff", study_isonoff);
                    new_study.put("study_islocal", study_islocal);
                    new_study.put("study_explain", study_explain);
                    new_study.put("study_masterID", study_masterID);
                    new_study.put("study_master", study_master);


                    writeNewStudy(study_name, study_category_high, study_category_low, study_company, study_studycafe,
                            study_number, study_isadd, study_isonoff, study_islocal, study_explain);


                }
                else{
                    Toast.makeText(getApplicationContext(), "스터디 이름 중복 체크를 먼저 진행해주세요!.", Toast.LENGTH_SHORT).show();//토스메세지 출력
                }

            }
        });

    }

    private void xml_parse() {
        String TAG = "Parsing";
        InputStream inputStream = getResources().openRawResource(R.raw.corpcode);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        XmlPullParserFactory xmlPullParserFactory = null;
        XmlPullParser xmlPullParser = null;

        try {
            xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(reader);

            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.i(TAG, "xml START");
                        break;
                    case XmlPullParser.START_TAG:
                        Log.i(TAG, "Start TAG:" + xmlPullParser.getName());
                        break;
                    case XmlPullParser.END_TAG:
                        Log.i(TAG, "END TAG :" + xmlPullParser.getName());
                        break;
                    case XmlPullParser.TEXT:
                        Log.i(TAG, "TEXT:" + xmlPullParser.getText());
                        break;
                }
                try {
                    eventType = xmlPullParser.next();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (inputStream != null) inputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();

            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    file = data.getData();
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    imageView.setImageBitmap(img);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void writeNewStudy(String study_name, String study_category_high, String study_category_low, String study_company, String study_studycafe,
                               String study_number, boolean study_isadd, boolean study_isonoff, boolean study_islocal, String study_explain) {
        Random study_primary_key = new Random();
        String study_primary = String.valueOf(study_primary_key.nextInt());

        Intent intent12345 = new Intent(this, CreateGroupActivity.class);

        System.out.println("poi"+file);
        if(file!=null){
            StorageReference imagesRef = storageRef.child("Study Images/"+study_primary+".jpg");
            UploadTask uploadTask = imagesRef.putFile(file);
            study_photo = study_primary;
        }
        else{
            study_photo = "default";
        }


        AddModel addModel = new AddModel(study_name, study_category_high, study_category_low, study_company, study_studycafe,
                study_number, study_isadd, study_isonoff, study_islocal, study_explain, study_primary, "1980-1-1",
                study_masterID ,study_master, study_photo , study_location);

        mDatabase.child("Study").child(study_primary).setValue(addModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddActivity.this, "등록이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                startActivity(intent12345);
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "등록이 실패했습니다", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}