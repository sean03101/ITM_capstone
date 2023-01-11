package com.example.myapplication.viewpagerr.VIewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.ui.a_ViewPagerAdapter.a_ViewPagerAdapter.a_ViewPagerAdapter8;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class viewpagerr8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager8);

        TabLayout tabLayout = findViewById(R.id.tab_8);
        ViewPager2 viewPager2 = findViewById(R.id.view_page_8);


        a_ViewPagerAdapter8 adapter = new a_ViewPagerAdapter8(this);
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
                        tab.setText("건축/설계/인테리어");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;

                    }
                    case 2: {
                        tab.setText("시공/현장/감리/공무");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 3: {
                        tab.setText("토목/조경/도시/측량");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 4: {
                        tab.setText("소방/안전");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 5: {
                        tab.setText("환경/플랜트");
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