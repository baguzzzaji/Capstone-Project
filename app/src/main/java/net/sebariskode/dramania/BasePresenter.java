package net.sebariskode.dramania;

import android.content.Context;

/**
 * Created by bagus on 22/10/16.
 */

public interface BasePresenter {

    void start();

    Context getContext();

    void setContext(Context context);
}
