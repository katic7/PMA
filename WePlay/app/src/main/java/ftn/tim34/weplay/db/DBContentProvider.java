package ftn.tim34.weplay.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ftn.tim34.weplay.model.GameRoom;

public class DBContentProvider extends ContentProvider {

    private GamingRoomSQLHelper database;

    private static final int GR = 10;
    private static final int GR_ID = 20;

    private static final String AUTHORITY = "ftn.tim34.weplay";

    private static final String GR_PATH = "gr";

    public static final Uri CONTENT_URI_GR = Uri.parse("content://" + AUTHORITY + "/" + GR_PATH);

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, GR_PATH, GR);
        sURIMatcher.addURI(AUTHORITY, GR_PATH + "/#", GR_ID);

    }

    @Override
    public boolean onCreate() {
        database = new GamingRoomSQLHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exist
        //checkColumns(projection);
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case GR_ID:
                // Adding the ID to the original query
                queryBuilder.appendWhere(GamingRoomSQLHelper.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                //$FALL-THROUGH$
            case GR:
                // Set the table
                queryBuilder.setTables(GamingRoomSQLHelper.TABLE_GR);
            /*case GR_FALSE:
                // not sent
                queryBuilder.appendWhere(GamingRoomSQLHelper.COLUMN_SYNCSTATUS + "="
                        + "false");*/
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri retVal = null;
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case GR:
                id = sqlDB.insert(GamingRoomSQLHelper.TABLE_GR, null, values);
                retVal = Uri.parse(GR_PATH + "/" + id);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return retVal;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        int rowsDeleted = 0;
        switch (uriType) {
            case GR:
                rowsDeleted = sqlDB.delete(GamingRoomSQLHelper.TABLE_GR,
                        selection,
                        selectionArgs);
                break;
            case GR_ID:
                String idPet = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(GamingRoomSQLHelper.TABLE_GR,
                            GamingRoomSQLHelper.COLUMN_ID + "=" + idPet,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(GamingRoomSQLHelper.TABLE_GR,
                            GamingRoomSQLHelper.COLUMN_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        int rowsUpdated = 0;
        switch (uriType) {
            case GR:
                rowsUpdated = sqlDB.update(GamingRoomSQLHelper.TABLE_GR,
                        values,
                        selection,
                        selectionArgs);
                break;
            case GR_ID:
                String idGr = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(GamingRoomSQLHelper.TABLE_GR,
                            values,
                            GamingRoomSQLHelper.COLUMN_ID + "=" + idGr,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(GamingRoomSQLHelper.TABLE_GR,
                            values,
                            GamingRoomSQLHelper.COLUMN_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    public void delete(GameRoom gr){
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        String table = GamingRoomSQLHelper.TABLE_GR;
        String whereClause = "_id=?";
        String[] whereArgs = new String[] { String.valueOf(gr.getId()) };
        sqlDB.delete(table, whereClause, whereArgs);
    }
}
