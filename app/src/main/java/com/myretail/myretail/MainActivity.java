package com.myretail.myretail;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView listView;
    List<String> listHeader;
    HashMap<String, List<String>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ExpandableListView) findViewById(R.id.categoryList);
        prepareListData();

        listAdapter = new ExpandableListAdaptor(this, listHeader, listItem);

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
