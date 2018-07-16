package jmlb0003.com.marveleando.presentation.list;

import java.util.List;

import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BasePresenter;

public interface CharacterListFragmentPresenter extends BasePresenter<CharacterListFragmentPresenter.CharacterListFragmentView> {

    void refreshCharacters();

    void fetchMoreCharacters(int currentPage);

    void onNoNetworkConnection();

    void onCharacterDataSetEmpty();

    void onCharacterClicked(Character character);

    interface CharacterListFragmentView extends BasePresenter.BaseView {

        void showLoading();

        void hideLoading();

        void updateCharactersToShow(List<Character> characters);

        void showNoCharactersToShow();

        void hideNoCharactersToShow();

        void showNoInternetConnection();

        void proceedToCharacterDetails(Character character);

    }

}
