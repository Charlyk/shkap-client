package com.shkap.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shkap.R;
import com.shkap.adapters.ThingPageImageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThingDetailedFragment extends android.support.v4.app.Fragment {

    public ThingDetailedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thing_detailed, container, false);
        setUp(view);
        return view;
    }

    private void setUp(View v) {
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.thingDetail_recyclerView);
        ThingPageImageAdapter adapter = new ThingPageImageAdapter();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }
}
