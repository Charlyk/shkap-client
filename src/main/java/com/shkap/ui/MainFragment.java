package com.shkap.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shkap.R;
import com.shkap.adapters.BigCardAdapter;
import com.shkap.adapters.SmallCardAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
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
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        SmallCardAdapter adapter = new SmallCardAdapter(null, getActivity()); //TODO: Добавить полученные данные вместо null
        recyclerView.setAdapter(adapter);
    }


}
