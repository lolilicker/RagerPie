package com.ragerpie.ayi.ragerpie.context;

import android.app.Application;

/**
 * Created by WangBo on 2016/11/2.
 */

public class AppContext extends Application {
    private static AppContext appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static AppContext getInstance() {
        return appContext;
    }
}
