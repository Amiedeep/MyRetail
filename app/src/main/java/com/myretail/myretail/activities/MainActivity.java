package com.myretail.myretail.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;

import com.myretail.myretail.R;
import com.myretail.myretail.db_helper.DataBaseHelper;
import com.myretail.myretail.tables.CategoryTable;
import com.myretail.myretail.tables.ItemTable;

public class MainActivity extends Activity {

    private MyExpandableListAdapter listAdapter;
    private ExpandableListView listView;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        dataBaseHelper = DataBaseHelper.getInstance(this.getBaseContext());
        dataBaseHelper.setUpDB();

        listView = (ExpandableListView) findViewById(R.id.categoryList);

        String[] groupFrom = { CategoryTable.NAME };
        int[] groupTo = { R.id.listHeader };

        String[] childFrom = { ItemTable.NAME };
        int[] childTo = { R.id.listItem };

        Cursor categoriesCursor = dataBaseHelper.getCategoriesCursor();
        categoriesCursor.moveToFirst();

        listAdapter = new MyExpandableListAdapter(categoriesCursor, getApplicationContext(),
                R.layout.list_group, R.layout.list_item, groupFrom, groupTo, childFrom, childTo);

        listView.setAdapter(listAdapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, ProductDetailActivity.class).putExtra("itemId", id);
                startActivity(intent);
                return false;
            }
        });


        Button myCartButton = (Button) findViewById(R.id.my_cart_button);

        myCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, MyCartActivity.class);
                startActivity(intent);
            }
        });
    }

    public class MyExpandableListAdapter extends SimpleCursorTreeAdapter {
        public MyExpandableListAdapter(Cursor cursor, Context context,int groupLayout,
                                       int childLayout, String[] groupFrom, int[] groupTo, String[] childrenFrom,
                                       int[] childrenTo) {
            super(context, cursor, groupLayout, groupFrom, groupTo,
                    childLayout, childrenFrom, childrenTo);
        }

        @Override
        protected Cursor getChildrenCursor(Cursor groupCursor) {
            Cursor childCursor = dataBaseHelper.getItemsCursor(groupCursor.getLong(groupCursor.getColumnIndex(CategoryTable.ID)));
            childCursor.moveToFirst();
            return childCursor;
        }
    }
}
