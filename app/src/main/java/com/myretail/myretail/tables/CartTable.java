package com.myretail.myretail.tables;

public class CartTable {
    public static final String TABLE_NAME = "cart";
    public static final String ID = "_id";
    public static final String[] ALL_COLUMNS = new String[]{ ID };

    public static final String DROP_QUERY = "drop table if exists " + TABLE_NAME;
    public static final String CREATE_QUERY = "create table " + TABLE_NAME +"(" + ID + " INT" + ")";
}
