package com.example.myapplication.mypage;
import java.util.ArrayList;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;

public class notice extends ExpandableListActivity{

    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItems = new ArrayList<Object>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // this is not really  necessary as ExpandableListActivity contains an ExpandableList
        //setContentView(R.layout.main);

        ExpandableListView expandableList = getExpandableListView(); // you can use (ExpandableListView) findViewById(R.id.list)

        expandableList.setDividerHeight(2);
        expandableList.setGroupIndicator(null);
        expandableList.setClickable(true);

        setGroupParents();
        setChildData();

        BaseExpandableAdapter adapter = new BaseExpandableAdapter(parentItems, childItems);

        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        expandableList.setAdapter(adapter);
        expandableList.setOnChildClickListener(this);
    }

    public void setGroupParents() {
        parentItems.add("스터디 공지사항");
        parentItems.add("시스템 업데이트 공지사항");
        parentItems.add("서비스 개인정보 취급방침 공지사항");
        parentItems.add("이용약관 개정안내");
        parentItems.add("도움말");
    }

    public void setChildData() {

        // 스터디 공지사항
        ArrayList<String> child = new ArrayList<String>();
        child.add("공지사항1");
        child.add("공지사항2");
        childItems.add(child);

        // 시스템 업데이트 공지사항
        child = new ArrayList<String>();
        child.add("버전정보 v8.3.1");
        child.add("버전정보 v8.3.0");
        child.add("버전정보 v8.2.9");
        child.add("버전정보 v8.2.8");
        child.add("버전정보 v8.2.7");
        childItems.add(child);

        // 서비스 개인정보 취급방침 공지사항
        child = new ArrayList<String>();
        child.add("개정사유");
        child.add("개정사항/변경시기");
        child.add("개일정보처리방침 전문 보기");
        childItems.add(child);

        // 이용약관 개정안내
        child = new ArrayList<String>();
        child.add("개정내용");
        child.add("변경전/변경후");
        child.add("수탁업체");
        child.add("개선");
        childItems.add(child);

        //도움말
        child = new ArrayList<String>();
        child.add("어플을 탈퇴하고 싶어요. 어떻게 하나요?");
        child.add("다른 스터디원과 어떻게 채팅할 수 있나요?");
        child.add("만든 스터디 방 정보를 수정/삭제 하고 싶어요.");
        child.add("동네 인증이 안돼요!");
        child.add("로그인 계정을 변경하고 싶어요. 어떻게 변경하나요?");
        childItems.add(child);
    }

}