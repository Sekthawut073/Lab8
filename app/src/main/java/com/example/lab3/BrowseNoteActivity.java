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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrowseNoteActivity extends AppCompatActivity {

    Button addsearch,back2;
    ProgressBar progressBar2;
    TextView display3;
    EditText editTextText5;;
    TextView showNote,showNoteFromAPI;


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
        showNote=findViewById(R.id.textView7);
        showNoteFromAPI=findViewById(R.id.textView8);

        //load data from db
        Executors.newSingleThreadExecutor().execute(()->{
            List<NoteEntity> entities=AppDatabase.getInstance(this).noteDao().getAll();
            List<Note> notes=new ArrayList<>();
            for (NoteEntity e : entities){
                notes.add(NoteMapper.fromEntity(e));
            }
            //display oo UI thread
            runOnUiThread(()->{
                StringBuilder sb =new StringBuilder();
                for (Note n : notes){
                    sb.append(n.getSummary()).append("\n");
                }
                showNote.setText(sb.toString());
            });
        });
        //load data from API
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService=retrofit.create(ApiService.class);
        Call<List<TextNote>> call =apiService.getTextNotes();
         call.enqueue(new Callback<List<TextNote>>() {
             @Override
             public void onResponse(Call<List<TextNote>> call, Response<List<TextNote>> response) {
               if (!response.isSuccessful()){
                   showNoteFromAPI.setText("Error Code:"+ response.code());
                   return;
               }
               List<TextNote> notes=response.body();
               StringBuilder builder =new StringBuilder();
               for (TextNote n : notes){
                   builder.append("Title:").append(n.getTitle()).append("\n")
                           .append("Body:").append(n.getTextContent()).append("\n\n");
               }
               showNoteFromAPI.setText(builder.toString());
             }

             @Override
             public void onFailure(Call<List<TextNote>> call, Throwable t) {
                 showNoteFromAPI.setText("Failed:"+ t.getMessage());
             }
         });



        back2 = findViewById(R.id.button3);//event source
        back2.setOnClickListener(new View.OnClickListener() {//event Listener
            @Override
            public void onClick(View v) {//event handler
                Intent addBack = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(addBack);
            }
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