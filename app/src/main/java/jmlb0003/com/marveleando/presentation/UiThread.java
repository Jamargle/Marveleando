package jmlb0003.com.marveleando.presentation;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import jmlb0003.com.marveleando.domain.interactor.PostExecutionThread;

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler} which will execute actions
 * on the Android UI thread
 */
public final class UiThread implements PostExecutionThread {

    @Inject
    public UiThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
