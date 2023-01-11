package com.example.myapplication.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.AddActivity;
import com.example.myapplication.AddCV;
import com.example.myapplication.Addmeeting;
import com.example.myapplication.R;
import com.example.myapplication.UserLocationData;
import com.example.myapplication.ui.Add.AddViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class FragmentAdd extends Fragment {


    private AddViewModel addViewModel;
    private Context context;
    private String getString;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add, container, false);

        AdView mAdView;
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { } });


        mAdView = root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Button button9 = (Button) root.findViewById(R.id.button9);
        Button button14 = (Button) root.findViewById(R.id.button14);
        Button button10 = (Button) root.findViewById(R.id.button10);



        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent14 = new Intent(getContext(), AddCV.class);
                startActivity(intent14);
            }

        });


        getString = UserLocationData.getInstance().getUser_location();
        System.out.println("kkk" + getString);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString == null) {
                    Toast.makeText(getActivity(), "지역 설정을 먼저 해주세요!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent9 = new Intent(getContext(), AddActivity.class);
                    startActivity(intent9);
                }

            }

        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent10 = new Intent(getContext(), Addmeeting.class);
                startActivity(intent10);
            }

        });

        // final TextView textView = root.findViewById(R.id.text_home);
        /*homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }



}