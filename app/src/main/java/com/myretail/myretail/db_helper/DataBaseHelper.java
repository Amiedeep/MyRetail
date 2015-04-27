package com.myretail.myretail.db_helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myretail.myretail.models.Item;

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
        System.out.println("Creating database");
        database.execSQL("drop table items");
        database.execSQL("create table items(" +
                "id INT PRIMARY KEY NOT NULL," +
                "name TEXT," +
                "price REAL," +
                "detail TEXT," +
                "image_url TEXT," +
                "category_id INT" +
                ")");
        System.out.println("creating table");
        database.execSQL("insert into items values(1, 'T.V', 3000, 'awesome T.V', 'http://i.imgur.com/DvpvklR.png', 10)");
    }

    public Item getItem(Long id) {
        Cursor item = database.query("items", new String[]{"id", "name", "image_url", "detail", "category_id", "price"}, "id=" + id.intValue(), null, null, null, null, null);
        if (item == null) return null;

        item.moveToNext();
        String name = item.getString(item.getColumnIndex("name"));
        String imageUrl = item.getString(item.getColumnIndex("image_url"));
        String categoryId = item.getString(item.getColumnIndex("category_id"));
        String detail = item.getString(item.getColumnIndex("detail"));
        String price = item.getString(item.getColumnIndex("price"));

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
