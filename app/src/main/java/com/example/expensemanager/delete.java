package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Button btnDelete= (Button) findViewById(R.id.btnDel);
        final EditText Delid= (EditText) findViewById(R.id.id);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IdToBeDeleted= Delid.getText().toString();

                FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(delete.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // Define 'where' part of query.
                String selection = BaseColumns._ID + " LIKE ?";
// Specify arguments in placeholder order.
                String selectionArgs[] = {IdToBeDeleted};
// Issue SQL statement.
                int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
                if(deletedRows==0){
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "No Entry Exist with the ID" + IdToBeDeleted, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if(deletedRows>0){
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "Entry with  ID " + IdToBeDeleted + " is Deleted Successfully!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });
    }
}
