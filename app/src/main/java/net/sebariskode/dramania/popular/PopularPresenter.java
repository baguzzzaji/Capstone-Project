package net.sebariskode.dramania.popular;

import android.content.Context;

import net.sebariskode.dramania.Utils.NetworkUtil;
import net.sebariskode.dramania.data.Drama;
import net.sebariskode.dramania.data.DramaResults;
import net.sebariskode.dramania.data.themoviedb.RetrofitHelper;
import net.sebariskode.dramania.data.themoviedb.TmdbInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by baguzzzaji on 10/29/2016.
 */

public class PopularPresenter implements PopularContract.Presenter {

    private static String TAG = PopularPresenter.class.getSimpleName();
    PopularContract.View view;
    Context context;

    List<Drama> dramas;

    public PopularPresenter(PopularContract.View view) {
        this.view = view;
        view.setPresenter(this);
        dramas = new ArrayList<>();
    }

    @Override
    public boolean isDramaAvailable() {
        return dramas.isEmpty();
    }

    @Override
    public List<Drama> getDrama() {
        return dramas;
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

        Call<DramaResults> call = tmdbInterface.getPopular(RetrofitHelper.API_KEY, "1");
        call.enqueue(new Callback<DramaResults>() {
            @Override
            public void onResponse(Call<DramaResults> call, Response<DramaResults> response) {
                dramas = response.body().getDramas();
                view.showDramaItemRecyclerView(dramas);
            }

            @Override
            public void onFailure(Call<DramaResults> call, Throwable t) {
                view.showDramaDownloadFailed();
            }
        });
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
