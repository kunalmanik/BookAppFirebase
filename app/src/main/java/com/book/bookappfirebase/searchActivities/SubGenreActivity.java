package com.book.bookappfirebase.searchActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    private String mainGenreStr;
    String [] tempStr;

    ListView subGenreList;

    String[] genreStringsContemporary = {"Best Contemporary of all Time", "Stand-Alones",
            "Adult Contemporary", "Something from New Authors"};

    String[] genreStringsFantasy = {"The Best Epic Fantasy", "Strong Female Fantasy Novels",
            "Best Urban Fantasy", "Series-Triologies-Duologies", "Kick-Ass Female characters",
            "Best Fantasy of 21st Century", "Most Interesting Magic System",
            "The Best Fairytales and Retellings", "Angels, Gods and Demons"};

    String[] genreStringsHistory = {"Most Influencial", "Teen Historical Fiction", "Historical Mystery",
            "Microhistory: Social Histories of Just One Thing", "Best of All Time", "Memoirs by Women",
            "Recommended", "Civil War Books", "Ancient Rome", "Middle Ages", "American History",
            "Tudor England", "Non-Fiction War", "Israel-Palestine Conflict", "Historical Romance" };

    String[] genreStringsHorror = {"Vampires", "Most Disturbing Books", "Best of Horror Novels",
            "Zombies", "Best of Stephen King", "Quality Dark Fiction", "Ghost Stories",
            "Serial Killers", "Darkest Books of All Time", "Not The 'Normal' Paranormal "};

    String[] genreStringsMystery = {"Must Read Thrillers", "Best Spy Novels", "Best Crime and Mystery",
            "Best Cozy Mystery Series", "Literary Mysteries", "Best Mysteries of the Decade"};

    String[] genreStringsNonFic = {"Best of Non-Fiction", "Best Humorous", "Favourite Travel Books",
            "Best Science Books", "Best Unappreciated Books", "Best Biographies",
            "Must Read Non-Fiction" };

    String[] genreStringsRomance = {"Best Book Boyfriends", "Young Adult", "Best Love Stories",
            "College Romance", "Contemporary Romance", "Historical Romance", "All Time Favourite",
            "Paranormal and Romance"};

    String[] genreStringsSciFi = {"Dystopian and Post-Apocalyptic Fiction", "Best of All Time",
            "Kick-Ass Female characters", "Best Books of 21st Century", "Best Steampunk Books",
            "From the new Stars", "Under-Rated Sci-Fi", "Amazon's Must Read Books", "This is the end",
            "Female Protagonist", "Science Fiction and Fantasy"};

    String[] genreStringsThrillerSuspense = {"Recommended Suspense and Thriller",
            "Romantic Suspense", "Best Technothrillers Ever", "Must Read Thriller",
            "Female Psychological Thriller and Suspense"};

    String[] genreStringsLeastKnown = {"Must-Knowns"};

    String[] genreStringsSocial = {"Best Feminist Books", "Books on Racism, Sexism and Class",
            "Books White People need to Read", "Books that Frame Thinking"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_genre_layout);

//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if(extras == null) {
//                mainGenreStr = null;
//            }
//            else {
//                mainGenreStr = extras.getString("GENRE NAME");
//            }
//        }
//        else {
//            mainGenreStr = (String) savedInstanceState.getSerializable("GENRE NAME");
//        }

        SharedPreferences preferences = getSharedPreferences("GENRE PREF", MODE_PRIVATE);
        mainGenreStr = preferences.getString("GENRE NAME", null);

        final SharedPreferences sharedPreferences = this.getSharedPreferences("SUBGENRE PREF", MODE_PRIVATE);

        if (mainGenreStr != null)
            switch (mainGenreStr)
            {
                case "Contemporary" :
                    tempStr = genreStringsContemporary;
                    break;

                case "Fantasy" :
                    tempStr = genreStringsFantasy;
                    break;

                case "History" :
                    tempStr = genreStringsHistory;
                    break;

                case "Horror" :
                    tempStr = genreStringsHorror;
                    break;

                case "Least Knowns" :
                    tempStr = genreStringsLeastKnown;
                    break;

                case "Mystery" :
                    tempStr = genreStringsMystery;
                    break;

                case "Non-Fiction" :
                    tempStr = genreStringsNonFic;
                    break;

                case "Romance" :
                    tempStr = genreStringsRomance;
                    break;

                case "Science Fiction" :
                    tempStr = genreStringsSciFi;
                    break;

                case "Social Issues" :
                    tempStr = genreStringsSocial;
                    break;

                case "Thriller and Suspense" :
                    tempStr = genreStringsThrillerSuspense;
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
                //intent.putExtra("PATH NAME", str);
                sharedPreferences.edit().putString("SUBGENRE", str).apply();
                startActivity(intent);
            }
        });
    }
}
