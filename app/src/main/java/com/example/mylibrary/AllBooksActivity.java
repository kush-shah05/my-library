package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
      private RecyclerView booksrecyclerView;
        private BooksRecViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        booksrecyclerView=findViewById(R.id.booksrecyclerview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter=new BooksRecViewAdapter(this,"allBooks");
        adapter.setBooks(Utils.getObj(this).getAllbooks());
        booksrecyclerView.setAdapter(adapter);


        booksrecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}