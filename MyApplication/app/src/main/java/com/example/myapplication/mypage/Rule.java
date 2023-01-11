package com.example.myapplication.mypage;

import android.os.Bundle;
import android.widget.TabHost;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class Rule extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rule);
        TabHost tab_host = (TabHost) findViewById(R.id.tabhost);
        tab_host.setup();

        TabHost.TabSpec ts1 = tab_host.newTabSpec("tab1");
        ts1.setIndicator("이용약관");
        ts1.setContent(R.id.이용약관);
        tab_host.addTab(ts1);

        TabHost.TabSpec ts2 = tab_host.newTabSpec("tab2");
        ts2.setIndicator("개인정보보호처리방침");
        ts2.setContent(R.id.개인정보보호처리방침);
        tab_host.addTab(ts2);

        tab_host.setCurrentTab(0);
    }

}
