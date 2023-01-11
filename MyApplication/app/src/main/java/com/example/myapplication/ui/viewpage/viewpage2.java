package com.example.myapplication.ui.viewpage;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.ui.ViewPagerAdapter.ViewPagerAdapter2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class viewpage2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage1);

        TabLayout tabLayout = findViewById(R.id.tabs_1);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager_1);


        ViewPagerAdapter2 adapter = new ViewPagerAdapter2(this);
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
                        tab.setText("마케팅/광고");
                        tab.setIcon(R.drawable.baseline_folder_open_black_24);
                        break;


                    }
                    case 2: {
                        tab.setText("홍보/PR");
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