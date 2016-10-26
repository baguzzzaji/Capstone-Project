package net.sebariskode.dramania.airingtoday;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.sebariskode.dramania.R;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class AiringTodayFragment extends Fragment implements AiringTodayContract.View {

    final String TAG = AiringTodayFragment.class.getSimpleName();

    AiringTodayContract.Presenter presenter;

    public AiringTodayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter == null)
            presenter = new AiringTodayPresenter(this);

        presenter.setContext(getContext());
        presenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_airing_today, container, false);
    }

    @Override
    public void setPresenter(AiringTodayContract.Presenter presenter) {
    }


    @Override
    public void showNoInternetConnection() {
        Log.d(TAG, "showNoInternetConnection: there's no internet connection");
        Toast.makeText(getContext(), "You device doesn't have an Internet connection. Connect and restart this app.", Toast.LENGTH_LONG).show();
    }
}
