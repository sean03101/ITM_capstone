package com.example.myapplication.ui.a_ViewPagerAdapter.a_ViewPagerAdapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.a_page.page1.page41.a_page41_0;
import com.example.myapplication.ui.a_page.page1.page41.a_page41_1;
import com.example.myapplication.ui.a_page.page1.page41.a_page41_2;
import com.example.myapplication.ui.a_page.page1.page41.a_page41_3;
import com.example.myapplication.ui.a_page.page1.page41.a_page41_4;
import com.example.myapplication.ui.a_page.page1.page41.a_page41_5;
import com.example.myapplication.ui.a_page.page1.page41.a_page41_6;

public class a_ViewPagerAdapter4 extends FragmentStateAdapter {


    public a_ViewPagerAdapter4(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new a_page41_0();
            case 1 :
                return new a_page41_1();
            case 2 :
                return new a_page41_2();
            case 3 :
                return new a_page41_3();
            case 4 :
                return new a_page41_4();
            case 5 :
                return new a_page41_5();
            case 6 :
                return new a_page41_6();
            default :
                return new a_page41_0();
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }
}