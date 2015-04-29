package com.myretail.myretail.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.myretail.myretail.Models.Item;
import com.myretail.myretail.R;
import com.myretail.myretail.db_helper.DataBaseHelper;


public class ProductDetailActivity extends Activity {
    private TextView item;
    private TextView price;
    private ImageView image;
    private TextView detail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        item = (TextView) findViewById(R.id.item);
        price = (TextView)findViewById(R.id.price);
        image = (ImageView) findViewById(R.id.image);
        detail = (TextView) findViewById(R.id.detail);

        Long itemId = getIntent().getLongExtra("itemId", 0l);
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance(this);
        Item item = dataBaseHelper.getItem(itemId);

        image.setImageBitmap(item.getImage());
        this.item.setText(item.getName());
        price.setText(item.getPrice().toString());
        detail.setText(item.getDetail());
    }
}
