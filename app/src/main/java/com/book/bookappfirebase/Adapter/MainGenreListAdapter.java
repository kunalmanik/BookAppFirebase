package com.book.bookappfirebase.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.book.bookappfirebase.Books;
import com.book.bookappfirebase.MainActivity;
import com.book.bookappfirebase.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunalmnik95 on 20/10/17.
 */

public class MainGenreListAdapter extends ArrayAdapter<String> {

    private final String[] genreItem;
    private final Activity context;

    public MainGenreListAdapter(Activity context, String[] genreItem)
    {
        super(context, R.layout.adapter_genre_list_layout, genreItem);
        this.context = context;
        this.genreItem = genreItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View listItemView = layoutInflater.inflate(R.layout.adapter_genre_list_layout, null, true);

        final TextView textViewGenre = listItemView.findViewById(R.id.genreButton);

        textViewGenre.setText(genreItem[position]);

        return listItemView;
    }
}
