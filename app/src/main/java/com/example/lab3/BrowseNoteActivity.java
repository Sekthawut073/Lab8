package com.example.lab3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BrowseNoteActivity extends AppCompatActivity {

    Button addsearch;
    ProgressBar progressBar2;
    TextView display3;
    EditText editTextText5;;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_browse_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addsearch = findViewById(R.id.addsearch);
        progressBar2 =findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);
        display3 = findViewById(R.id.textView5);
        editTextText5 = findViewById(R.id.editTextText5);
        addsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show progressbar
                progressBar2.setVisibility(View.VISIBLE);
                //create thread
                new Thread(()->{
                    //delay 2 seconds
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    //load data from Db
                    //back to main thread
                    runOnUiThread(()->{
                        progressBar2.setVisibility(View.GONE);
                        display3.setText("No information found ");
                        //go to BrowseNoteActivity
                        //finish();
                    });
                }).start();
            }
        });

    }
}