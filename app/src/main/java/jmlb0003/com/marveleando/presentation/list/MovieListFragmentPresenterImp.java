package jmlb0003.com.marveleando.presentation.list;

import jmlb0003.com.marveleando.presentation.BasePresenterImpl;

public final class MovieListFragmentPresenterImp extends BasePresenterImpl<CharacterListFragmentPresenter.CharacterListFragmentView>
        implements CharacterListFragmentPresenter {

    @Override
    public void refreshCharacters() {
        if (getView() != null) {
            getView().showLoading();
        }
    }

    @Override
    public void onNoNetworkConnection() {
        if (getView() != null) {
            getView().showNoInternetConnectionMessage();
        }
    }

}
