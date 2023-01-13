package com.example.book;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input,author_input,pages_input;
    Button update_button,delete_button;
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
        delete_button=findViewById(R.id.delete_button);

        ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setTitle(title);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyData myDB=new MyData(UpdateActivity.this);
                title=title_input.getText().toString().trim();
                author=author_input.getText().toString().trim();
                pages=pages_input.getText().toString().trim();
                myDB.updateData(id,title,author,pages);

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
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
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete"+title+"?");
        builder.setMessage("Are you sure you want to delete"+title+"?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyData myDB=new MyData(UpdateActivity.this);

                myDB.deleteOneRow(id);
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }

}