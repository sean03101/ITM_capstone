package com.example.myapplication.ui.page;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.PreferenceManager;
import com.example.myapplication.R;
import com.example.myapplication.StudydetailActivity;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {


    private ArrayList<RecylcerViewModel> arrayList;
    private Context context;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<RecylcerViewModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    //박스 생성
    public CustomViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_studylist_box, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    //각 요소들들 불러옴
    @Override
    public void onBindViewHolder(@NonNull @NotNull CustomViewHolder holder, int position) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        storageReference.child("Study Images/" + arrayList.get(position).getStudy_photo() + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(holder.itemView.getContext())
                        .load(uri)
                        .into(holder.recyclerview_studybox_image);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });
        holder.recyclerview_studybox_high.setText(arrayList.get(position).getStudy_name());
        holder.recyclerview_studybox_middle.setText(arrayList.get(position).getStudy_explain());
        holder.recyclerview_studybox_low_1.setText(arrayList.get(position).getStudy_company());
        holder.recyclerview_studybox_low_2.setText(arrayList.get(position).getStudy_studycafe());
        holder.recyclerview_studybox_low_3.setText(arrayList.get(position).getStudy_number());
        holder.recyclerview_studybox_low_4.setText(arrayList.get(position).getStudy_category_low());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StudydetailActivity.class);
                PreferenceManager.setString(v.getContext(), "Study Primary Key" , arrayList.get(position).getStudy_primary());
                PreferenceManager.setString(v.getContext(), "Study Photo" , arrayList.get(position).getStudy_photo());
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