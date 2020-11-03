package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY="all_books";
    private static final String ALREADYREAD_BOOKS_KEY="alreadyread_books";
    private static final String WANTOTREAD_BOOKS_KEY="wanttoread_books";
    private static final String FAVORITE_BOOKS_KEY="favorite_books";
    private static final String CURRENTLYREADING_BOOKS_KEY="currentlyreading_books";





    private static Utils obj;
    private SharedPreferences sharedPreferences;


   // private static ArrayList<Book> allbooks;
    //private static ArrayList<Book> alreadyreadbooks;
    //private static ArrayList<Book> currentlyreadingbooks;
    //private static ArrayList<Book> wanttoreadbooks;
    //private static ArrayList<Book> favouritebooks;

    private Utils(Context context) {

        sharedPreferences=context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);
        if (null == getAllbooks()) {

            initData();
        }


        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();

        if (null == getAlreadyreadbooks()) {
            editor.putString(ALREADYREAD_BOOKS_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentlyreadingbooks()) {
            editor.putString(CURRENTLYREADING_BOOKS_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getWanttoreadbooks()) {
            editor.putString(WANTOTREAD_BOOKS_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavouritebooks()) {
            editor.putString(FAVORITE_BOOKS_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {

         ArrayList<Book> books=new ArrayList<>();

        books.add(new Book(1, "Half Girlfriend", "chetan bhagat", 1350, "https://images-na.ssl-images-amazon.com/images/I/712HEn9SNwL.jpg",
                "Written In a Manner where you finish the whole book", "a beautiful work by chetan"));
        books.add(new Book(2, "The Myth of Sisyphus", "Albert Camus", 350, "https://miro.medium.com/max/500/1*DDsOx6D3oe8ZxcA-OTfIDA.jpeg", "long description"
                , "One Of the most influential work of this century ,this is a crucial exposition of existentialist"));

        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        editor.putString(ALL_BOOKS_KEY,gson.toJson(books));
        editor.commit();
    }

    public static Utils getObj(Context context) {
        if (null != obj) {
            return obj;
        } else {
            obj = new Utils(context);
            return obj;
        }

    }

    public ArrayList<Book> getAllbooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null),type);

        return books;
    }

    public ArrayList<Book> getAlreadyreadbooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(ALREADYREAD_BOOKS_KEY,null),type);

        return books;
    }

    public ArrayList<Book> getCurrentlyreadingbooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(CURRENTLYREADING_BOOKS_KEY,null),type);

        return books;
    }

    public ArrayList<Book> getWanttoreadbooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(WANTOTREAD_BOOKS_KEY,null),type);

        return books;
    }

    public ArrayList<Book> getFavouritebooks() {
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS_KEY,null),type);

        return books;
    }

    public Book getbookbyID(int id) {
        ArrayList<Book> books=getAllbooks();
        if (null != books){
            for (Book b : books) {
                if (b.getId() == id) {
                    return b;
                }

            }
        }

        return null;
    }

    public boolean addToalreadyreadbooks(Book book){
        ArrayList<Book> books=getAlreadyreadbooks();
        if (null != book){
            if (books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(ALREADYREAD_BOOKS_KEY);
                editor.putString(ALREADYREAD_BOOKS_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToreadbooks(Book book){
        ArrayList<Book> books=getWanttoreadbooks();
        if (null != book){
            if (books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(WANTOTREAD_BOOKS_KEY);
                editor.putString(WANTOTREAD_BOOKS_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addTofavouritebooks(Book book){
        ArrayList<Book> books=getFavouritebooks();
        if (null != book){
            if (books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS_KEY);
                editor.putString(FAVORITE_BOOKS_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;    }

    public boolean addTocurrentlyreadingbooks(Book book){
        ArrayList<Book> books=getCurrentlyreadingbooks();
        if (null != book){
            if (books.add(book)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(CURRENTLYREADING_BOOKS_KEY);
                editor.putString(CURRENTLYREADING_BOOKS_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removefromalreadyread(Book book){
        ArrayList<Book> books=getAlreadyreadbooks();
        if (null != books){
            for (Book b:books){
                if (b.getId()==book.getId()){
                    if (books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(ALREADYREAD_BOOKS_KEY);
                        editor.putString(ALREADYREAD_BOOKS_KEY,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removefromwanttoread(Book book){
        ArrayList<Book> books=getWanttoreadbooks();
        if (null != books){
            for (Book b:books){
                if (b.getId()==book.getId()){
                    if (books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(WANTOTREAD_BOOKS_KEY);
                        editor.putString(WANTOTREAD_BOOKS_KEY,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removefromcurrentlyreading(Book book){
        ArrayList<Book> books=getCurrentlyreadingbooks();
        if (null != books){
            for (Book b:books){
                if (b.getId()==book.getId()){
                    if (books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(CURRENTLYREADING_BOOKS_KEY);
                        editor.putString(CURRENTLYREADING_BOOKS_KEY,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removefromfavourite(Book book){
        ArrayList<Book> books=getFavouritebooks();
        if (null != books){
            for (Book b:books){
                if (b.getId()==book.getId()){
                    if (books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS_KEY);
                        editor.putString(FAVORITE_BOOKS_KEY,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
