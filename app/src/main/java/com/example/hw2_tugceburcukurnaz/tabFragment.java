package com.example.hw2_tugceburcukurnaz;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TabHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class tabFragment extends Fragment {

    private WebView webView1;
    private WebView webView2;
    private WebView webView3;

    public tabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_tab, container, false);
        TabHost tabHost= (TabHost) v.findViewById(R.id.tabHost);
        tabHost.setup();

        FragmentManager fragmentManager = getFragmentManager();



        TabHost.TabSpec spec = tabHost.newTabSpec("Food list");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Food list");
        tabHost.addTab(spec);

        foodListFragment fFoodList = new foodListFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.tab1 , fFoodList);
        transaction.commit();



        spec = tabHost.newTabSpec("Announce");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Announce");
        tabHost.addTab(spec);

        announcementFragment fAnnouncement = new announcementFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.tab2 , fAnnouncement);
        transaction.commit();



        spec = tabHost.newTabSpec("News");
        spec.setContent(R.id.tab3);
        spec.setIndicator("News");
        tabHost.addTab(spec);

        newsFragment fNews = new newsFragment();
        transaction= fragmentManager.beginTransaction();
        transaction.add(R.id.tab3 , fNews);
        transaction.commit();



        return v;
    }


}
