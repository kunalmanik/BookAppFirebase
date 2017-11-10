package com.book.bookappfirebase.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.book.bookappfirebase.R;

import java.util.ArrayList;

/**
 * Created by kunalmnik95 on 10/11/17.
 */

public class TagListAdapter extends ArrayAdapter<String> {

    private final ArrayList<String> resource;
    private final Activity context;

    public TagListAdapter(Activity context, ArrayList<String> resource) {
        super(context, R.layout.adapter_tag_activity,resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View listItemView = layoutInflater.inflate(R.layout.adapter_tag_activity, null, true);

        final TextView textViewGenre = listItemView.findViewById(R.id.tagButton);

        textViewGenre.setText(resource.get(position));

        return listItemView;
    }
}
