package com.book.bookappfirebase.searchActivities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.book.bookappfirebase.Adapter.TagListAdapter;
import com.book.bookappfirebase.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by kunalmnik95 on 10/11/17.
 */

public class TagActivity extends AppCompatActivity
{

    static ArrayList<String> arrayList = new ArrayList<>();
    ListView tagList;
    WebView webView;
    Context context;
    TagListAdapter tagListAdapter;
    private final static String url = "https://www.goodreads.com/list/tag/genre";
    private final static String redirectURL = "https://www.goodreads.com/list/tag/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_activity_layout);

        tagList = findViewById(R.id.tagActivityList);
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        tagListAdapter = new TagListAdapter(TagActivity.this, arrayList);
        tagList.setAdapter(tagListAdapter);

        tagListAdapter.notifyDataSetChanged();

        tagList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = arrayList.get(position);
                Toast.makeText(TagActivity.this, str, Toast.LENGTH_SHORT).show();
                webView.setVisibility(View.VISIBLE);
                webView.setWebViewClient(new WebViewClient(){
                    @Override
                    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                        Toast.makeText(TagActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

                webView.loadUrl(redirectURL.concat(str));
            }
        });

        new ParsePage().execute();
    }

    class ParsePage extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... strings) {

            Document document;
            try {
                document = Jsoup.connect(url).get();
                Elements elements = document.getElementsByClass("actionLinkLite");
                for(Element element : elements)
                {
                    //Log.i("ELEMENT", element.text());
                    arrayList.add(element.text());
                }
                //Log.d("TEST HTML", document.toString());
//                for (String str:arrayList
//                     ) {
//                    Log.i("TEST", str);
//                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String s) {
            tagListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        webView.clearCache(true);
        webView.clearFormData();
        webView.clearHistory();
        webView.clearSslPreferences();
        webView.clearMatches();
        if(webView.getVisibility() == View.VISIBLE)
        {
            webView.setVisibility(View.GONE);
        }
        super.onBackPressed();
    }
}
