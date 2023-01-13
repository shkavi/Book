package com.example.book;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input,author_input,pages_input;
    Button update_button;
    String id,title,author,pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        title_input=findViewById(R.id.title_input2);
        author_input=findViewById(R.id.author_input2) ;
        pages_input=findViewById(R.id.pages_input2);
        getAndSetIntentData();
        update_button=findViewById(R.id.update_button);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyData myDB=new MyData(UpdateActivity.this);
                myDB.updateData(id,title,author,pages);

            }
        });



    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")&& getIntent().hasExtra("title")&& getIntent().hasExtra("author")&&getIntent().hasExtra("pages")){
          //getting the data
            id=getIntent().getStringExtra("id");
            title=getIntent().getStringExtra("title");
            author=getIntent().getStringExtra("author");
            pages=getIntent().getStringExtra("pages");
            // setting the data
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);

        }else{
            Toast.makeText(this, "NoData", Toast.LENGTH_SHORT).show();
        }
    }
}