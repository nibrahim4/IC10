package com.example.ic10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.LogDescriptor;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.grpc.internal.LogExceptionRunnable;

public class MainActivity extends AppCompatActivity {
    public Button btn_AddMovie;
    public boolean isAddButtonClicked = false;
    public Button btn_EditMovie;
    public Button btn_DeleteMovie;
    public Button btn_MoviesByYear;
    public Button btn_MoviesByRating;
    public ArrayList<Movie> movieList = new ArrayList<Movie>();
    public ArrayList<String> nameList = new ArrayList<String>();
    public static final String MOVIE_KEY = "movies";
    public static final int REQ_CODE = 5;
    public Bundle extrasFromAddMovie = new Bundle();
    public Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("My Favorite Movies");


        btn_AddMovie = findViewById(R.id.btnAddMovie);
        btn_AddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAddButtonClicked = true;
                Intent intentToAddMovie = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivityForResult(intentToAddMovie, REQ_CODE);
            }
        });

        // Access a Cloud Firestore instance from your Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> userMap = new HashMap<>();

        userMap.put("name", "Nia");
        userMap.put("age", 99);

//        db.collection("users").add(userMap);



//        User user = new User("Jack", 22);
//
//        Map<String, Object> userMap = user.toHashMap();
//
         db.collection("users").document("users2")
                 .set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
                 if(task.isSuccessful()){
                     Log.d("demo", "onComplete: " + "succesful");
                 }else{
                     Log.e("demo",  task.getException().toString() );
                 }
             }
         });

//        db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
//                    Log.d("demo", documentSnapshot.getData().toString());
//
//                    User user = new User(documentSnapshot.getData());
//
//                    Log.d("demo", user.toString());
//
//                }
//            }
//        });

        User user1 = new User("test", 1);
        User user2 = new User("test2", 2);
        User user3 = new User("test3", 3);

//        ArrayList<User> users = new ArrayList<>();
//
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);

       // db.collection("usersArrayList").add(users);

//        db.collection("users").orderBy("age")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                        if(task.isSuccessful()){
//                            for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
//                                Log.d("demo", queryDocumentSnapshot.getData().toString());
//                            }
//
//                        }
//
//                    }
//                });

    }
}
