package com.myretail.myretail.db_helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.myretail.myretail.Models.Item;
import com.myretail.myretail.R;
import com.myretail.myretail.tables.CategoryTable;
import com.myretail.myretail.tables.ItemTable;

import java.io.ByteArrayOutputStream;


public class DataBaseHelper {

    private final String DATA_BASE = "retail.db";
    private final Integer DB_VERSION = 1;
    private Context context;
    private SQLiteDatabase database;
    private static DataBaseHelper dataBaseHelper;

    public DataBaseHelper(Context baseContext) {
        this.context = baseContext;
    }

    public static DataBaseHelper getInstance(Context context){
        if(dataBaseHelper == null){
            dataBaseHelper = new DataBaseHelper(context);
        }

        return dataBaseHelper;
    }

    public void setUpDB() {
        database = new DataBaseOpenHelper(this.context).getWritableDatabase();
        createAndSeedCategoriesTable();
        createAndSeedItemsTable();
    }

    public Cursor getCategoriesCursor() {
        String query = "SELECT * FROM " + CategoryTable.TABLE_NAME;
        return database.rawQuery(query, null);
    }

    public Cursor getItemsCursor(Long categoryId) {
        return database.query(ItemTable.TABLE_NAME, ItemTable.ALL_COLUMNS, ItemTable.CATEGORY_ID + "=" + categoryId.intValue(), null, null, null, null, null);
    }

    public Item getItem(Long id) {
        Cursor item = database.query(ItemTable.TABLE_NAME, ItemTable.ALL_COLUMNS, ItemTable.ID + "=" + id.intValue(), null, null, null, null, null);
        if (item == null) return null;

        item.moveToNext();
        String name = item.getString(item.getColumnIndex(ItemTable.NAME));
        byte[] image = item.getBlob(item.getColumnIndex(ItemTable.IMAGE));
        Long categoryId = item.getLong(item.getColumnIndex(ItemTable.CATEGORY_ID));
        String detail = item.getString(item.getColumnIndex(ItemTable.DETAIL));
        String price = item.getString(item.getColumnIndex(ItemTable.PRICE));

        return new Item(id, name, detail, price, image, categoryId);
    }

    private void createAndSeedItemsTable() {
        database.execSQL(ItemTable.DROP_QUERY);
        database.execSQL(ItemTable.CREATE_QUERY);

        insertItem(1l, "T.V", "3000", "LCD TV", imageToByteArray(R.drawable.tv), 10l);
        insertItem(2l, "Microwave", "4000", "microwave", imageToByteArray(R.drawable.microwave), 10l);

        insertItem(3l, "Chair", "1000", "Chair", imageToByteArray(R.drawable.chair), 20l);
        insertItem(4l, "Table", "5000", "Table", imageToByteArray(R.drawable.table), 20l);

        insertItem(5l, "T-Shirt", "800", "t-shirt", imageToByteArray(R.drawable.t_shirt), 30l);
        insertItem(6l, "Jeans", "1500", "jeans", imageToByteArray(R.drawable.jeans), 30l);
    }

    private void insertItem(Long id, String name, String price, String detail, byte[] image, Long categoryId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemTable.NAME, name);
        contentValues.put(ItemTable.ID, id);
        contentValues.put(ItemTable.PRICE, price);
        contentValues.put(ItemTable.DETAIL, detail);
        contentValues.put(ItemTable.IMAGE, image);
        contentValues.put(ItemTable.CATEGORY_ID, categoryId);

        database.insert(ItemTable.TABLE_NAME, null, contentValues);
    }

    private byte[] imageToByteArray(int imageResource) {
        Bitmap b = BitmapFactory.decodeResource(this.context.getResources(), imageResource);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }

    private void createAndSeedCategoriesTable() {
        database.execSQL(CategoryTable.DROP_QUERY);
        database.execSQL(CategoryTable.CREATE_QUERY);

        database.execSQL("insert into " + CategoryTable.TABLE_NAME  + " values(10, 'Electronics')");
        database.execSQL("insert into " + CategoryTable.TABLE_NAME  + " values(20, 'Furniture')");
        database.execSQL("insert into " + CategoryTable.TABLE_NAME  + " values(30, 'Clothes')");
    }

    private class DataBaseOpenHelper extends SQLiteOpenHelper {

        DataBaseOpenHelper(Context context) {
            super(context, DATA_BASE, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onCreate(db);
        }
    }
}
