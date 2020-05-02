package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class show extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_Amount,
                FeedReaderContract.FeedEntry.COLUMN_NAME_Category,
                FeedReaderContract.FeedEntry.COLUMN_NAME_Date
        };

// Filter results WHERE "title" = 'My Title'
      //  String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        //String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                BaseColumns._ID + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        TextView textView= (TextView) findViewById(R.id.showDataHere);
        while(cursor.moveToNext()) {
            String tempstr = " ";
            tempstr = " \n " + tempstr + "\t" + cursor.getString(0) + "\t\t\t"
                    + cursor.getString(1) + "\t\t\t" + cursor.getString(2)+
                    "\t\t\t" + cursor.getString(3) +"\n";
            textView.append(tempstr);
            }
        cursor.close();
        }
}
