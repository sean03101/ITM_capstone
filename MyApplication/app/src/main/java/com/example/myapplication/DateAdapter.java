package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DateAdapter extends RecyclerView.Adapter<DateViewHolder> implements ItemTouchHelperListener{
    private ArrayList<String> arrayList;
    private String study_primarykey;
    private String study_date;
    private DatabaseReference mDatabase;

    public DateAdapter() {
        arrayList = new ArrayList<>();
    }


    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_date_list, parent, false);

        DateViewHolder dateViewHolder = new DateViewHolder(view);

        return dateViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder dateViewHolder, int position) {
        String text = arrayList.get(position);
        String[] text_list = text.split(":");
        study_primarykey = text_list[2];
        study_date = text_list[0];
        dateViewHolder.textView.setText(text_list[0] + " : " + text_list[1]);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    } // 데이터를 입력

    public void setArrayData(String strData) {
        arrayList.add(strData);
    }

    private void delete(int position){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("DateList").child(study_primarykey).child(study_date).removeValue();

    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        String number = arrayList.get(from_position);
        arrayList.remove(from_position);
        arrayList.add(to_position , number);
        notifyItemMoved(from_position,to_position);


        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        String number = arrayList.get(position);
        delete(position);
        arrayList.remove(position);
        notifyItemRemoved(position);
    }
}