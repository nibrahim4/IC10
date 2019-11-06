package com.example.ic10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class AddMovieActivity extends AppCompatActivity {


    public Button btn_CreateMovie;
    public EditText et_Name;
    public EditText ml_description;
    public Spinner sp_genre;
    public EditText et_year;
    public EditText et_imdb;
    public SeekBar sb_rating;
    public TextView tv_rating;
    public TextView tv_hidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setTitle("Add Movie");

        btn_CreateMovie = findViewById(R.id.btn_SaveChanges);
        et_Name = findViewById(R.id.etName_Edit);
        ml_description = findViewById(R.id.ml_Description_Edit);
        sp_genre = findViewById(R.id.sp_genre_Edit);
        sb_rating = findViewById(R.id.sb_rating_Edit);
        et_year = findViewById(R.id.et_year_Edit);
        et_imdb = findViewById(R.id.et_imdb_Edit);
        tv_rating = findViewById(R.id.tv_rating_Edit);
        tv_hidden = findViewById(R.id.tv_hidden);

        final String[] selectedGenre = {""};

        sb_rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_rating.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sp_genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedGenre[0] = sp_genre.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        btn_CreateMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isErrorThrown = false;

                String enteredName = et_Name.getText().toString();
                String enteredDescription = ml_description.getText().toString();
                String enteredYear = et_year.getText().toString();
                String enteredIMDB = et_imdb.getText().toString();
                String selectedRating = tv_rating.getText().toString();

                Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

                boolean specialCharDetected = regex.matcher(enteredName).find();

                if(enteredName.equals("") || specialCharDetected){
                    et_Name.setError("Please enter a valid name.");
                    isErrorThrown = true;
                }

                if(enteredDescription.equals("")){
                    ml_description.setError("Please enter a valid description");
                    isErrorThrown = true;
                }

                if(enteredYear.equals("") || enteredYear.length() < 4 ){
                    et_year.setError("Year must have 4 characters.");
                    isErrorThrown = true;
                }

                if (enteredIMDB.equals("")){
                    et_imdb.setError("Please enter a valid IMDB.");
                    isErrorThrown = true;
                }

                if (selectedRating.equals("") || selectedRating == null || selectedRating.equals("0")){
                    Toast.makeText(AddMovieActivity.this, "Please select a rating!", Toast.LENGTH_LONG).show();
                    isErrorThrown = true;
                }
                Log.d("TEST", "Genre: " + selectedGenre[0]);
                if(selectedGenre[0].equals("Select")){
                    isErrorThrown = true;
                    tv_hidden.setError("Please select a genre");
                }

                if(!isErrorThrown ){
                    int movieId =  Movie.createMovieId();

                        Log.d("TEST", "Movie Id " + movieId);

                    Movie movie = new Movie(movieId, enteredName, enteredDescription, selectedGenre[0], Integer.parseInt(selectedRating), Integer.parseInt(enteredYear), enteredIMDB);

                    Bundle sentData = new Bundle();
                    sentData.putSerializable("addedMovie",movie);
                    Intent intent = new Intent(AddMovieActivity.this, MainActivity.class);
                    intent.putExtra(MainActivity.MOVIE_KEY, sentData);

                    setResult(AddMovieActivity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
