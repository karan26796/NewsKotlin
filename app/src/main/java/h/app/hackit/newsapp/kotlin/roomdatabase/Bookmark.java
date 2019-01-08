package h.app.hackit.newsapp.kotlin.roomdatabase;

import android.content.ContentValues;
import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Bookmark.TABLE_NAME)
public class Bookmark {
    /**
     * The name of the Bookmark table.
     */
    static final String TABLE_NAME = "bookmarks";
    /**
     * The name of the ID column.
     */

    public static final String COLUMN_ID = BaseColumns._ID;
    /**
     * The name of the name column.
     */

    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_IMGURL = "imgUrl";
    public static final String COLUMN_SOURCE = "source";
    public static final String COLUMN_DATE = "date";


    /**
     * The unique ID of the cheese.
     */

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;
    /**
     * The name of the cheese.
     */
    @ColumnInfo(name = COLUMN_TITLE)
    public String title;
    @ColumnInfo(name = COLUMN_DESC)
    public String desc;
    @ColumnInfo(name = COLUMN_DATE)
    public String date;
    @ColumnInfo(name = COLUMN_IMGURL)
    public String imgUrl;
    @ColumnInfo(name = COLUMN_URL)
    public String url;
    @ColumnInfo(name = COLUMN_SOURCE)
    public String source;


    /**
     * Create a new {@link Bookmark} from the specified {@link ContentValues}.
     *
     * @param values A {@link ContentValues} that at least contain {@link #COLUMN_TITLE}.
     * @return A newly created {@link Bookmark} instance.
     */
    public static Bookmark fromContentValues(ContentValues values) {
        final Bookmark bookmark = new Bookmark();
        if (values.containsKey(COLUMN_ID)) {
            bookmark.id = values.getAsLong(COLUMN_ID);
        }
        if (values.containsKey(COLUMN_TITLE)) {
            bookmark.title = values.getAsString(COLUMN_TITLE);
        }
        return bookmark;
    }

    /**
     * Dummy data.
     */
    static final String[] CHEESES = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
            "Zamorano", "Zanetti Grana Padano", "Zanetti Parmigiano Reggiano"
    };
}