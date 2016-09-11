package shortcuts.nura.com.shortcuts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Arun on 9/11/2016.
 */
public class SelectContactsDB extends SQLiteOpenHelper {

    // database version
    private static final int database_VERSION = 1;
    // database name
    private static  String database_NAME = "Selected_Contacts";
    private static  String table_Contacts = "Contacts";
    private static  int table_ContactsID = 0;
    private static  String Contact_Name = "Name";
    private static  String Contact_Number = "Number";

    private static final String[] COLUMNS = { Contact_Name, Contact_Number};

    public SelectContactsDB(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_CONTATCS_TABLE = "CREATE TABLE " + table_Contacts + "( " + table_ContactsID + "INT PRIMARY KEY AUTOINCREMENT, "
                + Contact_Name +"TEXT PRIMARY KEY, " + Contact_Number + "TEXT PRIMARY KEY)";
        db.execSQL(CREATE_CONTATCS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop books table if already exists
        db.execSQL("DROP TABLE IF EXISTS books");
        this.onCreate(db);
    }

    public void createEntry(Contact contact) {

        long returnValue = 0;
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(Contact_Name, contact.getName());
        values.put(Contact_Number, contact.getNumber());

        // insert book
        returnValue = db.insert(table_Contacts, null, values);

        // close database transaction
        db.close();
    }

    public List<Contact> ReadAllEntries() {

        List<Contact> Contacts = new LinkedList<Contact>();

        // select book query
        String query = "SELECT  * FROM " + table_Contacts;

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        Contact Contact = null;
        if (cursor.moveToFirst()) {
            do {
                Contact = new Contact();
                Contact.setName(cursor.getString(0));
                Contact.setNumber(cursor.getString(1));


                // Add book to books
                Contacts.add(Contact);
            } while (cursor.moveToNext());
        }
        return Contacts;
    }

}
