package com.example.mylibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnallbooks,btncurrentlybooks,btnalreadyread,btnabout,btnwishlist,btnfavbook;
    //private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       initviews();
       btnallbooks.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             //  Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(),AllBooksActivity.class));
                //Intent intent=new Intent(MainActivity.this,AllBooksActivity.class);
               // startActivity(intent);
           }

       });
       btnalreadyread.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,Alreadyreadactivity.class);
               startActivity(intent);
           }
       });
       btnwishlist.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,Wanttoreadactivity.class);
               startActivity(intent);
           }
       });
       btncurrentlybooks.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,Currentlyreadingactivity.class);
               startActivity(intent);
           }
       });
       btnfavbook.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,Favouritebooksactivity.class);
               startActivity(intent);
           }
       });
       btnabout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
               builder.setTitle(getString(R.string.app_name));
               builder.setMessage("Designed and developed by kush at home \n "+"check my application for more awesome stuff");
               builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(MainActivity.this,Webviewactivity.class);
                        intent.putExtra("url","https://www.google.com/");
                        startActivity(intent);
                   }
               });
               builder.setNegativeButton("dismiss", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });
               builder.setCancelable(false);
               builder.create().show();
           }
       });
       //if user first come on already read book page ,initially it is empty.
       Utils.getObj(this);
    }

    private void initviews() {
        btnallbooks=findViewById(R.id.btnAllBooks);
        btncurrentlybooks=findViewById(R.id.btnCurrRead);
        btnalreadyread=findViewById(R.id.btnAlreadyReadBooks);
        btnabout=findViewById(R.id.btnAbout);
        btnwishlist=findViewById(R.id.btnWantToReadBooks);
        btnfavbook=findViewById(R.id.btnFavourite);

    }
}