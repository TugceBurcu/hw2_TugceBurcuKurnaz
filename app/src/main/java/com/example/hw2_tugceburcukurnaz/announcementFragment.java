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
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class announcementFragment extends Fragment {
    ProgressDialog progressDialog;

    String url = "https://aybu.edu.tr/muhendislik/bilgisayar/";

    ArrayList<String> announcementList = new ArrayList<>();
    ArrayList<String> hrefList = new ArrayList<>();

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;

    public announcementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_announcement, container, false);

        textView = (TextView) v.findViewById(R.id.text1);
        textView2 = (TextView) v.findViewById(R.id.text2);
        textView3 = (TextView) v.findViewById(R.id.text3);
        textView4 = (TextView) v.findViewById(R.id.text4);
        textView5 = (TextView) v.findViewById(R.id.text5);
        textView6 = (TextView) v.findViewById(R.id.text6);

        announcementFragment.Content c = new announcementFragment.Content();
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

                for (int i = 38; i < 44; i++) {
                    Element a = form.select("a").get(i);
                    announcementList.add(a.text());
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

            textView.setText(">>"+announcementList.get(0));
            textView2.setText(">>"+announcementList.get(1));
            textView3.setText(">>"+announcementList.get(2));
            textView4.setText(">>"+announcementList.get(3));
            textView5.setText(">>"+announcementList.get(4));
            textView6.setText(">>"+announcementList.get(5));

            textView.setText(Html.fromHtml("<a href=\""+ url + hrefList.get(0) + "\">" + ">>" + announcementList.get(0) + "</a>"));
            textView.setClickable(true);
            textView.setMovementMethod (LinkMovementMethod.getInstance());

            textView2.setText(Html.fromHtml("<a href=\""+ url + hrefList.get(1) + "\">" + ">>" + announcementList.get(1) + "</a>"));
            textView2.setClickable(true);
            textView2.setMovementMethod (LinkMovementMethod.getInstance());

            textView3.setText(Html.fromHtml("<a href=\""+ url + hrefList.get(2) + "\">" + ">>" + announcementList.get(2) + "</a>"));
            textView3.setClickable(true);
            textView3.setMovementMethod (LinkMovementMethod.getInstance());

            textView4.setText(Html.fromHtml("<a href=\""+ url + hrefList.get(3) + "\">" + ">>" + announcementList.get(3) + "</a>"));
            textView4.setClickable(true);
            textView4.setMovementMethod (LinkMovementMethod.getInstance());

            textView5.setText(Html.fromHtml("<a href=\""+ url + hrefList.get(4) + "\">" + ">>" + announcementList.get(4) + "</a>"));
            textView5.setClickable(true);
            textView5.setMovementMethod (LinkMovementMethod.getInstance());

            textView6.setText(Html.fromHtml("<a href=\""+ url + hrefList.get(5) + "\">" + ">>" + announcementList.get(5) + "</a>"));
            textView6.setClickable(true);
            textView6.setMovementMethod (LinkMovementMethod.getInstance());

            progressDialog.dismiss();
        }
    }

}
