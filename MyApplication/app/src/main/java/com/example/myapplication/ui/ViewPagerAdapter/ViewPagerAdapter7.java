package com.example.myapplication.ui.ViewPagerAdapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.page.page71.page71_0;
import com.example.myapplication.ui.page.page71.page71_1;
import com.example.myapplication.ui.page.page71.page71_2;
import com.example.myapplication.ui.page.page71.page71_3;
import com.example.myapplication.ui.page.page71.page71_4;
import com.example.myapplication.ui.page.page71.page71_5;
import com.example.myapplication.ui.page.page71.page71_6;
import com.example.myapplication.ui.page.page71.page71_7;

public class ViewPagerAdapter7 extends FragmentStateAdapter {


    public ViewPagerAdapter7(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new page71_0();
            case 1 :
                return new page71_1();
            case 2 :
                return new page71_2();
            case 3 :
                return new page71_3();
            case 4 :
                return new page71_4();
            case 5 :
                return new page71_5();
            case 6 :
                return new page71_6();
            default :
                return new page71_0();
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }
}