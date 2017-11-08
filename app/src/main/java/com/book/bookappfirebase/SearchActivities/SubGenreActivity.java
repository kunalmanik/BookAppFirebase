package com.book.bookappfirebase.SearchActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.book.bookappfirebase.Adapter.MainGenreListAdapter;
import com.book.bookappfirebase.MainActivity;
import com.book.bookappfirebase.R;

/**
 * Created by kunalmnik95 on 8/11/17.
 */

public class SubGenreActivity extends AppCompatActivity {

    String mainGenreStr;
    String [] tempStr;

    ListView subGenreList;

    String[] genreStringsContemporary = {"Best Contemporary of all Time", "Stand-Alones",
            "Adult Contemporary", "Something from New Authors"};

    String[] genreStringsSciFi = {"Dystopian and Post-Apocalyptic Fiction", "Best of All Time",
            "Kick-Ass Female characters", "Best Books of 21st Century", "Best Steampunk Books",
            "From the new Stars", "Under-Rated Sci-Fi", "Amazon's Must Read Books", "This is the end",
            "Female Protagonist", "Science Fiction and Fantasy"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_genre_layout);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                mainGenreStr = null;
            }
            else {
                mainGenreStr = extras.getString("GENRE NAME");
            }
        }
        else {
            mainGenreStr = (String) savedInstanceState.getSerializable("GENRE NAME");
        }

        if (mainGenreStr != null)
            switch (mainGenreStr)
            {
                case "Contemporary" :
                    tempStr = genreStringsContemporary;
                    break;

                case "Fantasy" :
                    break;

                case "History" :
                    break;

                case "Horror" :
                    break;

                case "Mystery" :
                    break;

                case "Non-Fiction" :
                    break;

                case "Paranormal" :
                    break;

                case "Romance" :
                    break;

                case "Science Fiction" :
                    tempStr = genreStringsSciFi;
                    break;

                case "Suspense" :
                    break;

                case "Thriller" :
                    break;

                case "Young-Adult" :
                    break;

                default :
                    Toast.makeText(this, "There seems to be an error!", Toast.LENGTH_SHORT).show();
            }

        subGenreList = findViewById(R.id.subGenreList);

        MainGenreListAdapter adapter = new MainGenreListAdapter(SubGenreActivity.this, tempStr);

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, genreStrings);
        subGenreList.setAdapter(adapter);

        subGenreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = mainGenreStr + "/" + tempStr[i];
                Toast.makeText(SubGenreActivity.this, str, Toast.LENGTH_SHORT).show();
                Log.i("THIS IS SAMPLE : ", str);
                Intent intent = new Intent(SubGenreActivity.this, MainActivity.class);
                intent.putExtra("PATH NAME", str);
                startActivity(intent);
            }
        });
    }
}
