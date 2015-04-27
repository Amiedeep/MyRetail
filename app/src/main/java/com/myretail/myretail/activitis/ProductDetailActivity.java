package com.myretail.myretail.activitis;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.myretail.myretail.R;
import com.myretail.myretail.db_helper.DataBaseHelper;
import com.myretail.myretail.models.Item;
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

//        String name = getIntent().getStringExtra("items");
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance(this);
        Item item = dataBaseHelper.getItem(1l);

        Picasso.with(getApplicationContext()).load(item.getImageUrl()).into(img);
        this.item.setText(item.getName());
    }
}
