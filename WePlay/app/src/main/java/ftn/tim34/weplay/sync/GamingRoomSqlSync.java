package ftn.tim34.weplay.sync;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ftn.tim34.weplay.db.DBContentProvider;
import ftn.tim34.weplay.db.GamingRoomSQLHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import ftn.tim34.weplay.model.GamingRoom;

public class GamingRoomSqlSync {

    public static void fillDatabase(ArrayList<GamingRoom> gamingRooms, Context context) {
       /* if (fleg == 0 && listPets.size()>0) {
            activity.getContentResolver().delete(DBContentProvider.CONTENT_URI_PET, null, null);
        }*/

        GamingRoomSQLHelper dbHelper = new GamingRoomSQLHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int all = 0;

        {
            for (GamingRoom gr : gamingRooms) {
                all++;
                ContentValues entry = new ContentValues();
                fillContent(gr, entry);

                //nema id znaci nije sa servera-nego dodat offline
                if (gr.getId() == null) {
                    context.getContentResolver().insert(DBContentProvider.CONTENT_URI_GR, entry);
                } else {

                    if (context.getContentResolver().update(Uri.parse(DBContentProvider.CONTENT_URI_GR + "/" + gr.getId()), entry, "", null) == 0) {
                        entry.put(GamingRoomSQLHelper.COLUMN_ID, gr.getId().toString());
                        Log.d("fillDatabase", gr.getName() + " insert " + gr.getId());
                        context.getContentResolver().insert(DBContentProvider.CONTENT_URI_GR, entry);
                    }
                }

            }
        }

        db.close();

    }



    public static ContentValues fillContent(GamingRoom gr, ContentValues entry){
        entry.put(GamingRoomSQLHelper.COLUMN_NAME, gr.getName());
        entry.put(GamingRoomSQLHelper.COLUMN_RATING, Float.toString(gr.getRating()));
        entry.put(GamingRoomSQLHelper.COLUMN_LAT, Double.toString(gr.getLat()));
        entry.put(GamingRoomSQLHelper.COLUMN_LON, Double.toString(gr.getLon()));

        return entry;
    }

    public static List<GamingRoom> getData(Activity activity){
        String[] allColumns = { GamingRoomSQLHelper.COLUMN_ID,
                GamingRoomSQLHelper.COLUMN_NAME, GamingRoomSQLHelper.COLUMN_RATING, GamingRoomSQLHelper.COLUMN_LAT, GamingRoomSQLHelper.COLUMN_LON};
        String[] selectionArgs = {};

        Cursor cursor = activity.getContentResolver().query(DBContentProvider.CONTENT_URI_GR, allColumns, "", selectionArgs,
                null);
        List<GamingRoom> gamingRoom = new ArrayList<>();

        if (cursor != null) {
            cursor.moveToFirst();
            GamingRoom gr = new GamingRoom();
            for (int i = 0; i < cursor.getCount(); i++){
                Long id = Long.parseLong(cursor.getString(0));
                String name = cursor.getString(1);
                float rating = Float.valueOf(cursor.getString(2));
                double lat = Double.valueOf(cursor.getString(3));
                double lon = Double.valueOf(cursor.getString(4));

                GamingRoom gRoom = new GamingRoom(id,name,rating,lat,lon);
                gamingRoom.add(gRoom);
                cursor.moveToNext();
            }
            // always close the cursor
            cursor.close();
        }
        return gamingRoom;
    }
}
