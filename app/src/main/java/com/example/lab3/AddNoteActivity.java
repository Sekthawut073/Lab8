package com.example.lab3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddNoteActivity extends AppCompatActivity {
    Button addButton;
    Button addButton2;
    EditText title,textContent,EditName,ID;
    TextView display;
    Button addCheck;
    TextView display1;
    TextView display2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addButton = findViewById(R.id.back);//event source
        addButton.setOnClickListener(new View.OnClickListener() {//event Listener
            @Override
            public void onClick(View v) {//event handler
                System.out.println("Click!!!!");
                Intent addBack = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(addBack);
            }
        });
        //Add
        addButton2 = findViewById(R.id.Add);
        title = findViewById(R.id.editTextText);
        textContent = findViewById(R.id.editTextText2);
        display = findViewById(R.id.textView3);
        addCheck = findViewById(R.id.Check);
        display1 = findViewById(R.id.textView4);
        display2 = findViewById(R.id.textView6);
        EditName = findViewById(R.id.editTextText3);
        ID = findViewById(R.id.editTextText4);

        addButton2.setOnClickListener(new View.OnClickListener() {//event listener
            @Override
            public void onClick(View view) {
                //get data from (EditText)
                String strOfTitle = title.getText().toString();
                String strOfContent = textContent.getText().toString();
                String strOfDate = new Date().toString();
                String strOName = EditName.getText().toString();
                String strOID = ID.getText().toString();
                //set data to Text clas
                TextNote note1 = new TextNote();
                note1.setTitle(strOfTitle);

                note1.setTextContent(strOfContent);

                note1.createdDate = strOfDate;
                User user1 = new TextUser() ;
                user1.setName(strOName);
                user1.setID(strOID);
                //show note on TextView
                display.setText(note1.getSummary());
                display2.setText(user1.getSummary());
                //OOP-en
                NoteEntity entity = NoteMapper.toEntity(note1);
                //add data
                Context context = view.getContext();
                Executors.newSingleThreadExecutor().execute(() -> {
                    AppDatabase.getInstance(context).noteDao().insert(entity);

                });
            }

        });
        addCheck.setOnClickListener(new View.OnClickListener() {//event listener
            @Override
            public void onClick(View view) {
                String strOfTitle = title.getText().toString();
                String stOfDate = new Date().toString();
                CheckLisNote note1 = new CheckLisNote() ;
                note1.setTitle(strOfTitle);
                note1.createdDate = stOfDate;
                display1.setText(note1.getSummary());


            }
        });

    }
}
