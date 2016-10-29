package net.sebariskode.dramania.airingtoday;


import android.content.Context;
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
import net.sebariskode.dramania.data.Drama;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class AiringTodayFragment extends Fragment implements AiringTodayContract.View {

    final String TAG = AiringTodayFragment.class.getSimpleName();

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_airing_today_default_bg)
    ImageView defaultBg;

    AiringTodayContract.Presenter presenter;

    public AiringTodayFragment() {
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
            presenter = new AiringTodayPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_airing_today, container, false);

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
    public void setPresenter(AiringTodayContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void showNoInternetConnection() {
        Log.d(TAG, "showNoInternetConnection: there's no internet connection");
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
}
