package com.example.myapplication.ui.ViewPagerAdapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.page.page1.page1_0;
import com.example.myapplication.ui.page.page1.page1_1;
import com.example.myapplication.ui.page.page1.page1_2;
import com.example.myapplication.ui.page.page1.page1_3;
import com.example.myapplication.ui.page.page1.page1_4;
import com.example.myapplication.ui.page.page1.page1_5;
import com.example.myapplication.ui.page.page1.page1_6;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new page1_0();
            case 1 :
                return new page1_1();
            case 2 :
                return new page1_2();
            case 3 :
                return new page1_3();
            case 4 :
                return new page1_4();
            case 5 :
                return new page1_5();
            case 6 :
                return new page1_6();
            default :
                return new page1_0();
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }
}