package com.example.androidproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.androidproject.classes.Personne;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Personne.db";

    // PERSONNEtable name
    private static final String TABLE_PERSONNE= "personne";



    // PERSONNETable Columns names
    private static final String COLUMN_PERSONNE_ID = "personne_id";
    private static final String COLUMN_PERSONNE_NAME = "personne_name";
    private static final String COLUMN_PERSONNE_EMAIL = "personne_email";
    private static final String COLUMN_PERSONNE_PASSWORD = "personne_password";
    private static final String COLUMN_PERSONNE_PHONE = "personne_phone";
    private static final String COLUMN_PERSONNE_BIRTHDAY = "personne_birthday";
    private static final String COLUMN_PERSONNE_ROLE = "personne_role";


 

    // create table sql query
    private String CREATE_PERSONNE_TABLE = "CREATE TABLE " + TABLE_PERSONNE+ "("
            + COLUMN_PERSONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PERSONNE_NAME + " TEXT,"
            + COLUMN_PERSONNE_EMAIL + " TEXT," + COLUMN_PERSONNE_PASSWORD + " TEXT,"  + COLUMN_PERSONNE_PHONE + " TEXT,"
            + COLUMN_PERSONNE_BIRTHDAY + " TEXT," + COLUMN_PERSONNE_ROLE + " TEXT" + ")";

    // drop table sql query
    private String DROP_PERSONNE_TABLE = "DROP TABLE IF EXISTS " + TABLE_PERSONNE;

    /**
     * Constructor
     * 
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DROP_PERSONNE_TABLE);
        db.execSQL(CREATE_PERSONNE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop PERSONNETable if exist
        db.execSQL(DROP_PERSONNE_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create PERSONNErecord
     *
     */
    public void addpersonne(Personne personne) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PERSONNE_NAME, personne.getName());
        values.put(COLUMN_PERSONNE_EMAIL, personne.getEmail());
        values.put(COLUMN_PERSONNE_PASSWORD, personne.getPassword());
        values.put(COLUMN_PERSONNE_BIRTHDAY, personne.getBirthday());
        values.put(COLUMN_PERSONNE_ROLE, personne.getRole());
        values.put(COLUMN_PERSONNE_PHONE,personne.getPhone());

        // Inserting Row
        db.insert(TABLE_PERSONNE, null, values);
        //db.close();
    }

    /**
     * This method is to fetch all PERSONNEand return the list of PERSONNErecords
     *
     * @return list
     */
    public List<Personne> getAllpersonne() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_PERSONNE_ID,
                COLUMN_PERSONNE_NAME,
                COLUMN_PERSONNE_EMAIL,
                COLUMN_PERSONNE_PASSWORD,
                COLUMN_PERSONNE_PHONE,
                COLUMN_PERSONNE_BIRTHDAY,
                COLUMN_PERSONNE_ROLE
        };
        // sorting orders
        String sortOrder =
                COLUMN_PERSONNE_NAME + " ASC";
        List<Personne> personneList = new ArrayList<Personne>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the PERSONNEtable
        /**
         * Here query function is used to fetch records from PERSONNEtable this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT personne_id,personne_name,personne_email,personne_password FROM PERSONNEORDER BY personne_name;
         */
        Cursor cursor = db.query(TABLE_PERSONNE, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Personne personne = new Personne();
                personne.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PERSONNE_ID))));
                personne.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PERSONNE_NAME)));
                personne.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_PERSONNE_EMAIL)));
                personne.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PERSONNE_PASSWORD)));
                personne.setDateBirthday(cursor.getString(cursor.getColumnIndex(COLUMN_PERSONNE_BIRTHDAY)));
                personne.setRole(cursor.getString(cursor.getColumnIndex(COLUMN_PERSONNE_ROLE)));
                personne.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_PERSONNE_PHONE)));
                // Adding PERSONNE record to list
                personneList.add(personne);
            } while (cursor.moveToNext());
        }
        cursor.close();
       // db.close();

        // return PERSONNElist
        return personneList;
    }

    /**
     * This method to update PERSONNErecord
     *
     * @param personne
     */
    public void updatepersonne(Personne personne) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PERSONNE_NAME, personne.getName());
        values.put(COLUMN_PERSONNE_EMAIL, personne.getEmail());
        values.put(COLUMN_PERSONNE_PASSWORD, personne.getPassword());
        values.put(COLUMN_PERSONNE_BIRTHDAY,personne.getBirthday());
        values.put(COLUMN_PERSONNE_PHONE,personne.getPhone());
        values.put(COLUMN_PERSONNE_ROLE,personne.getRole());

        // updating row
        db.update(TABLE_PERSONNE, values, COLUMN_PERSONNE_ID + " = ?",
                new String[]{String.valueOf(personne.getId())});
        db.close();
    }

    /**
     * This method is to delete PERSONNErecord
     *
     * @param personne
     */
    public void deletepersonne(Personne personne) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete PERSONNErecord by id
        db.delete(TABLE_PERSONNE, COLUMN_PERSONNE_ID + " = ?",
                new String[]{String.valueOf(personne.getId())});
        db.close();
    }

    /**
     * This method to check PERSONNEexist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkpersonne(String email) {

        // array of columns to fetch
        // array of columns to fetch
        String[] columns = {
                COLUMN_PERSONNE_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_PERSONNE_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query PERSONNE table with condition
        /**
         * Here query function is used to fetch records from PERSONNEtable this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT personne_id FROM PERSONNEWHERE personne_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_PERSONNE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check PERSONNE exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkpersonne(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_PERSONNE_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_PERSONNE_EMAIL + " = ?" + " AND " + COLUMN_PERSONNE_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query PERSONNEtable with conditions
        /**
         * Here query function is used to fetch records from PERSONNEtable this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT personne_id FROM PERSONNEWHERE personne_email = 'jack@androidtutorialshub.com' AND personne_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_PERSONNE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        //db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}

