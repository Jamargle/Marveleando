package jmlb0003.com.marveleando.presentation.list;

import jmlb0003.com.marveleando.presentation.BasePresenter;

public interface CharacterListActivityPresenter extends BasePresenter<CharacterListActivityPresenter.CharacterListActivityView> {

    void onShowFavoritesSelected();

    void onShowEveryoneSelected();

    interface CharacterListActivityView extends BasePresenter.BaseView {

        void showFavoriteCharacters();

        void showEveryCharacters();

    }

}
