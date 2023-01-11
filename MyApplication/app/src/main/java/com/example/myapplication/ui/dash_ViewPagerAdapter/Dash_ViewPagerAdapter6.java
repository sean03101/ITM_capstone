package com.example.myapplication.ui.dash_ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.dash_page.page1.page61.d_page61_0;
import com.example.myapplication.ui.dash_page.page1.page61.d_page61_1;
import com.example.myapplication.ui.dash_page.page1.page61.d_page61_2;
import com.example.myapplication.ui.dash_page.page1.page61.d_page61_3;
import com.example.myapplication.ui.dash_page.page1.page61.d_page61_4;
import com.example.myapplication.ui.dash_page.page1.page61.d_page61_5;
import com.example.myapplication.ui.dash_page.page1.page61.d_page61_6;
import com.example.myapplication.ui.dash_page.page1.page61.d_page61_7;

public class Dash_ViewPagerAdapter6 extends FragmentStateAdapter {


    public Dash_ViewPagerAdapter6(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new d_page61_0();
            case 1 :
                return new d_page61_1();
            case 2 :
                return new d_page61_2();
            case 3 :
                return new d_page61_3();
            case 4 :
                return new d_page61_4();
            case 5 :
                return new d_page61_5();
            case 6 :
                return new d_page61_6();
            case 7 :
                return new d_page61_7();
            default :
                return new d_page61_0();
        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }
}