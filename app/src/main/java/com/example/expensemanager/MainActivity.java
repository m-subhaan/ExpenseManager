package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Float exp[]= calculateExpenses();
        Float val1;
        Float val2;
        Float val3;
        Float val4;
        val1=exp[0];
        val2=exp[1];
        val3=exp[2];
        val4=exp[3];


        TextView textView2= (TextView) findViewById(R.id.txt2);
        textView2.setText(""+ val1+"%");
        textView2.setTypeface(Typeface.SERIF, Typeface.ITALIC);


        TextView textView4= (TextView) findViewById(R.id.txt4);
        textView4.setText(""+ val2+"%");
        textView4.setTypeface(Typeface.SERIF, Typeface.ITALIC);


        TextView textView6= (TextView) findViewById(R.id.txt6);
        textView6.setText(""+val3+"%");
        textView6.setTypeface(Typeface.SERIF, Typeface.ITALIC);


        TextView textView8= (TextView) findViewById(R.id.txt8);
        textView8.setText(""+val4+"%");
        textView8.setTypeface(Typeface.SERIF, Typeface.ITALIC);

        Button b1= (Button) findViewById(R.id.btn1);//add
        Button b2=(Button)findViewById(R.id.btn2);//edit
        Button b3=(Button)findViewById(R.id.btn3);//delete
        Button b4=(Button) findViewById(R.id.btn4);//show All

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),add.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),edit.class);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),delete.class);
                startActivity(i);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),show.class);
                startActivity(i);
            }
        });
    }

    public Float[] calculateExpenses()
    {
        FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_NAME_Amount
        };
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

        Float Total=0.0f;
        while(cursor.moveToNext()) {
            Total = Total + Float.parseFloat(cursor.getString(0)) ;
        }
        cursor.close();
        ///////////////////////////////////////////////////////////////////////////////////
        projection = new String[]{ FeedReaderContract.FeedEntry.COLUMN_NAME_Amount};
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_Category + " = ?";
        String[] selectionArgs = { "Fuel" };


         cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        Float Fuel=0.0f;
        while(cursor.moveToNext()) {
            Fuel = Fuel + Float.parseFloat(cursor.getString(0)) ;
        }
        cursor.close();
///////////////////////////////////////////////////////////////////////////////////
        projection = new String[]{ FeedReaderContract.FeedEntry.COLUMN_NAME_Amount};
         selection = FeedReaderContract.FeedEntry.COLUMN_NAME_Category + " = ?";
         selectionArgs = new String[]{ "Food" };
        sortOrder = BaseColumns._ID + " ASC";
        cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        Float Food=0.0f;
        while(cursor.moveToNext()) {
            Food = Food + Float.parseFloat(cursor.getString(0)) ;
        }
        cursor.close();

        ///////////////////////////////////////////////////////////////////////////////////
        projection = new String[]{ FeedReaderContract.FeedEntry.COLUMN_NAME_Amount};
        selection = FeedReaderContract.FeedEntry.COLUMN_NAME_Category + " = ?";
        selectionArgs = new String[]{ "Education" };
        sortOrder = BaseColumns._ID + " ASC";
        cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        Float Education=0.0f;
        while(cursor.moveToNext()) {
            Education = Education + Float.parseFloat(cursor.getString(0)) ;
        }
        cursor.close();

        ///////////////////////////////////////////////////////////////////////////////////
        projection = new String[]{ FeedReaderContract.FeedEntry.COLUMN_NAME_Amount};
        selection = FeedReaderContract.FeedEntry.COLUMN_NAME_Category + " = ?";
        selectionArgs = new String[]{ "Misc" };
        sortOrder = BaseColumns._ID + " ASC";
        cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        Float Misc=0.0f;
        while(cursor.moveToNext()) {
            Misc = Misc + Float.parseFloat(cursor.getString(0)) ;
        }
        cursor.close();

        Fuel=Fuel/Total;
        Fuel*=100;
        String s = String.format("%.2f", Fuel);
        Fuel=  Float.valueOf(s);

        Food=Food/Total;
        Food*=100;
        s = String.format("%.2f", Food);
        Food=  Float.valueOf(s);

        Education=Education/Total;
        Education*=100;
        s = String.format("%.2f", Education);
        Education=  Float.valueOf(s);

        Misc=Misc/Total;
        Misc*=100;
        s = String.format("%.2f", Misc);
        Misc=  Float.valueOf(s);

        Float[] expenses= new Float[4];
        expenses[0]=Fuel;
        expenses[1]=Food;
        expenses[2]=Education;
        expenses[3]=Misc;
        return expenses;
    }
    }
