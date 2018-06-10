package jmlb0003.com.marveleando.presentation;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import jmlb0003.com.marveleando.domain.interactor.PostExecutionThread;

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler} which will execute actions
 * on the Android UI thread
 */
public final class UiThread implements PostExecutionThread {

    public UiThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
