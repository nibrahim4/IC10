package com.example.ic10;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.content.Intent.EXTRA_TEXT;

public class MoviesbyRatingActivity extends AppCompatActivity {

    public  Bundle extrasFromMain;
    public ArrayList<Movie> moviesFromMain = new ArrayList<Movie>();
    public Button btn_finish_byRating;
    public TextView tv_titleValue_byRating;
    public TextView tv_descriptionValue_byRating;
    public TextView tv_genreValue_byRating;
    public TextView tv_ratingValue_byRating;
    public TextView tv_yearValue_byRating;
    public TextView tv_imdbValue_byRating;
    public ImageView iv_first_byRating;
    public ImageView iv_last_byRating;
    public ImageView iv_previous_byRating;
    public ImageView iv_next_byRating;
    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviesby_rating);
        setTitle("Movies by Rating");

        extrasFromMain = getIntent().getExtras();
        moviesFromMain = (ArrayList<Movie>) extrasFromMain.getSerializable(EXTRA_TEXT);

        tv_titleValue_byRating= findViewById(R.id.tv_movieName_byRating);
        tv_descriptionValue_byRating = findViewById(R.id.ml_descriptionValue_byRating);
        tv_genreValue_byRating = findViewById(R.id.tv_genreValue_byRating);
        tv_imdbValue_byRating = findViewById(R.id.tv_IMDBValue_byRating);
        tv_ratingValue_byRating = findViewById(R.id.tv_ratingValue_byRating);
        tv_yearValue_byRating = findViewById(R.id.tv_yearValue_byRating);

        if (moviesFromMain.size() > 0){
            Collections.sort(moviesFromMain, new Comparator<Movie>() {

                public int compare(Movie m1, Movie m2) {
                    return m2.getRating() - m1.getRating();
                }
            });

            setFieldsForMovie(0);

            iv_first_byRating = findViewById(R.id.iv_first_byRating);
            iv_first_byRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counter =0;
                    setFieldsForMovie(counter);
                }
            });

            iv_next_byRating = findViewById(R.id.iv_next_byRating);
            iv_next_byRating.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    counter += 1;
                    try {
                        setFieldsForMovie(counter);
                    }catch(Exception e){
                        counter =0;
                        setFieldsForMovie(counter);
                    }
                }
            });

            iv_last_byRating = findViewById(R.id.iv_last_byRating);
            iv_last_byRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counter = moviesFromMain.size()-1;
                    setFieldsForMovie(moviesFromMain.size()-1);
                }
            });

            iv_previous_byRating = findViewById(R.id.iv_previous_byRating);
            iv_previous_byRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counter -= 1;
                    try {
                        setFieldsForMovie(counter);
                    }catch(Exception e){
                        counter =moviesFromMain.size() -1;
                        setFieldsForMovie(counter);
                    }
                }
            });

        }else{
            Toast.makeText(MoviesbyRatingActivity.this, "There are no movies in the list. ", Toast.LENGTH_SHORT).show();
        }





        btn_finish_byRating = findViewById(R.id.btn_Finish_byRating);
        btn_finish_byRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void setFieldsForMovie(int index) {

        String rating = moviesFromMain.get(index).getRating() + "/5";
        tv_titleValue_byRating.setText(moviesFromMain.get(index).getName());
        tv_descriptionValue_byRating.setText(moviesFromMain.get(index).getDescription());
        tv_genreValue_byRating.setText(moviesFromMain.get(index).getGenre());
        tv_imdbValue_byRating.setText(moviesFromMain.get(index).getImbd());
        tv_ratingValue_byRating.setText( rating);
        tv_yearValue_byRating.setText(Integer.toString(moviesFromMain.get(index).getYear()));
    }
}
