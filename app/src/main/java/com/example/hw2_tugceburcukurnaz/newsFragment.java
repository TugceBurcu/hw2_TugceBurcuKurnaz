package com.example.hw2_tugceburcukurnaz;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class newsFragment extends Fragment {
    ProgressDialog progressDialog;


    String url = "https://aybu.edu.tr/muhendislik/bilgisayar/";

    ArrayList<String> newsList = new ArrayList<>();
    ArrayList<String> hrefList = new ArrayList<>();

    TextView textView;
    TextView textView2;
    TextView textView3;

    public newsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_news, container, false);

        textView = (TextView) v.findViewById(R.id.text1);
        textView2 = (TextView) v.findViewById(R.id.text2);
        textView3 = (TextView) v.findViewById(R.id.text3);

        newsFragment.Content c = new newsFragment.Content();
        c.execute();

        return v;
    }

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                //Connect to the website
                Document document = Jsoup.connect(url).get();

                Element form = document.select("form").get(0);

                for (int i = 34; i < 37; i++) {
                    Element a = form.select("a").get(i);
                    newsList.add(a.text());
                    hrefList.add(a.attr("href"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

            textView.setText(Html.fromHtml("<a href=\""+ url + hrefList.get(0) + "\">" + ">>" + newsList.get(0) + "</a>"));
            textView.setClickable(true);
            textView.setMovementMethod (LinkMovementMethod.getInstance());

            textView2.setText(Html.fromHtml("<a href=\""+ url + hrefList.get(1) + "\">" + ">>" + newsList.get(1) + "</a>"));
            textView2.setClickable(true);
            textView2.setMovementMethod (LinkMovementMethod.getInstance());

            textView3.setText(Html.fromHtml("<a href=\""+ url + hrefList.get(2) + "\">" + ">>" + newsList.get(2) + "</a>"));
            textView3.setClickable(true);
            textView3.setMovementMethod (LinkMovementMethod.getInstance());


            progressDialog.dismiss();
        }
    }

}
