package h.app.hackit.newsapp.kotlin.roomdatabase;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BookmarkDao {
    /**
     * Counts the number of cheeses in the table.
     *
     * @return The number of cheeses.
     */

    @Query("SELECT COUNT(*) FROM " + Bookmark.TABLE_NAME)
    int count();

    /**
     * Inserts a cheese into the table.
     *
     * @param bookmark A new bookmark.
     * @return The row ID of the newly inserted cheese.
     */

    @Insert
    long insert(Bookmark bookmark);

    /**
     * Inserts multiple cheeses into the database
     *
     * @param bookmarks An array of new cheeses.
     * @return The row IDs of the newly inserted cheeses.
     */

    @Insert
    long[] insertAll(Bookmark[] bookmarks);

    /**
     * Select all cheeses.
     *
     * @return A {@link Cursor} of all the cheeses in the table.
     */

    @Query("SELECT * FROM " + Bookmark.TABLE_NAME)
    Cursor selectAll();

    /**
     * Select a cheese by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected cheese.
     */

    @Query("SELECT * FROM " + Bookmark.TABLE_NAME + " WHERE " + Bookmark.COLUMN_ID + " = :id")
    Cursor selectById(long id);

    /**
     * Delete a cheese by the ID.
     *
     * @param id The row ID.
     * @return A number of cheeses deleted. This should always be {@code 1}.
     */

    @Query("DELETE FROM " + Bookmark.TABLE_NAME + " WHERE " + Bookmark.COLUMN_ID + " = :id")
    int deleteById(long id);

    /**
     * Update the cheese. The cheese is identified by the row ID.
     *
     * @param bookmark The cheese to update.
     * @return A number of cheeses updated. This should always be {@code 1}.
     */
    @Update
    int update(Bookmark bookmark);
}