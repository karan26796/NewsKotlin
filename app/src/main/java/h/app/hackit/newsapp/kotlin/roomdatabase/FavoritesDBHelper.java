package h.app.hackit.newsapp.kotlin.roomdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import h.app.hackit.newsapp.kotlin.model.Favorites;

/**
 * Created by karan on 4/19/2018.
 */

public class FavoritesDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Favorites.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Favorites";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESC = "des";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_SOURCE = "src";

    public FavoritesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT NOT NULL, " +
                COLUMN_DESC + " TEXT NOT NULL," +
                COLUMN_DATE + " TEXT NOT NULL," +
                COLUMN_URL + " TEXT NOT NULL," +
                COLUMN_SOURCE + " TEXT NOT NULL," +
                COLUMN_IMAGE + " TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public boolean newFavorite(Favorites favorites) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, favorites.getTitle());
        values.put(COLUMN_DESC, favorites.getDesc());
        values.put(COLUMN_IMAGE, favorites.getImgUrl());
        values.put(COLUMN_DATE, favorites.getDate());
        values.put(COLUMN_SOURCE, favorites.getSource());
        values.put(COLUMN_URL, favorites.getUrl());

        long result = database.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else return true;

    }

    public List<Favorites> favoritesList() {
        List<Favorites> favorites = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Favorites favorites1;

        if (cursor.moveToFirst()) {
            do {
                favorites1 = new Favorites();

                favorites1.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                favorites1.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                favorites1.setDesc(cursor.getString(cursor.getColumnIndex(COLUMN_DESC)));
                favorites1.setImgUrl(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                favorites1.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL)));
                favorites1.setSource(cursor.getString(cursor.getColumnIndex(COLUMN_SOURCE)));

                favorites.add(favorites1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return favorites;
    }

    public boolean deleteFavorite(long id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE _id='" + id + "'");
        return true;
    }

    /*public void updateNote(long noteId, Note updatedNote) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  " + TABLE_NAME + " SET text ='" + updatedNote.getText() + "', time ='" + updatedNote.getTimestamp() + "', image ='" + updatedNote.getImage() + "'  WHERE _id='" + noteId + "'");
    }*/
}
