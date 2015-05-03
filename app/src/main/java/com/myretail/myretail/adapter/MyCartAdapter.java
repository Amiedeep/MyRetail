package com.myretail.myretail.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.myretail.myretail.Models.Item;
import com.myretail.myretail.R;
import com.myretail.myretail.db_helper.DataBaseHelper;
import com.myretail.myretail.tables.CartTable;

public class MyCartAdapter extends SimpleCursorAdapter {
    private Context context;

    public MyCartAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        Cursor c = getCursor();
        LayoutInflater inflater = LayoutInflater.from(context);
        View myCartRowView = inflater.inflate(R.layout.my_cart_row, parent, false);

        long itemId = c.getLong(c.getColumnIndex(CartTable.ITEM_ID));
        final Long cartItemId = c.getLong(c.getColumnIndex(CartTable.ID));

        final DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance(this.context);
        Item item = dataBaseHelper.getItem(itemId);

        TextView itemTextView = (TextView)myCartRowView.findViewById(R.id.my_cart_item);
        TextView itemPriceTextView = (TextView)myCartRowView.findViewById(R.id.my_cart_item_price);
        ImageView itemImageTextView = (ImageView)myCartRowView.findViewById(R.id.my_cart_item_image);
        ImageView deleteItemView = (ImageView)myCartRowView.findViewById(R.id.my_cart_item_delete);
        itemTextView.setText(item.getName());
        itemPriceTextView.setText(item.getPrice().toString());
        itemImageTextView.setImageBitmap(item.getImage());

        deleteItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Delete Item: " + cartItemId.toString(), "");
                dataBaseHelper.deleteCartItem(cartItemId);
                changeCursor(dataBaseHelper.getCartCursor());
                notifyDataSetChanged();
            }
        });

        return myCartRowView;
    }
}
