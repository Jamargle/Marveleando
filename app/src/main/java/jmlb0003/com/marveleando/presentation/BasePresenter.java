package jmlb0003.com.marveleando.presentation;

public interface BasePresenter<V extends BasePresenter.BaseView> {

    V getView();

    void attachView(V view);

    void detachView();

    interface BaseView {

    }

}
