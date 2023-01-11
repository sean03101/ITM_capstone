package com.example.myapplication.ui.a_ViewPagerAdapter.a_ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.a_page.page1.page31.a_page31_0;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_1;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_10;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_11;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_12;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_2;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_3;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_4;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_5;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_6;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_7;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_8;
import com.example.myapplication.ui.a_page.page1.page31.a_page31_9;

public class a_ViewPagerAdapter3 extends FragmentStateAdapter {


    public a_ViewPagerAdapter3(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new a_page31_0();
            case 1 :
                return new a_page31_1();
            case 2 :
                return new a_page31_2();
            case 3 :
                return new a_page31_3();
            case 4 :
                return new a_page31_4();
            case 5 :
                return new a_page31_5();
            case 6 :
                return new a_page31_6();
            case 7 :
                return new a_page31_7();
            case 8 :
                return new a_page31_8();
            case 9 :
                return new a_page31_9();
            case 10 :
                return new a_page31_10();
            case 11 :
                return new a_page31_11();
            case 12 :
                return new a_page31_12();

            default :
                return new a_page31_0();
        }
    }

    @Override
    public int getItemCount() {
        return 14;
    }
}