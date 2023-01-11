package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;

public class DateViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public Button button;

    DateViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        button = itemView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strText = textView.getText().toString();
                //Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
                System.out.println(strText);
            }
        });
    }
}
