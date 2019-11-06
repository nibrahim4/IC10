package com.example.ic10;

import java.io.Serializable;

public class Movie implements Serializable{

    private int _id;
    private String _name;
    private String _description;
    private String _genre;
    private int _rating;
    private int _year ;
    private String _imbd;
    private static int _counter =0;

    public String getName() {
        return _name;
    }


    public String getDescription() {
        return _description;
    }



    public String getGenre() {
        return _genre;
    }



    public int getRating() {
        return _rating;
    }



    public int getYear() {
        return _year;
    }



    public String getImbd() {
        return _imbd;
    }


    public void setName(String _name) {
        this._name = _name;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }


    public void setGenre(String _genre) {
        this._genre = _genre;
    }


    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public void setRating(int _rating) {
        this._rating = _rating;
    }

    public void setYear(int _year) {
        this._year = _year;
    }


    public void setIMDB(String _imbd) {
        this._imbd = _imbd;
    }

    public Movie (int id, String name, String description, String genre, int rating, int year, String imbd)
    {
        this._id = id;
        this._name = name;
        this._description = description;
        this._genre = genre;
        this._rating = rating;
        this._year = year;
        this._imbd = imbd;
    }

    public static int createMovieId(){
        return _counter +=1;
    }

}

