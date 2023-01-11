package com.example.myapplication.ui.viewpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.ui.ViewPagerAdapter.ViewPagerAdapter7;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class viewpage7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage1);

        TabLayout tabLayout = findViewById(R.id.tabs_1);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager_1);


        ViewPagerAdapter7 adapter = new ViewPagerAdapter7(this);
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
                        tab.setText("자동차/조선/기계");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;


                    }
                    case 2: {
                        tab.setText("반도체/디스플레이");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 3: {
                        tab.setText("화학/에너지/환경");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 4: {
                        tab.setText("전기/전자/제어");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 5: {
                        tab.setText("기계설계/CAP/CAM");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 6: {
                        tab.setText("통신기술/네트워크구축");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 7: {
                        tab.setText("바이오/제약/식품");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }


                }
            }
        }
        );
        tabLayoutMediator.attach();


    }
}