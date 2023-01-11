package com.example.myapplication.ui.dash_ViewPagerAdapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.dash_page.page1.page51.d_page51_0;
import com.example.myapplication.ui.dash_page.page1.page51.d_page51_1;
import com.example.myapplication.ui.dash_page.page1.page51.d_page51_2;
import com.example.myapplication.ui.dash_page.page1.page51.d_page51_3;
import com.example.myapplication.ui.dash_page.page1.page51.d_page51_4;

public class Dash_ViewPagerAdapter5 extends FragmentStateAdapter {


    public Dash_ViewPagerAdapter5(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new d_page51_0();
            case 1 :
                return new d_page51_1();
            case 2 :
                return new d_page51_2();
            case 3 :
                return new d_page51_3();
            case 4 :
                return new d_page51_4();

            default :
                return new d_page51_0();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}