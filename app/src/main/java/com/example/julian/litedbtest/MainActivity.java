package com.example.julian.litedbtest;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.support.v7.app.AlertDialog.*;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button btnviewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        btnviewAll = (Button) findViewById(R.id.button);
        viewAll();
    }

    public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getALLData();
                        if (res.getCount() == 0) {
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("SubmissionClassCodeID :" + res.getString(0)+"\n");
                            buffer.append("SubmissionClassCode :" + res.getString(1)+"\n");
                            buffer.append("SubmissionClassCodeDescription :" + res.getString(2)+"\n\n");

                        }
                        // show all data
                        showMessage("Data",buffer.toString());

                    }

                }
        );
    }
    public void showMessage(String title, String Message){
        Builder builder= new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}
