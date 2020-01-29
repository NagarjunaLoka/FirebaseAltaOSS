package com.example.anushak.firebasealtaoss;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class BlankFragment extends Fragment {

    LinearLayout l1,l2,l3,content,content1;
    Button mobile,web;
    View view;

    public static final String TITLE = "Applications";

    public static BlankFragment newInstance() {

        return new BlankFragment();
    }

    // @Nullable
    @Override
    // public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_blank, container, false);

        l1 = (LinearLayout) view.findViewById(R.id.l1);
        l2 = (LinearLayout) view.findViewById(R.id.l2);
        l3 = (LinearLayout) view.findViewById(R.id.l3);
        content = (LinearLayout) view.findViewById(R.id.content);
        content1 = (LinearLayout) view.findViewById(R.id.content1);
        mobile = (Button) view.findViewById(R.id.mobile);
        web = (Button) view.findViewById(R.id.web);

        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                content.setVisibility(View.VISIBLE);
                content1.setVisibility(View.GONE);

            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                content1.setVisibility(View.VISIBLE);
                content.setVisibility(View.GONE);

            }
        });

        return view;


    }
}