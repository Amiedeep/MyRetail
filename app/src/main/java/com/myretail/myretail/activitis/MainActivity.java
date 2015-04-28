package com.myretail.myretail.activitis;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.myretail.myretail.Models.Category;
import com.myretail.myretail.Models.Item;
import com.myretail.myretail.R;
import com.myretail.myretail.adopters.ExpandableListAdapter;
import com.myretail.myretail.db_helper.DataBaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;

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

        List<Category> categories = DataBaseHelper.getInstance(this).getCategories();

        for (Category category : categories) {
            listHeader.add(category.getName());
            List<String> items = new ArrayList<>();

            for (Item item : category.getItems()) {
                items.add(item.getName());
            }

            listItem.put(category.getName(), items);
        }
    }
}
