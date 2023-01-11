package com.example.myapplication.mypage;

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
import com.example.myapplication.ItemTouchHelperListener;
import com.example.myapplication.R;
import com.example.myapplication.StudydetailActivity;
import com.example.myapplication.UseridData;
import com.example.myapplication.ui.page.RecyclerViewAdapter;
import com.example.myapplication.ui.page.RecylcerViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> implements ItemTouchHelperListener {

    private ArrayList<RecylcerViewModel> arrayList;
    private Context context;
    private DatabaseReference mDatabase;

    public WishlistAdapter(ArrayList<RecylcerViewModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_studylist_box, parent, false);
        WishlistViewHolder holder = new WishlistViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WishlistViewHolder holder, int position) {
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
                intent.putExtra("Study Primary Key",arrayList.get(position).getStudy_primary());
                intent.putExtra("Study Photo", arrayList.get(position).getStudy_photo());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        RecylcerViewModel number = arrayList.get(from_position);
        arrayList.remove(from_position);
        arrayList.add(to_position , number);

        notifyItemMoved(from_position,to_position);


        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        RecylcerViewModel number = arrayList.get(position);
        delete(position);
        arrayList.remove(position);
        notifyItemRemoved(position);



    }

    private void delete(int position){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("User").child(UseridData.getInstance().getUser_id()).child("Information")
                .child("user_study_wishlist").child(arrayList.get(position).getStudy_primary()).removeValue();
    }

    public class WishlistViewHolder extends RecyclerView.ViewHolder {

        ImageView recyclerview_studybox_image;
        TextView recyclerview_studybox_high;
        TextView recyclerview_studybox_middle;
        TextView recyclerview_studybox_low_1;
        TextView recyclerview_studybox_low_2;
        TextView recyclerview_studybox_low_3;
        TextView recyclerview_studybox_low_4;

        public WishlistViewHolder(@NonNull @NotNull View itemView) {
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