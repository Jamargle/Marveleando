package jmlb0003.com.marveleando.presentation.list;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import jmlb0003.com.marveleando.domain.interactor.DefaultObserver;
import jmlb0003.com.marveleando.domain.interactor.SearchCharacters;
import jmlb0003.com.marveleando.domain.interactor.UseCase;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BasePresenterImpl;

public final class CharacterListFragmentPresenterImp
        extends BasePresenterImpl<CharacterListFragmentPresenter.CharacterListFragmentView>
        implements CharacterListFragmentPresenter {

    private final UseCase<SearchCharacters.Input, List<Character>> searchCharactersUseCase;

    @Inject
    public CharacterListFragmentPresenterImp(
            @Named(SearchCharacters.NAME) final UseCase<SearchCharacters.Input, List<Character>> searchCharactersUseCase) {

        this.searchCharactersUseCase = searchCharactersUseCase;
    }

    @Override
    public void searchCharacterByName(
            final int currentPage,
            final String query) {

        if (getView() != null) {
            getView().showLoading();
        }
        searchCharactersUseCase.execute(
                new SearchCharacters.Input(currentPage, query),
                new DefaultObserver<List<Character>>() {

                    @Override
                    public void processOnNext(final List<Character> characters) {
                        if (getView() != null) {
                            getView().hideNoCharactersToShow();
                            getView().setBeginningCharactersToShow(characters);
                            getView().hideLoading();
                        }
                    }

                    @Override
                    public void processOnError(final Throwable exception) {
                        if (getView() != null) {
                            getView().hideLoading();
                            getView().showNoCharactersToShow();
                        }
                    }
                });
    }

    @Override
    public void fetchMoreCharactersOnScroll(
            final int currentPage,
            @Nullable final String query) {

        if (getView() != null) {
            getView().showLoading();
        }
        searchCharactersUseCase.execute(
                new SearchCharacters.Input(currentPage, query),
                new DefaultObserver<List<Character>>() {

                    @Override
                    public void processOnNext(final List<Character> characters) {
                        if (getView() != null) {
                            getView().hideNoCharactersToShow();
                            getView().addCharactersToShownList(characters);
                            getView().hideLoading();
                        }
                    }

                    @Override
                    public void processOnError(final Throwable exception) {
                        if (getView() != null) {
                            getView().hideLoading();
                            getView().showNoCharactersToShow();
                        }
                    }
                });
    }

    @Override
    public void showCharactersWithoutFilters() {

    }

    @Override
    public void showOnlyFavorites() {

    }

    @Override
    public void onNoNetworkConnection() {

    }

    @Override
    public void onCharacterDataSetEmpty() {
        if (getView() != null) {
            getView().showNoCharactersToShow();
        }
    }

    @Override
    public void onCharacterClicked(final Character character) {
        if (getView() != null) {
            getView().proceedToCharacterDetails(character);
        }
    }

}
