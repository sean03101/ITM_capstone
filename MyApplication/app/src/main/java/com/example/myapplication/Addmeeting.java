package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MeetAddData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class Addmeeting extends AppCompatActivity {

    private TextView  tv_company;
    Intent previous_intent;
    RadioGroup radioGroup;

    private static final int REQUEST_CODE = 0;


    ArrayAdapter<CharSequence> adspin1, adspin2; //어댑터를 선언했습니다. adspint1(서울,인천..) adspin2(강남구,강서구..)
    String choice_do="";
    String choice_se="";


    private Switch switch_hab;
    private TextView  et_description;
    private TextView  et_description1;
    private TextView et_des2, des22, des3, des33, des4, des44, des5, des55;
    private Button btn_make;
    private TextView et_aname;


    private DatabaseReference mDatabase;
    private String select;
    private Context mContext;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmeeting);

        mContext = this;

        radioGroup = findViewById(R.id.study_type);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton8:
                        select = "인성면접";
                        PreferenceManager.setString(mContext, "select" , select);
                        break;
                    case R.id.radioButton6:
                        select = "PT면접";
                        PreferenceManager.setString(mContext, "select" , select);
                        break;
                    case R.id.radioButton5:
                        select= "토론면접";
                        PreferenceManager.setString(mContext, "select" , select);
                        break;
                    case R.id.radioButton4:
                        select = "임원면접";
                        PreferenceManager.setString(mContext, "select" , select);
                        break;
                    case R.id.radioButton7:
                        select = "일반면접";
                        PreferenceManager.setString(mContext, "select" , select);
                        break;

                }
            }
        });


        String getString = getIntent().getStringExtra("passData2");
        String getString2 = getIntent().getStringExtra("passData3");



        final Spinner spin1 = (Spinner)findViewById(R.id.spinner);
        final Spinner spin2 = (Spinner)findViewById(R.id.spinner2);


        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adspin1);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adspin1.getItem(i).equals("경영/사무")) {

                    choice_do = "경영/사무";//버튼 클릭시 출력을 위해 값을 넣었습니다.
                    adspin2 = ArrayAdapter.createFromResource(Addmeeting.this, R.array.spinner_do_manage, android.R.layout.simple_spinner_dropdown_item);

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
                    adspin2 = ArrayAdapter.createFromResource(Addmeeting.this, R.array.spinner_do_marketing, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("IT/디지털")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "IT/디지털";
                    adspin2 = ArrayAdapter.createFromResource(Addmeeting.this, R.array.spinner_do_it, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("디자인")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "디자인";
                    adspin2 = ArrayAdapter.createFromResource(Addmeeting.this, R.array.spinner_do_design, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("영업/고객/서비스")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "영업/고객/서비스";
                    adspin2 = ArrayAdapter.createFromResource(Addmeeting.this, R.array.spinner_do_service, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("무역유통")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "무역유통";
                    adspin2 = ArrayAdapter.createFromResource(Addmeeting.this, R.array.spinner_do_trade, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("연구개발/설계")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "연구개발/설계";
                    adspin2 = ArrayAdapter.createFromResource(Addmeeting.this, R.array.spinner_do_search, android.R.layout.simple_spinner_dropdown_item);
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
                else if (adspin1.getItem(i).equals("건설/토목")) {
//똑같은 소스에 반복입니다. 인천부분입니다.
                    choice_do = "건설/토목";
                    adspin2 = ArrayAdapter.createFromResource(Addmeeting.this, R.array.spinner_do_construction, android.R.layout.simple_spinner_dropdown_item);
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

        et_aname = findViewById(R.id.et_aname);
        tv_company = findViewById(R.id.tv_company);
        switch_hab = findViewById(R.id.switch_hab);
        et_description = findViewById(R.id.et_description);
        et_description1 = findViewById(R.id.et_description1);
        et_des2 = findViewById(R.id.et_description22);
        des22 =  findViewById(R.id.et_description33);
        des3 = findViewById(R.id.et_description4);
        des33 = findViewById(R.id.et_description5);
        des4 = findViewById(R.id.et_description6);
        des44 = findViewById(R.id.et_description3);
        des5 = findViewById(R.id.et_description2);
        des55 = findViewById(R.id.et_description3333);

        previous_intent = getIntent();

        MeetAddData meetAddData_previous = (MeetAddData) previous_intent.getSerializableExtra("Data Object");


        if(meetAddData_previous != null){

            et_aname.setText(meetAddData_previous.getA_name());

            tv_company.setText(meetAddData_previous.getStudy_company());

            switch_hab.setChecked(meetAddData_previous.isStudy_ishab());

            et_description.setText(meetAddData_previous.getStudy_explain());

            et_description1.setText(meetAddData_previous.getStudy_explain2());

            et_des2.setText(meetAddData_previous.getStudy_explain3());

            des22.setText(meetAddData_previous.getStudy_explain4());

            des3.setText(meetAddData_previous.getStudy_explain5());

            des33.setText(meetAddData_previous.getStudy_explain6());

            des4.setText(meetAddData_previous.getStudy_explain7());

            des44.setText(meetAddData_previous.getStudy_explain8());

            des5.setText(meetAddData_previous.getStudy_explain9());

            des55.setText(meetAddData_previous.getStudy_explain10());



        }


        btn_make = findViewById(R.id.btn_make);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        tv_company = (Button) findViewById(R.id.tv_company);
        tv_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MeetAddData meetAddData = new MeetAddData();


                String study_category_high = choice_do;
                String study_category_low = choice_se;

                String study_company = tv_company.getText().toString();

                boolean study_ishab = switch_hab.isChecked();

                String study_explain = et_description.getText().toString();
                String study_explain2 = et_description1.getText().toString();
                String study_explain3 = et_des2.getText().toString();
                String study_explain4 = des22.getText().toString();
                String study_explain5 = des3.getText().toString();
                String study_explain6 = des33.getText().toString();
                String study_explain7 = des4.getText().toString();
                String study_explain8 = des44.getText().toString();
                String study_explain9 = des5.getText().toString();
                String study_explain10 = des55.getText().toString();


                meetAddData.setStudy_category_high(study_category_high);
                meetAddData.setStudy_category_low(study_category_low);

                meetAddData.setStudy_company(study_company);

                meetAddData.setStudy_ishab(study_ishab);

                meetAddData.setStudy_explain(study_explain);
                meetAddData.setStudy_explain2(study_explain2);
                meetAddData.setStudy_explain2(study_explain3);
                meetAddData.setStudy_explain2(study_explain4);
                meetAddData.setStudy_explain2(study_explain5);
                meetAddData.setStudy_explain2(study_explain6);
                meetAddData.setStudy_explain2(study_explain7);
                meetAddData.setStudy_explain2(study_explain8);
                meetAddData.setStudy_explain2(study_explain9);
                meetAddData.setStudy_explain2(study_explain10);

                Intent intent12 = new Intent(Addmeeting.this, Parsingmeeting.class);
                intent12.putExtra("Data Object", meetAddData);
                startActivity(intent12);
            }
        });


        btn_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a_name = et_aname.getText().toString();
                String study_category_high = choice_do;
                String study_category_low = choice_se;

                String study_company = tv_company.getText().toString();


                boolean study_ishab = switch_hab.isChecked();

                String study_explain = et_description.getText().toString();
                String study_explain2 = et_description1.getText().toString();
                String study_explain3 = et_des2.getText().toString();
                String study_explain4 = des22.getText().toString();
                String study_explain5 = des3.getText().toString();
                String study_explain6 = des33.getText().toString();
                String study_explain7 = des4.getText().toString();
                String study_explain8 = des44.getText().toString();
                String study_explain9 = des5.getText().toString();
                String study_explain10 = des55.getText().toString();


                HashMap new_study = new HashMap<>();

                new_study.put("name" , a_name);
                new_study.put("study_category_high" , study_category_high);
                new_study.put("study_category_low" , study_category_low);
                new_study.put("study_company" , study_company);
                new_study.put("study_ishab" , study_ishab);
                new_study.put("study_explain" , study_explain);
                new_study.put("study_explain2" , study_explain2);
                new_study.put("study_explain3" , study_explain3);
                new_study.put("study_explain4" , study_explain4);
                new_study.put("study_explain5" , study_explain5);
                new_study.put("study_explain6" , study_explain6);
                new_study.put("study_explain7" , study_explain7);
                new_study.put("study_explain8" , study_explain8);
                new_study.put("study_explain9" , study_explain9);
                new_study.put("study_explain10" , study_explain10);

                writeNewStudy(a_name,study_category_high,study_category_low,study_company, study_ishab,
                        study_explain, study_explain2, study_explain3, study_explain4, study_explain5, study_explain6, study_explain7, study_explain8, study_explain9, study_explain10);
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




    private void writeNewStudy( String a_name, String study_category_high, String study_category_low, String study_company, boolean study_ishab,
                                String study_explain,String study_explain2, String study_explain3, String study_explain4, String study_explain5, String study_explain6, String study_explain7, String study_explain8, String study_explain9, String study_explain10){
        Random study_primary_key = new Random();
        String a_primary = String.valueOf(study_primary_key.nextInt());

        AddmeetingModel addmeetingModel = new AddmeetingModel(a_name, a_primary, study_category_high,study_category_low,study_company, study_ishab,
                study_explain,study_explain2, study_explain3,study_explain4,study_explain5,study_explain6,study_explain7,study_explain8,study_explain9,study_explain10);



        Random study_primary = new Random();
        Intent intent12345 = new Intent(this, HomeActivity.class);

        mDatabase.child("Meeting").child(a_primary).setValue(addmeetingModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Addmeeting.this, "등록이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                startActivity(intent12345);
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Addmeeting.this, "등록이 실패했습니다", Toast.LENGTH_SHORT).show();
                    }
                });


    }


}