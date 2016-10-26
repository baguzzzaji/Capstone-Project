package net.sebariskode.dramania.airingtoday;

import android.content.Context;
import android.net.Network;
import android.telephony.NeighboringCellInfo;
import android.util.Log;

import net.sebariskode.dramania.Utils.NetworkUtil;
import net.sebariskode.dramania.data.DramaResults;
import net.sebariskode.dramania.data.themoviedb.RetrofitHelper;
import net.sebariskode.dramania.data.themoviedb.TmdbInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by bagus on 22/10/16.
 */

public class AiringTodayPresenter implements AiringTodayContract.Presenter {
    private static final String TAG = AiringTodayFragment.class.getSimpleName();
    AiringTodayContract.View view;
    Context context;

    public AiringTodayPresenter(AiringTodayContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        if (!NetworkUtil.isNetworkActive(context)){
            view.showNoInternetConnection();
        } else {
            getDramas();
        }
    }

    private void getDramas() {
        TmdbInterface tmdbInterface = RetrofitHelper.getClient().create(TmdbInterface.class);

        Call<DramaResults> call = tmdbInterface.getAiringToday(RetrofitHelper.API_KEY, "1");
        call.enqueue(new Callback<DramaResults>() {
            @Override
            public void onResponse(Call<DramaResults> call, Response<DramaResults> response) {
                Log.d(TAG, "onResponse: " + response.body().getTotal_results());
            }

            @Override
            public void onFailure(Call<DramaResults> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
