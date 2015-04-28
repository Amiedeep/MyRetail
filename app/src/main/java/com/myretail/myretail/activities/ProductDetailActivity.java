package com.myretail.myretail.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.myretail.myretail.Models.Item;
import com.myretail.myretail.R;
import com.myretail.myretail.db_helper.DataBaseHelper;
import com.squareup.picasso.Picasso;


public class ProductDetailActivity extends Activity {
    TextView item;
    TextView cost;
    ImageView img;
    TextView detail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        item = (TextView) findViewById(R.id.item);
        cost = (TextView)findViewById(R.id.cost);
        img = (ImageView) findViewById(R.id.image);
        detail = (TextView) findViewById(R.id.detail);

        Long itemId = getIntent().getLongExtra("itemId", 1l);
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance(this);
        Item item = dataBaseHelper.getItem(itemId);

        Picasso.with(getApplicationContext()).load(item.getImageUrl()).into(img);
        this.item.setText(item.getName());
    }
}
