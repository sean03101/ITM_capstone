package com.example.myapplication;

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

import com.example.myapplication.CVAddData;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ParsingCV extends AppCompatActivity {

    final static String TAG ="XML";
    ListView listView;
    private TextView selected_item_textview;
    Intent previous_intent123;
    CVAddData cvAddData_previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing);

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

        previous_intent123 = getIntent();
        cvAddData_previous = (CVAddData) previous_intent123.getSerializableExtra("Data Object");

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

            CVAddData cvAddData = new CVAddData();


            cvAddData.setStudy_category_high(cvAddData_previous.getStudy_category_high());
            cvAddData.setStudy_category_low(cvAddData_previous.getStudy_category_low());

            cvAddData.setStudy_company(selected_item);
            cvAddData.setStudy_explain(cvAddData_previous.getStudy_explain());
            cvAddData.setStudy_explain1(cvAddData_previous.getStudy_explain1());



            Intent intent2 = new Intent(ParsingCV.this, AddCV.class);
            intent2.putExtra("Data Object", cvAddData);
            startActivity(intent2);

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