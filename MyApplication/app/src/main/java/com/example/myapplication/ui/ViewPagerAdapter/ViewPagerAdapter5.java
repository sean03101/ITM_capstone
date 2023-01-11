package com.example.myapplication.ui.ViewPagerAdapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.page.page51.page51_0;
import com.example.myapplication.ui.page.page51.page51_1;
import com.example.myapplication.ui.page.page51.page51_2;
import com.example.myapplication.ui.page.page51.page51_3;
import com.example.myapplication.ui.page.page51.page51_4;

public class ViewPagerAdapter5 extends FragmentStateAdapter {


    public ViewPagerAdapter5(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new page51_0();
            case 1 :
                return new page51_1();
            case 2 :
                return new page51_2();
            case 3 :
                return new page51_3();
            case 4 :
                return new page51_4();

            default :
                return new page51_0();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}