package com.ragerpie.ayi.ragerpie.net;

import com.ragerpie.ayi.ragerpie.util.LogUtils;

import rx.Subscriber;

/**
 * Created by WangBo on 2016/11/2.
 */

public class RagerSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        LogUtils.i("onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(e.getMessage());
        e.printStackTrace();
    }

    @Override
    public void onNext(T o) {
        LogUtils.i("onNext");
    }
}
