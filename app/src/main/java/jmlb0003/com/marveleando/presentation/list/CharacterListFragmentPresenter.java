package jmlb0003.com.marveleando.presentation.list;

import android.support.annotation.Nullable;

import java.util.List;

import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BasePresenter;
import jmlb0003.com.marveleando.presentation.list.adapter.CharacterTransitionObject;

public interface CharacterListFragmentPresenter extends BasePresenter<CharacterListFragmentPresenter.CharacterListFragmentView> {

    void searchCharacterByName(
            int currentPage,
            @Nullable String query);

    void fetchMoreCharactersOnScroll(
            int currentPage,
            @Nullable String query);

    void showCharactersWithoutFilters();

    void showOnlyFavorites();

    void onNoNetworkConnection();

    void onCharacterDataSetEmpty();

    void onCharacterClicked(CharacterTransitionObject transitionData);

    interface CharacterListFragmentView extends BasePresenter.BaseView {

        void showLoading();

        void hideLoading();

        void setBeginningCharactersToShow(List<Character> characters);

        void addCharactersToShownList(List<Character> characters);

        void showNoCharactersToShow();

        void hideNoCharactersToShow();

        void showNoInternetConnection();

        void proceedToCharacterDetails(CharacterTransitionObject transitionData);

    }

}
