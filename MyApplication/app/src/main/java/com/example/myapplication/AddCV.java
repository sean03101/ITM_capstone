package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class AddCV extends AppCompatActivity {

    private TextView et_name;
    private TextView  tv_company;
    Intent previous_intent;


    private static final int REQUEST_CODE = 0;
    private ImageView imageView;

    ArrayAdapter<CharSequence> adspin1, adspin2; //어댑터를 선언했습니다. adspint1(서울,인천..) adspin2(강남구,강서구..)
    String choice_do="";
    String choice_se="";

    private TextView  et_dashname;
    private TextView  et_description;
    private TextView  et_description1;

    private Button btn_make;

    private DatabaseReference mDatabase;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcv);

        imageView = findViewById(R.id.iv_cover_image);
        imageView.setOnClickListener(new View.OnClickListener(){
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
                    adspin2 = ArrayAdapter.createFromResource(AddCV.this, R.array.spinner_do_manage, android.R.layout.simple_spinner_dropdown_item);

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
                    adspin2 = ArrayAdapter.createFromResource(AddCV.this, R.array.spinner_do_marketing, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(AddCV.this, R.array.spinner_do_it, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(AddCV.this, R.array.spinner_do_design, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(AddCV.this, R.array.spinner_do_service, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(AddCV.this, R.array.spinner_do_trade, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(AddCV.this, R.array.spinner_do_search, android.R.layout.simple_spinner_dropdown_item);
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
                    adspin2 = ArrayAdapter.createFromResource(AddCV.this, R.array.spinner_do_construction, android.R.layout.simple_spinner_dropdown_item);
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



        et_dashname = findViewById(R.id.et_dashname);
        tv_company = findViewById(R.id.tv_company);

        et_description = findViewById(R.id.et_description);
        et_description1 = findViewById(R.id.et_description1);

        previous_intent = getIntent();
        CVAddData cvAddData_previous = (CVAddData) previous_intent.getSerializableExtra("Data Object");

        if(cvAddData_previous != null){

            et_dashname.setText(cvAddData_previous.getStudy_name());
            tv_company.setText(cvAddData_previous.getStudy_company());

            et_description.setText(cvAddData_previous.getStudy_explain());
            et_description1.setText(cvAddData_previous.getStudy_explain1());
        }



        btn_make = findViewById(R.id.btn_make);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        tv_company = (Button) findViewById(R.id.tv_company);
        tv_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CVAddData cvAddData = new CVAddData();


                String study_category_high = choice_do;
                String study_category_low = choice_se;

                String study_company = tv_company.getText().toString();

                String study_explain = et_description.getText().toString();
                String study_explain1 = et_description1.getText().toString();


                cvAddData.setStudy_category_high(study_category_high);
                cvAddData.setStudy_category_low(study_category_low);

                cvAddData.setStudy_company(study_company);

                cvAddData.setStudy_explain(study_explain);
                cvAddData.setStudy_explain(study_explain1);

                Intent intent11 = new Intent(AddCV.this, ParsingCV.class);
                intent11.putExtra("Data Object", cvAddData);
                startActivity(intent11);
            }
        });


        btn_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String study_category_high = choice_do;
                String study_category_low = choice_se;

                String study_company = tv_company.getText().toString();

                String study_name = et_dashname.getText().toString();
                String study_explain = et_description.getText().toString();
                String study_explain2 = et_description1.getText().toString();
                HashMap new_study = new HashMap<>();

                new_study.put("name" , study_name);
                new_study.put("study_category_high" , study_category_high);
                new_study.put("study_category_low" , study_category_low);
                new_study.put("study_company" , study_company);


                new_study.put("study_explain" , study_explain);
                new_study.put("study_explain2" , study_explain2);

                writeNewStudy(study_name, study_category_high,study_category_low,study_company,
                        study_explain,study_explain2);

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

    private void writeNewStudy( String study_name, String study_category_high, String study_category_low, String study_company,
                                String study_explain, String study_explain2){

        Random study_primary_key = new Random();
        String study_primary = String.valueOf(study_primary_key.nextInt());

        AddCVModel addcvModel = new AddCVModel(study_name,study_category_high,study_category_low,study_company,
                study_explain,study_explain2,study_primary);


        Intent intent12345 = new Intent(this, HomeActivity.class);

        mDatabase.child("CV").child(study_primary).setValue(addcvModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddCV.this, "등록이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                Intent intent123 = new Intent(AddCV.this, DashDetailActivity.class);
                intent123.putExtra("sibal", study_primary);
                startActivity(intent12345);
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddCV.this, "등록이 실패했습니다", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}