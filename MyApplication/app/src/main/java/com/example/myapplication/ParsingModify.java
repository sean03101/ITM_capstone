package com.example.myapplication;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParsingModify extends AppCompatActivity {

    final static String TAG ="XML";
    ListView listView;
    private TextView selected_item_textview;
    private String[] study_key_list;
    private DatabaseReference mDatabase;
    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing);

        Intent intent123 = getIntent();// 인텐트 받아오기
        intent123 = getIntent();// 인텐트 받아오기
        study_key_list = intent123.getStringArrayExtra("study primary key"); //Adapter에서 받은 키값 연결

        System.out.println("abc" + study_key_list[0]);

        listView = findViewById(R.id.listview1);
        selected_item_textview = (TextView)findViewById(R.id.textView1);

        ArrayList<Corp> list = parser();
        String[] data = new String[list.size()]; // ArrayList 크기만큼 배열 할당

        for(int i = 0; i < list.size(); i++){
            data[i] = list.get(i).getCorp_name()+" ";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, data);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(itemClickListenerOfList);


        EditText editTextFilter = (EditText)findViewById(R.id.editTextFilter) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {





            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                if (filterText.length() > 0) {
                    listView.setFilterText(filterText) ;
                } else {
                    listView.clearTextFilter() ;
                }
            }

        }) ;



        // Adapter 생성

    }
    private AdapterView.OnItemClickListener itemClickListenerOfList = new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> adapterView, View clickedView, int pos, long id) {
            String toastMessage = ((TextView) clickedView).getText().toString();
            Toast.makeText(
                    getApplicationContext(),
                    toastMessage,
                    Toast.LENGTH_SHORT
            ).show();
            String selected_item = (String)adapterView.getItemAtPosition(pos);
            selected_item_textview.setText(selected_item);

            mDatabase = FirebaseDatabase.getInstance().getReference();
            Map<String, Object> tempmap = new HashMap<>();
            System.out.println("asdbsd"+"Study/"+study_key_list[0]+"/study_company");
            tempmap.put("Study/"+study_key_list[0]+"/study_company", selected_item);
            mDatabase.updateChildren(tempmap);

            arrayList = new ArrayList<>();
            mDatabase.child("Study").orderByChild("study_primary").equalTo(study_key_list[0]).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화
                    for(DataSnapshot snapshot2 : snapshot.getChildren()){
                        arrayList.add(snapshot2.getValue());
                    }
                    arrayList.add(study_key_list[0]);

                    Iterator iter = arrayList.iterator(); //Iterator 선언
                    while(iter.hasNext()) {//다음값이 있는지 체크
                        System.out.print("4343");
                        System.out.println(iter.next()); //값 출력
                    }

                    Intent intent2 = new Intent(ParsingModify.this, StudyModifyActivity.class);
                    intent2.putExtra("information list",arrayList);
                    startActivity(intent2);
                    finish();

                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });


        }
    };


    private ArrayList<Corp> parser(){

        ArrayList<Corp> arrayList = new ArrayList<Corp>();

        // 내부 xml파일이용시
        InputStream inputStream = getResources().openRawResource(R.raw.corpcode);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        XmlPullParserFactory factory = null;
        XmlPullParser xmlParser = null;

        try {
            factory = XmlPullParserFactory.newInstance();
            xmlParser = factory.newPullParser();
            xmlParser.setInput(inputStreamReader);
            Corp student = null;

            int eventType = xmlParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:

                        try {
                            String startTag = xmlParser.getName();
                            if(startTag.equals("list")){
                                student = new Corp();
                            }
                            if(startTag.equals("corp_name")){
                                student.setCorp_name(xmlParser.nextText());
                                Log.i(TAG,"TEXT : "+ student.getCorp_name());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case XmlPullParser.END_TAG:

                        String endTag = xmlParser.getName();
                        if(endTag.equals("list")){
                            arrayList.add(student);
                        }
                        break;
                }
                try {
                    eventType = xmlParser.next();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally{
            try{
                if(inputStreamReader !=null) inputStreamReader.close();
                if(inputStream !=null) inputStream.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
        return arrayList;


    }
}