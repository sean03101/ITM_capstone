package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class CategortData {

    private List<String> CategoryList;
    private static volatile CategortData categortdata;

    private CategortData(){
        CategoryList = new ArrayList<String>();
    }
    public static synchronized CategortData getInstance(){
        if(categortdata == null){
            categortdata = new CategortData();
        }
        return categortdata;
    }

    public void add(String category){
        CategoryList.add(category);
    }

    public String get(int index){
        return CategoryList.get(index);
    }


}
