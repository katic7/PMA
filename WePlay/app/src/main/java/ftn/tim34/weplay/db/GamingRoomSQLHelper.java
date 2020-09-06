package ftn.tim34.weplay.db;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.model.GamingRoom;

public class GamingRoomSQLHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "pma.db";

    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_GR = "gamingRoomDB";
    public static final String COLUMN_ID = "_id";
    //public static final String COLUMN_SERVER_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_RATING = "ratring";
    public static final String COLUMN_LON = "lon";
    public static final String COLUMN_LAT = "lat";



    private static final String DB_CREATE = "create table "
            + TABLE_GR + "("
            + COLUMN_ID  + " integer primary key  , "       //autoincrement
            + COLUMN_NAME + " text, "
            + COLUMN_RATING + " text, "
            + COLUMN_LAT + " text, "
            + COLUMN_LON + " text "
            +")";

    private static final String DB_DROP = "drop table if exists " + TABLE_GR;

    public GamingRoomSQLHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("onCreate", "Meesage recieved");
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DB_DROP);
        onCreate(db);
    }


}