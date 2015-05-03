package com.myretail.myretail.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.myretail.myretail.R;
import com.myretail.myretail.adapter.MyCartAdapter;
import com.myretail.myretail.db_helper.DataBaseHelper;


public class MyCartActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_cart);

        ListView myCartListView = (ListView)findViewById(R.id.myCartList);

        String[] from = {};
        int[] to = {};

        MyCartAdapter adapter = new MyCartAdapter(this, R.layout.my_cart, DataBaseHelper.getInstance(this).getCartCursor(), from, to);
        adapter.notifyDataSetChanged();
        myCartListView.setAdapter(adapter);
    }
}