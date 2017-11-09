package com.book.bookappfirebase.searchActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.book.bookappfirebase.Adapter.MainGenreListAdapter;
import com.book.bookappfirebase.R;


/**
 * Created by kunalmnik95 on 20/10/17.
 */

public class MainGenreActivity extends AppCompatActivity {

    String[] genreStrings = {"Contemporary", "Fantasy", "History", "Horror", "Least Knowns", "Mystery",
            "Non-Fiction", "Romance", "Science Fiction", "Social Issues", "Thriller and Suspense"};

    ListView mainGenreList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_genre_layout);

        mainGenreList = findViewById(R.id.mainGenreList);

        MainGenreListAdapter adapter = new MainGenreListAdapter(MainGenreActivity.this, genreStrings);

        final SharedPreferences preferences = this.getSharedPreferences("GENRE PREF", Context.MODE_PRIVATE);

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, genreStrings);
        mainGenreList.setAdapter(adapter);

        mainGenreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = genreStrings[i];
                Toast.makeText(MainGenreActivity.this, str, Toast.LENGTH_SHORT).show();
                Log.i("THIS IS SAMPLE : ", str);
                Intent intent = new Intent(MainGenreActivity.this, SubGenreActivity.class);
                //intent.putExtra("GENRE NAME", str);
                preferences.edit().putString("GENRE NAME", str).apply();
                startActivity(intent);
            }
        });
    }
}