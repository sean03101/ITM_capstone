package com.example.myapplication.ui.VIewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.ui.dash_ViewPagerAdapter.Dash_ViewPagerAdapter3;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class viewpager3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager3);

        TabLayout tabLayout = findViewById(R.id.tab_3);
        ViewPager2 viewPager2 = findViewById(R.id.view_page_3);


        Dash_ViewPagerAdapter3 adapter = new Dash_ViewPagerAdapter3(this);
        viewPager2.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0: {
                        tab.setText("전체");
                        tab.setIcon(R.drawable.baseline_folder_black_24);
                        break;
                    }

                    case 1: {
                        tab.setText("IT/디지털");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;


                    }
                    case 2: {
                        tab.setText("웹프로그램");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 3: {
                        tab.setText("시스템프로그램");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 4: {
                        tab.setText("DBA/데이터베이스");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 5: {
                        tab.setText("네트워크/서버/보안");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 6: {
                        tab.setText("기획/PM");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 7: {
                        tab.setText("UI/UX");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 8: {
                        tab.setText("QA/테스터/검증");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }

                    case 9: {
                        tab.setText("게임");
                        tab.setIcon(R.drawable.ic_launcher_background);
                        break;
                    }
                    case 10: {
                        tab.setText("ERP/시스템분석/설계");
                        tab.setIcon(R.drawable.ic_launcher_background);
                        break;
                    }
                    case 11: {
                        tab.setText("빅데이터/AI");
                        tab.setIcon(R.drawable.ic_launcher_background);
                        break;
                    }

                    case 12: {
                        tab.setText("SW/HW");
                        tab.setIcon(R.drawable.ic_launcher_background);
                        break;
                    }


                }
            }
        }
        );
        tabLayoutMediator.attach();
    }
}