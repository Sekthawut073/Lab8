package com.example.lab3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button addButton;

    Button addBrowse;
    ImageView logo;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addButton = findViewById(R.id.button);//event source
        logo = findViewById(R.id.imageView);
        logo.setImageResource(R.drawable.hk);

        addButton.setOnClickListener(new View.OnClickListener() {//event Listener
            @Override
            public void onClick(View v) {//event handler
                System.out.println("Click!!!!");
                Intent addNoteAct = new Intent(getApplicationContext(),AddNoteActivity.class);
                startActivity(addNoteAct);
            }
        });
        addBrowse = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        addBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show progressbar
                progressBar.setVisibility(View.VISIBLE);
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
                        progressBar.setVisibility(View.GONE);
                        //go to BrowseNoteActivity
                        Intent BrowseNoteActivity = new Intent(getApplicationContext(), BrowseNoteActivity.class);
                        startActivity(BrowseNoteActivity);
                        finish();
                    });
                }).start();
            }
        }
        );



    }

    //public static void main(String[] args) {
       // Note note1 = new Note();
        //note1.createdDate = 17/7/68;
        //note1.title = "Last day";
       // note1.content = "Coding OOP class and object  bye bye";
       // note1.getSummary();

        //Note note2 = new Note();
       // note2.createdDate = 17/7/68;
       // note2.title = "Frat day";
        //note2.content = "bye bye";
       // note2.getSummary();

       // TextNote textNote1 = new TextNote();
       // textNote1.title = "Mom";
        //textNote1.createdDate ="123";
        //textNote1.textContent = "bye bye";
       // textNote1.setTextContent("bye bye");
       // textNote1.getSummary();

       // User user1 = new TextUser();
       // user1.Id = 073;
        //user1.Name = "Sekthawut Phuthasongkran";
       // user1.getSummary();
    //}
}