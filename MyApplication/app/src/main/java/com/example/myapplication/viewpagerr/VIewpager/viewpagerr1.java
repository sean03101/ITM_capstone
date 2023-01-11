package com.example.myapplication.viewpagerr.VIewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.ui.a_ViewPagerAdapter.a_ViewPagerAdapter.a_ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class viewpagerr1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager1);

        TabLayout tabLayout = findViewById(R.id.tab_1);
        ViewPager2 viewPager2 = findViewById(R.id.view_page_1);


        a_ViewPagerAdapter adapter = new a_ViewPagerAdapter(this);
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
                        tab.setText("기획/전략/경영");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 2: {
                        tab.setText("사무/총무/법무");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 3: {
                        tab.setText("인사/노무/교육");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 4: {
                        tab.setText("경리/회계/결산");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 5: {
                        tab.setText("재무/세무");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;
                    }
                    case 6: {
                        tab.setText("비서/안내");
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