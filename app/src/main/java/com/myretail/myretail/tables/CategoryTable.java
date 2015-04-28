package com.myretail.myretail.tables;

public class CategoryTable {
    public static final String TABLE_NAME = "categories";
    public static final String ID = "id";
    public static final String NAME = "name";

    public static final String DROP_QUERY = "drop table if exists " + TABLE_NAME;
    public static final String CREATE_QUERY = "create table " + TABLE_NAME +"(" +
                ID + " INT PRIMARY KEY NOT NULL," +
                NAME + " TEXT" +
            ")";
}
