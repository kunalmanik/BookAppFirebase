package com.book.bookappfirebase;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.book.bookappfirebase.Adapter.BookListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listViewBooks;
    List<Books> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String pathStr;
//
//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if(extras == null) {
//                pathStr = null;
//            }
//            else {
//                pathStr = extras.getString("PATH NAME");
//            }
//        }
//        else {
//            pathStr = (String) savedInstanceState.getSerializable("PATH NAME");
//        }

        SharedPreferences preferences = this.getSharedPreferences("SUBGENRE PREF", MODE_PRIVATE);
        pathStr = preferences.getString("SUBGENRE", null);

        try {
            databaseReference = FirebaseDatabase.getInstance().getReference(pathStr);
        }
        catch (Exception ex) {
            Log.e("ERROR", ex.toString());
        }

        listViewBooks = findViewById(R.id.userList);

        bookList = new ArrayList<>();
        
        bookList.clear();
//TODO - CODE FOR RETRIEVING THE (BASIC) DATA FROM THE REAL TIME DATABASE
//        mDatabaseName = mDatabase.child("Name");
//        mDatabaseAge = mDatabase.child("Age");
//
//        mNameView = (TextView) findViewById(R.id.name_view);
//        mAgeView = (TextView) findViewById(R.id.age_view);
//
//        mDatabaseName.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                String name = dataSnapshot.getValue().toString();
//                mNameView.setText(name);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        mDatabaseAge.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                String age = dataSnapshot.getValue().toString();
//                mAgeView.setText(age);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//TODO - CODE FOR STORING THE DATA TO THE REAL TIME DATABASE
//        mFirebaseButton = (Button) findViewById(R.id.button);
//        mNameField = (EditText) findViewById(R.id.nameField);
//        mEmailField = (EditText) findViewById(R.id.emailField);

//        mFirebaseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String name = mNameField.getText().toString().trim();
//                String email = mEmailField.getText().toString().trim();
//
//                HashMap<String, String> dataMap = new HashMap<>();
//                dataMap.put("Name", name);
//                dataMap.put("Email", email);
//
//                mDatabase.push().setValue(dataMap)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                        if(task.isSuccessful())
//                            Toast.makeText(MainActivity.this, "Stored", Toast.LENGTH_SHORT).show();
//
//                        else
//                            Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        final BookListAdapter adapter = new BookListAdapter(MainActivity.this, bookList);

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                listViewBooks.setAdapter(adapter);

                bookList.clear();
                for(DataSnapshot bookSnapShot : dataSnapshot.getChildren())
                {
                    Books books = bookSnapShot.getValue(Books.class);
                    bookList.add(books);
                    adapter.notifyDataSetChanged();
                }

                listViewBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        view.setSelected(true);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Please Try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}