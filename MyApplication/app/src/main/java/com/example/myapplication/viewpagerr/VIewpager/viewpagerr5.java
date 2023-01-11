package com.example.myapplication.viewpagerr.VIewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.ui.a_ViewPagerAdapter.a_ViewPagerAdapter.a_ViewPagerAdapter5;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class viewpagerr5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager5);

        TabLayout tabLayout = findViewById(R.id.tab_5);
        ViewPager2 viewPager2 = findViewById(R.id.view_page_5);


        a_ViewPagerAdapter5 adapter = new a_ViewPagerAdapter5(this);
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
                        tab.setText("해외영업/무역영업");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 2: {
                        tab.setText("수출입");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 3: {
                        tab.setText("상품기획/MD");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 4: {
                        tab.setText("유통/물류/재고");
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