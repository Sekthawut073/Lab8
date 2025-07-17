package com.example.lab3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }

    public static void main(String[] args) {
        Note note1 = new Note();
        note1.createdDate = 17/7/68;
        note1.title = "Last day";
        note1.context = "Coding OOP class and object  bye bye";
        note1.getSummary();

        Note note2 = new Note();
        note2.createdDate = 17/7/68;
        note2.title = "Frat day";
        note2.context = "bye bye";
        note2.getSummary();

        User user1 = new User();
        user1.Id = 073;
        user1.Name = "Sekthawut Phuthasongkran";
        user1.Take_notes();

    }




}