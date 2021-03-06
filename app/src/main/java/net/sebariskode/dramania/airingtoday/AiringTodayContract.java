package net.sebariskode.dramania.airingtoday;

import net.sebariskode.dramania.BasePresenter;
import net.sebariskode.dramania.BaseView;
import net.sebariskode.dramania.data.Drama;

import java.util.List;

/**
 * Created by bagus on 22/10/16.
 */

public interface AiringTodayContract {

    interface View extends BaseView<Presenter> {
        void showNoInternetConnection();
        void showDramaItemRecyclerView(List<Drama> dramas);
        void showDramaDownloadFailed();
    }

    interface Presenter extends BasePresenter {
        boolean isDramaAvailable();
        List<Drama> getDrama();
    }
}
