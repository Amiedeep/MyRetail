package com.myretail.myretail.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myretail.myretail.Models.Item;
import com.myretail.myretail.R;
import com.myretail.myretail.db_helper.DataBaseHelper;
import com.myretail.myretail.db_helper.DatabaseManager;


public class ProductDetailActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        TextView item1 = (TextView) findViewById(R.id.item);
        TextView price = (TextView) findViewById(R.id.price);
        ImageView image = (ImageView) findViewById(R.id.image);
        TextView detail = (TextView) findViewById(R.id.detail);
        Button addToCart = (Button) findViewById(R.id.add_to_cart_button);

        Long itemId = getIntent().getLongExtra("itemId", 0l);
        final DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance(this);
        final Item item = dataBaseHelper.getItem(itemId);

        image.setImageBitmap(item.getImage());
        item1.setText(item.getName());
        price.setText("Rs. " + item.getPrice().toString());
        detail.setText(item.getDetail());

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.addItemToCart(item.getId());
                Toast.makeText(getApplicationContext(), "Item is added to cart Successfully", Toast.LENGTH_SHORT).show();
                DatabaseManager.backupDB();
            }
        });
    }


}