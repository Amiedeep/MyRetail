package com.myretail.myretail.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.myretail.myretail.Models.Item;
import com.myretail.myretail.R;
import com.myretail.myretail.adapter.MyCartAdapter;
import com.myretail.myretail.db_helper.DataBaseHelper;

import java.util.List;


public class MyCartActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_cart);

        ListView myCartListView = (ListView)findViewById(R.id.myCartList);
        List<Item> cartItems = DataBaseHelper.getInstance(this).getCartItems();
        MyCartAdapter adapter = new MyCartAdapter(this, cartItems);
        myCartListView.setAdapter(adapter);
    }
}