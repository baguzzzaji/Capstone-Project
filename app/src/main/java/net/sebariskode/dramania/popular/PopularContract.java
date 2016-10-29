package net.sebariskode.dramania.popular;

import net.sebariskode.dramania.BasePresenter;
import net.sebariskode.dramania.BaseView;
import net.sebariskode.dramania.airingtoday.AiringTodayContract;
import net.sebariskode.dramania.data.Drama;

import java.util.List;

/**
 * Created by baguzzzaji on 10/29/2016.
 */

public interface PopularContract {
    interface View extends BaseView<PopularContract.Presenter> {
        void showNoInternetConnection();
        void showDramaItemRecyclerView(List<Drama> dramas);
    }

    interface Presenter extends BasePresenter {
        boolean isDramaAvailable();
        List<Drama> getDrama();
    }
}
