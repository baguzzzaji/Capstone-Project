package net.sebariskode.dramania.popular;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sebariskode.dramania.R;
import net.sebariskode.dramania.airingtoday.AiringTodayContract;
import net.sebariskode.dramania.data.Drama;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment implements PopularContract.View {


    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }

    @Override
    public void showNoInternetConnection() {

    }

    @Override
    public void showDramaItemRecyclerView(List<Drama> dramas) {

    }

    @Override
    public void setPresenter(PopularContract.Presenter presenter) {

    }
}
