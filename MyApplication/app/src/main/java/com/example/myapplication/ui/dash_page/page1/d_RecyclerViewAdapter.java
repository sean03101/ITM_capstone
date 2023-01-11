package com.example.myapplication.ui.dash_page.page1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DashDetailActivity;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class d_RecyclerViewAdapter extends RecyclerView.Adapter<d_RecyclerViewAdapter.CustomViewHolder> {


    private ArrayList<d_RecyclerViewModel> arrayList;
    private Context context;

    public d_RecyclerViewAdapter(ArrayList<d_RecyclerViewModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    //박스 생성
    public CustomViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_dashlist_box, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    //각 요소들들 불러옴
    @Override
    public void onBindViewHolder(@NonNull @NotNull CustomViewHolder holder, int position) {


        holder.recyclerview_studybox_high.setText(arrayList.get(position).getStudy_category_high());
        holder.recyclerview_studybox_middle.setText(arrayList.get(position).getStudy_explain());
        holder.recyclerview_studybox_low_1.setText(arrayList.get(position).getStudy_company());
        holder.recyclerview_studybox_low_4.setText(arrayList.get(position).getStudy_category_low());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashDetailActivity.class);
                intent.putExtra("Study Primary Key2", arrayList.get(position).getStudy_primary());
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        //삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView recyclerview_studybox_image;
        TextView recyclerview_studybox_high;
        TextView recyclerview_studybox_middle;
        TextView recyclerview_studybox_low_1;
        TextView recyclerview_studybox_low_2;
        TextView recyclerview_studybox_low_3;
        TextView recyclerview_studybox_low_4;


        public CustomViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.recyclerview_studybox_image = itemView.findViewById(R.id.recyclerview_studybox_image);
            this.recyclerview_studybox_high = itemView.findViewById(R.id.recyclerview_studybox_high);
            this.recyclerview_studybox_middle = itemView.findViewById(R.id.recyclerview_studybox_middle);
            this.recyclerview_studybox_low_1 = itemView.findViewById(R.id.recyclerview_studybox_low_1);
            this.recyclerview_studybox_low_2 = itemView.findViewById(R.id.recyclerview_studybox_low_2);
            this.recyclerview_studybox_low_3 = itemView.findViewById(R.id.recyclerview_studybox_low_3);
            this.recyclerview_studybox_low_4 = itemView.findViewById(R.id.recyclerview_studybox_low_4);


        }
    }
}