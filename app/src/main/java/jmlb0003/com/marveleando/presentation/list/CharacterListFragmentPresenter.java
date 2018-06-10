package jmlb0003.com.marveleando.presentation.list;

import java.util.List;

import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BasePresenter;

public interface CharacterListFragmentPresenter
        extends BasePresenter<CharacterListFragmentPresenter.CharacterListFragmentView> {

    void refreshCharacters();

    void onNoNetworkConnection();

    interface CharacterListFragmentView extends BasePresenter.BaseView {

        void showLoading();

        void hideLoading();

        void updateCharactersToShow(List<Character> characters);

        void showNoCharactersToShowScreen();

        void showNoInternetConnectionMessage();

    }

}
