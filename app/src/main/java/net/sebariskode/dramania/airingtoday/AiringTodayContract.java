package net.sebariskode.dramania.airingtoday;

import net.sebariskode.dramania.BasePresenter;
import net.sebariskode.dramania.BaseView;

/**
 * Created by bagus on 22/10/16.
 */

public interface AiringTodayContract {

    interface View extends BaseView<Presenter> {
        void showNoInternetConnection();
    }

    interface Presenter extends BasePresenter {

    }
}
