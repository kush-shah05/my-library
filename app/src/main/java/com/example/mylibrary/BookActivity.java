package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private TextView txtbookname, txtauthor, txtpage, txtdesc, txtlongdesc;
    private Button btnaAddToWanToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavourite;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initviews();


        Intent intent = getIntent();
        if (null != intent) {
            int bookid = intent.getIntExtra("bookId", -1);
            if (bookid != -1) {
                Book incomingbook = Utils.getObj(this).getbookbyID(bookid);
                if (null != incomingbook) {
                    setData(incomingbook);

                    handlealreadyreadbooks(incomingbook);

                    handlewanttoreadbooks(incomingbook);

                    handlecurrentlyreadinbooks(incomingbook);

                    handlefavouritesbooks(incomingbook);

                }
            }
        }
    }

    private void handlefavouritesbooks(final Book book) {
        ArrayList<Book> favouritebooks = Utils.getObj(this).getFavouritebooks();

        boolean exist_in_favourite = false;

        for (Book b : favouritebooks) {

            if (b.getId() == book.getId()) {
                exist_in_favourite = true;
            }
        }
        if (exist_in_favourite) {
            btnAddToFavourite.setEnabled(false);
        } else {
            btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getObj(BookActivity.this).addTofavouritebooks(book)) {
                        Toast.makeText(BookActivity.this, "Book has been added succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, Favouritebooksactivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handlecurrentlyreadinbooks(final Book book) {
        ArrayList<Book> currentlyreadingbooks = Utils.getObj(this).getCurrentlyreadingbooks();

        boolean exist_in_currentlyreading = false;

        for (Book b : currentlyreadingbooks) {

            if (b.getId() == book.getId()) {
                exist_in_currentlyreading = true;
            }
        }
        if (exist_in_currentlyreading) {
            btnAddToCurrentlyReading.setEnabled(false);
        } else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getObj(BookActivity.this).addTocurrentlyreadingbooks(book)) {
                        Toast.makeText(BookActivity.this, "Book has been added succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, Currentlyreadingactivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handlewanttoreadbooks(final Book book) {
        ArrayList<Book> wantoreadbooks = Utils.getObj(this).getWanttoreadbooks();

        boolean exist_in_wanttobooks = false;

        for (Book b : wantoreadbooks) {

            if (b.getId() == book.getId()) {
                exist_in_wanttobooks = true;
            }
        }
        if (exist_in_wanttobooks) {
            btnaAddToWanToRead.setEnabled(false);
        } else {
            btnaAddToWanToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getObj(BookActivity.this).addToWantToreadbooks(book)) {
                        Toast.makeText(BookActivity.this, "Book has been added succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, Wanttoreadactivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void handlealreadyreadbooks(final Book book) {


        ArrayList<Book> alreadyreadbooks = Utils.getObj(this).getAlreadyreadbooks();

        boolean exist_in_alreadyreadbooks = false;

        for (Book b : alreadyreadbooks) {

            if (b.getId() == book.getId()) {
                exist_in_alreadyreadbooks = true;
            }
        }
        if (exist_in_alreadyreadbooks) {
            btnAddToAlreadyRead.setEnabled(false);
        } else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getObj(BookActivity.this).addToalreadyreadbooks(book)) {
                        Toast.makeText(BookActivity.this, "Book has been added succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, Alreadyreadactivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    private void setData(Book book) {

        txtbookname.setText(book.getName());
        txtauthor.setText(book.getAuthor());
        txtpage.setText(String.valueOf(book.getPages()));
        txtdesc.setText(book.getShortdesc());
        txtlongdesc.setText(book.getLongdesc());
        Glide.with(this).asBitmap().load(book.getImgurl()).into(imageView);


    }

    private void initviews() {
        txtbookname = findViewById(R.id.booktxt);
        txtauthor = findViewById(R.id.nametxt);
        txtpage = findViewById(R.id.pagetxt);
        txtdesc = findViewById(R.id.desctxt);
        txtlongdesc = findViewById(R.id.longdesctxt);


        btnaAddToWanToRead = findViewById(R.id.btnaddtowanttoread);
        btnAddToAlreadyRead = findViewById(R.id.btnaddtoalreadyread);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurReading);
        btnAddToFavourite = findViewById(R.id.btnaddtofavourites);

        imageView = findViewById(R.id.imageView);
    }
}