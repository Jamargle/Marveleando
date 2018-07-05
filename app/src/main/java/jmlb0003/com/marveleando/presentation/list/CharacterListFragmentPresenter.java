package jmlb0003.com.marveleando.presentation.list;

import android.support.annotation.Nullable;

import java.util.List;

import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BasePresenter;

public interface CharacterListFragmentPresenter extends BasePresenter<CharacterListFragmentPresenter.CharacterListFragmentView> {

    /**
     * It launches the download of Marvel characters for the page given as parameter
     *
     * @param currentPage Integer with the value for the current page of Marvel characters to
     *                    download
     */
    void fetchCharacters(int currentPage);

    void fetchMoreCharacters(
            int currentPage,
            @Nullable String query);

    void onNoNetworkConnection();

    void onCharacterDataSetEmpty();

    void onCharacterClicked(Character character);

    void searchCharacterByName(
            int currentPage,
            @Nullable String query);

    interface CharacterListFragmentView extends BasePresenter.BaseView {

        void showLoading();

        void hideLoading();

        void setCharactersToShow(List<Character> characters);

        void addCharactersToShownList(List<Character> characters);

        void showNoCharactersToShow();

        void hideNoCharactersToShow();

        void showNoInternetConnection();

        void proceedToCharacterDetails(Character character);

    }

}
