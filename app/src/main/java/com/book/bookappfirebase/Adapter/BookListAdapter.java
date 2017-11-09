package com.book.bookappfirebase.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.book.bookappfirebase.Books;
import com.book.bookappfirebase.OnSwipeTouchListener;
import com.book.bookappfirebase.R;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by kunalmnik95 on 10/10/17.
 */

public class BookListAdapter extends ArrayAdapter<Books>
{
    private Activity context;
    private List<Books> booksList;
    final private String uriText = "https://www.amazon.in/s/ref=nb_sb_noss_2/258-4426740-9158844?url=search-alias%3Dstripbooks&field-keywords=";

    //TODO Used only for Amazon AppStore
    //final private String uriTextAmz = "amzn://apps/android?s=";
    final private String packageName = "in.amazon.mShop.android.shopping://www.amazon.in/s/ref=nb_sb_noss_2/258-4426740-9158844?url=search-alias%3Dstripbooks&field-keywords=";
    //final private String test = "in.amazon.mShop.android.shopping://https://www.amazon.in/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=Harry+Potter";

    public BookListAdapter(Activity context, List<Books> booksList)
    {
        super(context, R.layout.adapter_book_list_layout, booksList);
        this.context = context;
        this.booksList = booksList;
    }

    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.adapter_book_list_layout, null, true);

        final TextView textViewBook = listViewItem.findViewById(R.id.textViewBook);
        final TextView textViewAuthor = listViewItem.findViewById(R.id.textViewAuthor);
        final LinearLayout amazonButtonView = listViewItem.findViewById(R.id.amazonButtonView);
        final LinearLayout listObject = listViewItem.findViewById(R.id.listObject);
        final Button amazonButton = listViewItem.findViewById(R.id.amazonButton);
        final Button amazonText = listViewItem.findViewById(R.id.amazonText);

        Books books = booksList.get(position);

        textViewBook.setText(books.getName());
        textViewAuthor.setText(books.getAuthor());

//        listObject.setOnTouchListener(new OnSwipeTouchListener(context) {
//            @Override
//            public void onSwipeRight() {
//                Toast.makeText(context, textViewBook.getText() + "\t" + textViewAuthor.getText(), Toast.LENGTH_SHORT).show();
//                if(amazonButtonView.getVisibility() == View.GONE) {
//                    linearText.setVisibility(View.GONE);
//                    amazonButtonView.setVisibility(View.VISIBLE);
//                }
//            }
//        });
        listObject.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, textViewBook.getText() + "\t" + textViewAuthor.getText(), Toast.LENGTH_SHORT).show();
                if(amazonButtonView.getVisibility() == View.GONE) {
                    amazonButtonView.setVisibility(View.VISIBLE);
                }

                else if(amazonButtonView.getVisibility() == View.VISIBLE) {
                    amazonButtonView.setVisibility(View.GONE);
                }

                return true;
            }
        });

        amazonButtonView.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            @SuppressLint("ClickableViewAccessibility")
            public void onSwipeLeft() {
                Toast.makeText(context, textViewBook.getText() + "\t" + textViewAuthor.getText(), Toast.LENGTH_SHORT).show();
                if(amazonButtonView.getVisibility() == View.VISIBLE){
                    amazonButtonView.setVisibility(View.GONE);
                }
            }

            @Override
            @SuppressLint("ClickableViewAccessibility")
            public void onSwipeRight() {
                Toast.makeText(context, textViewBook.getText() + "\t" + textViewAuthor.getText(), Toast.LENGTH_SHORT).show();
                if(amazonButtonView.getVisibility() == View.VISIBLE){
                    amazonButtonView.setVisibility(View.GONE);
                }
            }
        });


        amazonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Long Press this to search Books by this author on Amazon", Toast.LENGTH_LONG).show();
            }
        });

        amazonButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uriText.concat(textViewAuthor.getText().toString()))));
                }
                catch (Exception e) {
                    Log.e("ERROR CHECK", e.toString());
                }
                return true;
            }
        });

        amazonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Long Press this to search this Book on Amazon", Toast.LENGTH_LONG).show();
            }
        });

        amazonText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uriText.concat(textViewBook.getText().toString()))));
                }
                catch (Exception e) {
                    Log.e("ERROR CHECK", e.toString());
                }
                return true;
            }
        });

        return listViewItem;
    }

}