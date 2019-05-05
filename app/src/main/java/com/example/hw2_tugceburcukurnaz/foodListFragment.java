package com.example.hw2_tugceburcukurnaz;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class foodListFragment extends Fragment {
    ProgressDialog progressDialog;

    ArrayList<String> foodList = new ArrayList<>();

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;

    public foodListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_food_list, container, false);

        textView = (TextView) v.findViewById(R.id.text1);
        textView2 = (TextView) v.findViewById(R.id.text2);
        textView3 = (TextView) v.findViewById(R.id.text3);
        textView4 = (TextView) v.findViewById(R.id.text4);
        textView5 = (TextView) v.findViewById(R.id.text5);

        Content c = new Content();
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
                String url = "https://aybu.edu.tr/sks/";

                //Connect to the website
                Document document = Jsoup.connect(url).get();

                Element table = document.select("table").get(0);
                Element tr_1 = table.select("tr").get(0);
                Elements tr_2 = tr_1.select("tr");

                for (int i = 2; i < tr_2.size(); i++) {
                    Element td = tr_2.get(i).select("td").get(0);
                    foodList.add(td.text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            textView.setText(foodList.get(0));
            textView2.setText(foodList.get(1));
            textView3.setText(foodList.get(2));
            textView4.setText(foodList.get(3));
            textView5.setText(foodList.get(4));
            progressDialog.dismiss();
        }
    }


}
