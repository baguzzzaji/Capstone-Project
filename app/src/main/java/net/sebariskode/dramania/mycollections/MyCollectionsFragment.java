package net.sebariskode.dramania.mycollections;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sebariskode.dramania.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCollectionsFragment extends Fragment {


    public MyCollectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_collections, container, false);
    }

}