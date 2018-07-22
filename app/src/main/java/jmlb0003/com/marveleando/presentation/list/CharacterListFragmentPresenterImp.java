package jmlb0003.com.marveleando.presentation.list;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import jmlb0003.com.marveleando.app.utils.FirebaseAnalyticsHelper;
import jmlb0003.com.marveleando.domain.interactor.DefaultObserver;
import jmlb0003.com.marveleando.domain.interactor.FetchFavoriteCharacters;
import jmlb0003.com.marveleando.domain.interactor.SearchCharacters;
import jmlb0003.com.marveleando.domain.interactor.UseCase;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BasePresenterImpl;
import jmlb0003.com.marveleando.presentation.list.adapter.CharacterTransitionObject;

public final class CharacterListFragmentPresenterImp
        extends BasePresenterImpl<CharacterListFragmentPresenter.CharacterListFragmentView>
        implements CharacterListFragmentPresenter {

    private final UseCase<SearchCharacters.Input, List<Character>> searchCharactersUseCase;
    private final UseCase<Void, List<Character>> fetchFavoriteCharactersUseCase;
    private final FirebaseAnalyticsHelper firebaseAnalyticsHelper;

    @Inject
    public CharacterListFragmentPresenterImp(
            @Named(SearchCharacters.NAME) final UseCase<SearchCharacters.Input, List<Character>> searchCharactersUseCase,
            @Named(FetchFavoriteCharacters.NAME) final UseCase<Void, List<Character>> fetchFavoriteCharactersUseCase,
            final FirebaseAnalyticsHelper firebaseAnalyticsHelper) {

        this.searchCharactersUseCase = searchCharactersUseCase;
        this.fetchFavoriteCharactersUseCase = fetchFavoriteCharactersUseCase;
        this.firebaseAnalyticsHelper = firebaseAnalyticsHelper;
    }

    @Override
    public void searchCharacterByName(
            final int currentPage,
            final String query) {

        firebaseAnalyticsHelper.logSearch(query);
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
                            getView().enableLoadMoreOnScroll();
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

        firebaseAnalyticsHelper.logScroll(currentPage, query);
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
        firebaseAnalyticsHelper.logShowWithoutFilters();
        searchCharacterByName(0, null);
    }

    @Override
    public void showOnlyFavorites() {
        firebaseAnalyticsHelper.logShowFavorites();
        if (getView() != null) {
            getView().showLoading();
        }
        fetchFavoriteCharactersUseCase.execute(
                null,
                new DefaultObserver<List<Character>>() {

                    @Override
                    public void processOnNext(final List<Character> characters) {
                        if (getView() != null) {
                            getView().hideNoCharactersToShow();
                            getView().disableLoadMoreOnScroll();
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
    public void onNoNetworkConnection() {

    }

    @Override
    public void onCharacterDataSetEmpty() {
        if (getView() != null) {
            getView().showNoCharactersToShow();
        }
    }

    @Override
    public void onCharacterClicked(CharacterTransitionObject transitionData) {
        firebaseAnalyticsHelper.logCharacterClicked(transitionData.getCharacter());
        if (getView() != null) {
            getView().proceedToCharacterDetails(transitionData);
        }
    }

}
