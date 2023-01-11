package com.example.myapplication.ui.dash_ViewPagerAdapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.dash_page.page1.page21.d_page21_0;
import com.example.myapplication.ui.dash_page.page1.page21.d_page21_1;
import com.example.myapplication.ui.dash_page.page1.page21.d_page21_2;

public class Dash_ViewPagerAdapter2 extends FragmentStateAdapter {


    public Dash_ViewPagerAdapter2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new d_page21_0();
            case 1 :
                return new d_page21_1();
            case 2 :
                return new d_page21_2();
            default :
                return new d_page21_0();

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}