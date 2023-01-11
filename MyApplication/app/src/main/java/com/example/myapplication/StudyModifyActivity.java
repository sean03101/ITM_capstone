package com.example.myapplication;

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
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class StudyModifyActivity extends AppCompatActivity {

    private Intent intent123; //인텐트 선언
    private String study_primary_key;


    private TextView et_name;
    private TextView tv_company;
    private TextView tv_studycafe;

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

    String study_list_whole;
    String[] study_list_split;

    String study_master;

    String study_masterID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    Uri file;
    String study_photo;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        intent123 = getIntent();// 인텐트 받아오기
        ArrayList arrayList = (ArrayList) intent123.getSerializableExtra("information list");
        study_primary_key = String.valueOf(arrayList.get(1));
        System.out.println("qaz" + study_primary_key);

        imageView = findViewById(R.id.iv_cover_image);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

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
                    adspin2 = ArrayAdapter.createFromResource(StudyModifyActivity.this, R.array.spinner_do_manage, android.R.layout.simple_spinner_dropdown_item);

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
                    adspin2 = ArrayAdapter.createFromResource(StudyModifyActivity.this, R.array.spinner_do_marketing, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(StudyModifyActivity.this, R.array.spinner_do_it, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(StudyModifyActivity.this, R.array.spinner_do_design, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(StudyModifyActivity.this, R.array.spinner_do_service, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(StudyModifyActivity.this, R.array.spinner_do_trade, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(StudyModifyActivity.this, R.array.spinner_do_search, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(StudyModifyActivity.this, R.array.spinner_do_construction, android.R.layout.simple_spinner_dropdown_item);
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

        btn_make = findViewById(R.id.btn_make);

        study_list_whole = arrayList.get(0).toString();
        study_list_split = study_list_whole.split(", |=");

        for (int i = 0; i < study_list_split.length; i++) {
            System.out.println("123results[" + i + "] = " + study_list_split[i]);
        }

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child("Study Images/" + study_list_split[17] + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplication()).load(uri).into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });

        et_name.setText(study_list_split[1]);
        tv_company.setText(study_list_split[7]);
        tv_studycafe.setText(study_list_split[25]);

        System.out.println("cxzcxzzc" + study_list_split[5].getClass().getName());

        if (study_list_split[10].equals("true")) {
            switch_add.setChecked(true);
        } else {
            switch_add.setChecked(false);
        }

        if (study_list_split[5].equals("true")) {
            switch_onoff.setChecked(true);
        } else {
            switch_onoff.setChecked(false);
        }

        if (study_list_split[3].equals("true")) {
            switch_local.setChecked(true);
        } else {
            switch_local.setChecked(false);
        }

        et_description.setText(study_list_split[15]);
        tv_peopleNumber.setText(study_list_split[13]);

        mDatabase = FirebaseDatabase.getInstance().getReference();

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
                String[] intent_temp = new String[2];
                intent_temp[0] = study_primary_key;
                intent_temp[1] = study_list_split[1];
                Intent intent11 = new Intent(StudyModifyActivity.this, ParsingModify.class);
                intent11.putExtra("study primary key", intent_temp);
                v.getContext().startActivity(intent11);
                startActivity(intent11);
                finish();
            }
        });
        Button btn_study_cafe = findViewById(R.id.tv_studycafe);
        btn_study_cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] intent_temp = new String[2];
                intent_temp[0] = study_primary_key;
                intent_temp[1] = study_list_split[1];
                Intent intent12 = new Intent(StudyModifyActivity.this, MapModify.class);
                intent12.putExtra("study primary key", intent_temp);
                System.out.println("sii" + study_primary_key);
                v.getContext().startActivity(intent12);
                startActivity(intent12);
                finish();
            }
        });


        btn_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String study_name = et_name.getText().toString();
                String study_category_high = choice_do;
                String study_category_low = choice_se;

                String study_company = tv_company.getText().toString();
                String study_studycafe = tv_studycafe.getText().toString();
                String study_number = tv_peopleNumber.getText().toString();

                boolean study_isadd = switch_add.isChecked();
                boolean study_isonoff = switch_onoff.isChecked();
                boolean study_islocal = switch_local.isChecked();

                String study_explain = et_description.getText().toString();

                if(file!=null){
                    StorageReference imagesRef = storageRef.child("Study Images/"+study_primary_key+".jpg");
                    UploadTask uploadTask = imagesRef.putFile(file);
                    study_photo = study_primary_key;
                }
                else{
                    study_photo = "default";
                }

                Map<String, Object> modify_study = new HashMap<>();

                modify_study.put("study_name", study_name);
                modify_study.put("study_category_high", study_category_high);
                modify_study.put("study_category_low", study_category_low);
                modify_study.put("study_company", study_company);
                modify_study.put("study_studycafe", study_studycafe);
                modify_study.put("study_number", study_number);
                modify_study.put("study_isadd", study_isadd);
                modify_study.put("study_isonoff", study_isonoff);
                modify_study.put("study_islocal", study_islocal);
                modify_study.put("study_explain", study_explain);

                modify_study.put("study_photo", study_photo);

                mDatabase.child("Study").child(study_primary_key).updateChildren(modify_study).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Intent intent = new Intent(StudyModifyActivity.this, HomeActivity.class);
                        Toast.makeText(StudyModifyActivity.this, "수정이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();

                    }
                });

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
                    file = data.getData();;
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

}