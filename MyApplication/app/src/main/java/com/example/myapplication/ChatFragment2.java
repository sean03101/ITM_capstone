package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ChatModel;
import com.example.myapplication.Parsing;
import com.example.myapplication.R;
import com.example.myapplication.UserInfoModel;
import com.example.myapplication.UseridData;
import com.example.myapplication.chat.GroupMessageActivity;
import com.example.myapplication.chat.MessageActivity;
import com.example.myapplication.chat.MessageActivity2;
import com.example.myapplication.ui.page.RecyclerViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


public class ChatFragment2 extends Fragment {
    private String user_id = "";
    private String destinationUid2;
    public String uid3;

    public String text;


    SharedPreferences preferences = this.getActivity().getSharedPreferences("test", MODE_PRIVATE);

    String uid4 = preferences.getString("inputText","");



    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat2, container, false);

        RecyclerView recyclerView =  view.findViewById(R.id.chatfragment_recyclerview);
        recyclerView.setAdapter(new ChatRecyclerViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager((inflater.getContext())));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        Log.i(TAG, "onDataChange: " +uid4);



        return view;
    }




    class ChatRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<ChatModel> chatModels = new ArrayList<>();
        private List<String> keys = new ArrayList<>();
        private String uid;

        private ArrayList<String> destinationUsers = new ArrayList<>();


        public ChatRecyclerViewAdapter() {
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


            FirebaseDatabase.getInstance().getReference().child("chatrooms").orderByChild("users/"+text).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    chatModels.clear();
                    for (DataSnapshot item : dataSnapshot.getChildren()){
                        chatModels.add(item.getValue(ChatModel.class));
                        keys.add(item.getKey());
                    }
                    notifyDataSetChanged();
                }



                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);

            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            CustomViewHolder customViewHolder = (CustomViewHolder)holder;
            String destinationUid = null;

            user_id = UseridData.getInstance().getUser_id();
            //일일 챗방에 있는 유저를 체크
            for(String user: chatModels.get(position).users.keySet()){
                if(!user.equals(text)){
                    destinationUid = user;
                    destinationUsers.add(destinationUid);
                }
            }
            List<UserInfoModel> userModels;
            userModels = new ArrayList<>();

            final String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseDatabase.getInstance().getReference().child("users").child(destinationUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserInfoModel userModel = dataSnapshot.getValue(UserInfoModel.class);

                    customViewHolder.textView_title.setText(userModel.user_name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            //메세지를 내림차순으로 정렬 후 마지막 메세지 키 값을 가져옴
            Map<String,ChatModel.Comment> commentMap = new TreeMap<>(Collections.reverseOrder());
            commentMap.putAll(chatModels.get(position).comments);
            if(commentMap.keySet().toArray().length>0) {


                String lastMessageKey = (String) commentMap.keySet().toArray()[0];
                customViewHolder.textView_last_message.setText(chatModels.get(position).comments.get(lastMessageKey).message);


                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

                long unixTime = (long) chatModels.get(position).comments.get(lastMessageKey).timestamp;

                Date date = new Date(unixTime);
                customViewHolder.textView_timestamp.setText(simpleDateFormat.format(date));
            }
            customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = null;
                    if(chatModels.get(position).users.size()>2){
                        intent = new Intent(view.getContext(), GroupMessageActivity.class);
                        intent.putExtra("destinationRoom",keys.get(position));
                    }else{
                        intent = new Intent(view.getContext(), MessageActivity2.class);
                        intent.putExtra("destinationUid", destinationUsers.get(position));
                    }

                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return chatModels.size();
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder{

            public ImageView imageView;
            public TextView textView_title;
            public TextView textView_last_message;
            public TextView textView_timestamp;
            public CustomViewHolder(View view){
                super(view);

                imageView = view.findViewById(R.id.chatitem_imageview);
                textView_title = view.findViewById(R.id.chatitem_textview_title);
                textView_last_message = view.findViewById(R.id.chatitem_textview_lastMessage);
                textView_timestamp = view.findViewById(R.id.chatitem_textview_timestamp);
            }
        }
    }
}