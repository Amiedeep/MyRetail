package com.myretail.myretail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myretail.myretail.Models.Item;
import com.myretail.myretail.R;

import java.util.List;

public class MyCartAdapter extends BaseAdapter {
    private final Context context;
    private final List<Item> items;

    public MyCartAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View myCartItemView, ViewGroup parent) {
        if (myCartItemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myCartItemView = (View) inflater.inflate(R.layout.my_cart_row, null);
        }

        if(items.size() > 0){
            TextView name = (TextView)myCartItemView.findViewById(R.id.my_cart_item);
            TextView price = (TextView)myCartItemView.findViewById(R.id.my_cart_item_price);
            ImageView image = (ImageView)myCartItemView.findViewById(R.id.my_cart_item_image);

            name.setText(items.get(position).getName());
            price.setText(items.get(position).getPrice().toString());
            image.setImageBitmap(items.get(position).getImage());
        }

        return myCartItemView;
    }
}
