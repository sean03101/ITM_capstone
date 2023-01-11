package com.example.myapplication.ui.ViewPagerAdapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.page.page61.page61_0;
import com.example.myapplication.ui.page.page61.page61_1;
import com.example.myapplication.ui.page.page61.page61_2;
import com.example.myapplication.ui.page.page61.page61_3;
import com.example.myapplication.ui.page.page61.page61_4;
import com.example.myapplication.ui.page.page61.page61_5;
import com.example.myapplication.ui.page.page61.page61_6;
import com.example.myapplication.ui.page.page61.page61_7;

public class ViewPagerAdapter6 extends FragmentStateAdapter {


    public ViewPagerAdapter6(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new page61_0();
            case 1 :
                return new page61_1();
            case 2 :
                return new page61_2();
            case 3 :
                return new page61_3();
            case 4 :
                return new page61_4();
            case 5 :
                return new page61_5();
            case 6 :
                return new page61_6();
            case 7 :
                return new page61_7();
            default :
                return new page61_0();
        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }
}