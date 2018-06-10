package jmlb0003.com.marveleando.presentation;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public abstract class BasePresenterImpl<V extends BasePresenter.BaseView>
        implements BasePresenter<V> {

    private WeakReference<V> referenceView;

    @Nullable
    @Override
    public V getView() {
        if (referenceView != null) {
            return referenceView.get();
        } else {
            return null;
        }
    }

    @Override
    public void attachView(final V view) {
        this.referenceView = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (getView() != null) {
            referenceView.clear();
            referenceView = null;
        }
    }

}
