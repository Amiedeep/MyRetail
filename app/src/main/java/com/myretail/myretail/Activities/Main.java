package com.myretail.myretail.activities;



import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.myretail.myretail.adoptors.ExpandableListAdapter;
import com.myretail.myretail.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Activity {

    android.widget.ExpandableListAdapter listAdapter;
    ExpandableListView listView;
    List<String> listHeader;
    HashMap<String, List<String>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ExpandableListView) findViewById(R.id.categoryList);
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listHeader, listItem);
        listView.setAdapter(listAdapter);
        
    }

    private void prepareListData() {
        listHeader = new ArrayList<String>();
        listItem = new HashMap<String, List<String>>();
        listHeader.add("Electronics");
        listHeader.add("Furniture");
        listHeader.add("Clothing");


        List<String> electronics = new ArrayList<String>();
        electronics.add("Television");
        electronics.add("Microwave");


        List<String> furniture = new ArrayList<String>();
        furniture.add("Chairs");
        furniture.add("Tables");

        List<String> clothing = new ArrayList<String>();
        clothing.add("Jeans");
        clothing.add("T-Shirt");

        listItem.put(listHeader.get(0), electronics);
        listItem.put(listHeader.get(1), furniture);
        listItem.put(listHeader.get(2), clothing);
    }
}
