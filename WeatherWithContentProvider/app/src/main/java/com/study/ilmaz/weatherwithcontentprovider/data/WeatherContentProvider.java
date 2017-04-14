package com.study.ilmaz.weatherwithcontentprovider.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.study.ilmaz.weatherwithcontentprovider.model.tables.CityContract;

public class WeatherContentProvider extends ContentProvider {

    private static final int CITY_URI = 100;
    private WeatherSQLiteOpenHelper openHelper;
    private static String baseContentAuthority;
    private static final String CONTENT_AUTHORITY = "com.study.ilmaz.weatherwithcontentprovider";
    private static Uri baseUri;
    private final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    public WeatherContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        String tableName = getType(uri);
        return db.delete(tableName, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        final int matchCode = mUriMatcher.match(uri);
        switch (matchCode) {
            case CITY_URI:
                return CityContract.TABLE_NAME;
            default:
                throw new IllegalStateException("WRING URI! request = " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        String tableName = getType(uri);
        long id = db.insertWithOnConflict(tableName, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public boolean onCreate() {
        if (getContext() != null) {
            openHelper = new WeatherSQLiteOpenHelper(getContext());
            baseContentAuthority = CONTENT_AUTHORITY;
            baseUri = Uri.parse("content://" + baseContentAuthority);
            mUriMatcher.addURI(baseContentAuthority, CityContract.TABLE_NAME, CITY_URI);
            return true;
        }
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        SQLiteDatabase db = openHelper.getReadableDatabase();
        String tabName = getType(uri);
        cursor = db.query(
                tabName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        if (getContext() != null && getContext().getContentResolver() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        String tableName = getType(uri);
        return db.update(tableName, values, selection,selectionArgs);
    }

    public static Uri getBaseUri(){
        return baseUri;
    }
}
