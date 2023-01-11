package com.example.myapplication.ui.dash_ViewPagerAdapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.dash_page.page1.d_page1_0;
import com.example.myapplication.ui.dash_page.page1.d_page1_1;
import com.example.myapplication.ui.dash_page.page1.d_page1_2;
import com.example.myapplication.ui.dash_page.page1.d_page1_3;
import com.example.myapplication.ui.dash_page.page1.d_page1_4;
import com.example.myapplication.ui.dash_page.page1.d_page1_5;
import com.example.myapplication.ui.dash_page.page1.d_page1_6;

public class Dash_ViewPagerAdapter extends FragmentStateAdapter {


    public Dash_ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new d_page1_0();
            case 1 :
                return new d_page1_1();
            case 2 :
                return new d_page1_2();
            case 3 :
                return new d_page1_3();
            case 4 :
                return new d_page1_4();
            case 5 :
                return new d_page1_5();
            case 6 :
                return new d_page1_6();
            default :
                return new d_page1_0();
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }
}