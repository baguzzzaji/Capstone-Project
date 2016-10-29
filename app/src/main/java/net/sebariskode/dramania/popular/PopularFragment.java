package net.sebariskode.dramania.popular;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import net.sebariskode.dramania.DramaAdapter;
import net.sebariskode.dramania.R;
import net.sebariskode.dramania.airingtoday.AiringTodayContract;
import net.sebariskode.dramania.airingtoday.AiringTodayPresenter;
import net.sebariskode.dramania.data.Drama;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment implements PopularContract.View {
    private static String TAG = PopularFragment.class.getSimpleName();
    PopularContract.Presenter presenter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_popular_default_bg)
    ImageView defaultBg;

    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.setContext(getContext());
        presenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null)
            presenter = new PopularPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        ButterKnife.bind(this, view);
        initializeRecyclerView();
        Log.d(TAG, "onCreateView");
        if (presenter.isDramaAvailable()) {
            defaultBg.setVisibility(View.GONE);
        } else {
            defaultBg.setVisibility(View.VISIBLE);
        }

        return view;
    }

    /**
     * This method is for initialization only
     */
    private void initializeRecyclerView() {
        List<Drama> dramas = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3,
                    StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else {
            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(5,
                    StaggeredGridLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        recyclerView.setAdapter(new DramaAdapter(getActivity(), dramas));
    }

    @Override
    public void showNoInternetConnection() {
        Toast.makeText(getContext(), "You device doesn't have an Internet connection. Connect and restart this app.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDramaItemRecyclerView(List<Drama> dramas) {
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3,
                    StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else {
            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(5,
                    StaggeredGridLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(gridLayoutManager);
        }

        DramaAdapter dramaAdapter = new DramaAdapter(getContext(), dramas);
        recyclerView.setAdapter(dramaAdapter);
    }

    @Override
    public void showDramaDownloadFailed() {
        Toast.makeText(getContext(), "Failed to download drama information, please try again.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(PopularContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
