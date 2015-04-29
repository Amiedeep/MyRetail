package com.myretail.myretail.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.myretail.myretail.Models.Item;
import com.myretail.myretail.R;
import com.myretail.myretail.db_helper.DataBaseHelper;


public class ProductDetailActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        TextView item1 = (TextView) findViewById(R.id.item);
        TextView price = (TextView) findViewById(R.id.price);
        ImageView image = (ImageView) findViewById(R.id.image);
        TextView detail = (TextView) findViewById(R.id.detail);

        Long itemId = getIntent().getLongExtra("itemId", 0l);
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance(this);
        Item item = dataBaseHelper.getItem(itemId);

        image.setImageBitmap(item.getImage());
        item1.setText(item.getName());
        price.setText("Rs. " + item.getPrice().toString());
        detail.setText(item.getDetail());
    }
}