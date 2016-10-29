package net.sebariskode.dramania.popular;

import android.content.Context;

import net.sebariskode.dramania.data.Drama;

import java.util.ArrayList;
import java.util.List;

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
        return false;
    }

    @Override
    public List<Drama> getDrama() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void setContext(Context context) {

    }
}
