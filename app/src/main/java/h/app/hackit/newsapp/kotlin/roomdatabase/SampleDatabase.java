package h.app.hackit.newsapp.kotlin.roomdatabase;

import android.content.Context;

import com.google.common.annotations.VisibleForTesting;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Bookmark.class}, version = 1)
public abstract class SampleDatabase extends RoomDatabase {
    /**
     * @return The DAO for the Cheese table.
     */

    @SuppressWarnings("WeakerAccess")
    public abstract BookmarkDao bookmarkDao();

    /** The only instance */
    private static SampleDatabase sInstance;

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */

    public static synchronized SampleDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), SampleDatabase.class, "ex")
                    .build();
            sInstance.populateInitialData();
        }
        return sInstance;
    }

    /**
     * Switches the internal implementation with an empty in-memory database.
     *
     * @param context The context.
     */

    @VisibleForTesting
    public static void switchToInMemory(Context context) {
        sInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                SampleDatabase.class).build();
    }

    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
    private void populateInitialData() {
        if (bookmarkDao().count() == 0) {
            Bookmark bookmark = new Bookmark();
            beginTransaction();
            try {
                for (int i = 0; i < Bookmark.CHEESES.length; i++) {
                    bookmark.title = Bookmark.CHEESES[i];
                    bookmarkDao().insert(bookmark);
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }
}
