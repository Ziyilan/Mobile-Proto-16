package jason.hw3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zlan on 9/26/16.
 */
public class toDoHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDo.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + todoSchema.todoEntry.TABLE_NAME + " (" +
                    todoSchema.todoEntry._ID + " INTEGER PRIMARY KEY," +
                    todoSchema.todoEntry.columnOne + TEXT_TYPE + COMMA_SEP +
                    todoSchema.todoEntry.columnTwo + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + todoSchema.todoEntry.TABLE_NAME;

    public toDoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void dropOld(SQLiteDatabase db){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

//    status for todo 0:not finished 1:finished -1:deleted
    public void addToDo(SQLiteDatabase db, String content){
        db.execSQL("INSERT INTO " + todoSchema.todoEntry.TABLE_NAME + "(" +
                todoSchema.todoEntry.columnOne + COMMA_SEP +
                todoSchema.todoEntry.columnTwo + ") VALUES (" + content + COMMA_SEP + 0 + ")" );
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
