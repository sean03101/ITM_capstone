package com.example.myapplication.ui.ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.page.page31.page31_0;
import com.example.myapplication.ui.page.page31.page31_1;
import com.example.myapplication.ui.page.page31.page31_10;
import com.example.myapplication.ui.page.page31.page31_11;
import com.example.myapplication.ui.page.page31.page31_12;
import com.example.myapplication.ui.page.page31.page31_2;
import com.example.myapplication.ui.page.page31.page31_3;
import com.example.myapplication.ui.page.page31.page31_4;
import com.example.myapplication.ui.page.page31.page31_5;
import com.example.myapplication.ui.page.page31.page31_6;
import com.example.myapplication.ui.page.page31.page31_7;
import com.example.myapplication.ui.page.page31.page31_8;
import com.example.myapplication.ui.page.page31.page31_9;

public class ViewPagerAdapter3 extends FragmentStateAdapter {


    public ViewPagerAdapter3(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new page31_0();
            case 1 :
                return new page31_1();
            case 2 :
                return new page31_2();
            case 3 :
                return new page31_3();
            case 4 :
                return new page31_4();
            case 5 :
                return new page31_5();
            case 6 :
                return new page31_6();
            case 7 :
                return new page31_7();
            case 8 :
                return new page31_8();
            case 9 :
                return new page31_9();
            case 10 :
                return new page31_10();
            case 11 :
                return new page31_11();
            case 12 :
                return new page31_12();

            default :
                return new page31_0();
        }
    }

    @Override
    public int getItemCount() {
        return 14;
    }
}