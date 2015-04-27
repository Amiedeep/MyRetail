package com.myretail.myretail.activitis;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.myretail.myretail.R;
import com.myretail.myretail.adopters.ExpandableListAdapter;
import com.myretail.myretail.db_helper.DataBaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView listView;
    private List<String> listHeader;
    private HashMap<String, List<String>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance(this.getBaseContext());
        dataBaseHelper.setUpDB();

        listView = (ExpandableListView) findViewById(R.id.categoryList);
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listHeader, listItem);
        listView.setAdapter(listAdapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, ProductDetailActivity.class).putExtra("items",
                                String.valueOf(listAdapter.getChild(groupPosition,childPosition)));
                startActivity(intent);
                return false;
            }
        });

    }


    private void prepareListData()  {

        listHeader = new ArrayList<String>();
        listItem = new HashMap<String,List<String>>();

        listHeader.add("Electronics");
        listHeader.add("Furniture");
        listHeader.add("Clothing");


        List<String> electronics = new ArrayList<>();
        electronics.add("TV");
        electronics.add("MicroWave");


        List<String> furniture = new ArrayList<>();
        furniture.add("Chairs");
        furniture.add("Table");

        List<String> clothing = new ArrayList<>();
        clothing.add("Jeans");
        clothing.add("T-Shirt");

        listItem.put(listHeader.get(0), electronics);
        listItem.put(listHeader.get(1), furniture);
        listItem.put(listHeader.get(2), clothing);
    }

}
