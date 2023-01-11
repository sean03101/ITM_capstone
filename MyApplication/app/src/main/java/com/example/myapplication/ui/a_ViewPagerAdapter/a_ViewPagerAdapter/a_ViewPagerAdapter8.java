package com.example.myapplication.ui.a_ViewPagerAdapter.a_ViewPagerAdapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.ui.a_page.page1.page81.a_page81_0;
import com.example.myapplication.ui.a_page.page1.page81.a_page81_1;
import com.example.myapplication.ui.a_page.page1.page81.a_page81_2;
import com.example.myapplication.ui.a_page.page1.page81.a_page81_3;
import com.example.myapplication.ui.a_page.page1.page81.a_page81_4;
import com.example.myapplication.ui.a_page.page1.page81.a_page81_5;

public class a_ViewPagerAdapter8 extends FragmentStateAdapter {


    public a_ViewPagerAdapter8(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0 :
                return new a_page81_0();
            case 1 :
                return new a_page81_1();
            case 2 :
                return new a_page81_2();
            case 3 :
                return new a_page81_3();
            case 4 :
                return new a_page81_4();
            case 5 :
                return new a_page81_5();

            default :
                return new a_page81_0();
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}