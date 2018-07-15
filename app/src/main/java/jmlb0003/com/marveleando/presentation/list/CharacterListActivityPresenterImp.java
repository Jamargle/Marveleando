package jmlb0003.com.marveleando.presentation.list;

import javax.inject.Inject;

import jmlb0003.com.marveleando.presentation.BasePresenterImpl;

public final class CharacterListActivityPresenterImp
        extends BasePresenterImpl<CharacterListActivityPresenter.CharacterListActivityView>
        implements CharacterListActivityPresenter {

    @Inject
    public CharacterListActivityPresenterImp() {
    }

    @Override
    public void onShowFavoritesSelected() {
        if (getView() != null) {
            getView().showFavoriteCharacters();
        }
    }

    @Override
    public void onShowEveryoneSelected() {
        if (getView() != null) {
            getView().showEveryCharacters();
        }
    }
}
