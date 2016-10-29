package net.sebariskode.dramania.toprated;

import net.sebariskode.dramania.BasePresenter;
import net.sebariskode.dramania.BaseView;
import net.sebariskode.dramania.data.Drama;
import net.sebariskode.dramania.popular.PopularContract;

import java.util.List;

/**
 * Created by baguzzzaji on 10/29/2016.
 */

public interface TopRatedContract{
    interface View extends BaseView<TopRatedContract.Presenter> {
        void showNoInternetConnection();
        void showDramaItemRecyclerView(List<Drama> dramas);
        void showDramaDownloadFailed();
    }

    interface Presenter extends BasePresenter {
        boolean isDramaAvailable();
        List<Drama> getDrama();
    }
}
