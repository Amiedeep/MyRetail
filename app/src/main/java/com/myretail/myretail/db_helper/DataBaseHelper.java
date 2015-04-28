package com.myretail.myretail.db_helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myretail.myretail.Models.Item;
import com.myretail.myretail.tables.CategoryTable;
import com.myretail.myretail.tables.ItemTable;


public class DataBaseHelper {

    private final String DATA_BASE = "retail.db";
    private static final String DB_PATH = "/data/";
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

    private void createAndSeedItemsTable() {
        database.execSQL(ItemTable.DROP_QUERY);
        database.execSQL(ItemTable.CREATE_QUERY);

        database.execSQL("insert into " + ItemTable.TABLE_NAME  + " values(1, 'T.V', 3000, 'awesome T.V', 'http://i.imgur.com/DvpvklR.png', 10)");
        database.execSQL("insert into " + ItemTable.TABLE_NAME  + " values(2, 'Microwave', 4000, 'awesome T.V', 'http://i.imgur.com/DvpvklR.png', 10)");

        database.execSQL("insert into " + ItemTable.TABLE_NAME  + " values(3, 'Chair', 1000, 'awesome T.V', 'http://i.imgur.com/DvpvklR.png', 20)");
        database.execSQL("insert into " + ItemTable.TABLE_NAME  + " values(4, 'Table', 5000, 'awesome T.V', 'http://i.imgur.com/DvpvklR.png', 20)");

        database.execSQL("insert into " + ItemTable.TABLE_NAME  + " values(5, 'T-Shirt', 800, 'awesome T.V', 'http://i.imgur.com/DvpvklR.png', 30)");
        database.execSQL("insert into " + ItemTable.TABLE_NAME  + " values(6, 'Jeans', 1500, 'awesome T.V', 'http://i.imgur.com/DvpvklR.png', 30)");
    }

    private void createAndSeedCategoriesTable() {
        database.execSQL(CategoryTable.DROP_QUERY);
        database.execSQL(CategoryTable.CREATE_QUERY);

        database.execSQL("insert into " + CategoryTable.TABLE_NAME  + " values(10, 'Electronics')");
        database.execSQL("insert into " + CategoryTable.TABLE_NAME  + " values(20, 'Furniture')");
        database.execSQL("insert into " + CategoryTable.TABLE_NAME  + " values(30, 'Clothes')");
    }

    public Item getItem(Long id) {
        Cursor item = database.query(ItemTable.TABLE_NAME, ItemTable.ALL_COLUMNS, ItemTable.ID + "=" + id.intValue(), null, null, null, null, null);
        if (item == null) return null;

        item.moveToNext();
        String name = item.getString(item.getColumnIndex(ItemTable.NAME));
        String imageUrl = item.getString(item.getColumnIndex(ItemTable.IMAGE_URL));
        String categoryId = item.getString(item.getColumnIndex(ItemTable.CATEGORY_ID));
        String detail = item.getString(item.getColumnIndex(ItemTable.DETAIL));
        String price = item.getString(item.getColumnIndex(ItemTable.PRICE));

        return new Item(id, name, detail, price, imageUrl, categoryId);
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
