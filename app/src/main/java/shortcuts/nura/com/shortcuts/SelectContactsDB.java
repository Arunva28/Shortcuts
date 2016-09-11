package shortcuts.nura.com.shortcuts;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




/**
 * Created by Arun on 9/11/2016.
 */
public class SelectContactsDB extends SQLiteOpenHelper {

    // database version
    private static final int database_VERSION = 1;
    // database name
    private static  String database_NAME = "Selected_Contacts";
    private static  String table_Contacts = "Contacts";
    private static  String Contact_Name = "Name";
    private static  int Contact_Number = 0;

    private static final String[] COLUMNS = { Contact_Name, String.valueOf(Contact_Number)};

    public SelectContactsDB(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_CONTATCS_TABLE = "CREATE TABLE Contacts ( " + "Name TEXT PRIMARY KEY, " + "Number int PRIMARY KEY)";
        db.execSQL(CREATE_CONTATCS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop books table if already exists
        db.execSQL("DROP TABLE IF EXISTS books");
        this.onCreate(db);
    }

    public void createEntry(Contact contact) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(Contact_Name, contact.getName());
        values.put(String.valueOf(Contact_Number), contact.getNumber());

        // insert book
        db.insert(table_Contacts, null, values);

        // close database transaction
        db.close();
    }

}
