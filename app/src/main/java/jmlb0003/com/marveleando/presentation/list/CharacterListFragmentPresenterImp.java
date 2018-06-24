package jmlb0003.com.marveleando.presentation.list;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import jmlb0003.com.marveleando.domain.interactor.DefaultObserver;
import jmlb0003.com.marveleando.domain.interactor.FetchBeginningCharacters;
import jmlb0003.com.marveleando.domain.interactor.UseCase;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BasePresenterImpl;

public final class CharacterListFragmentPresenterImp
        extends BasePresenterImpl<CharacterListFragmentPresenter.CharacterListFragmentView>
        implements CharacterListFragmentPresenter {

    private final UseCase<Void, List<Character>> fetchCharactersUseCase;

    @Inject
    public CharacterListFragmentPresenterImp(
            @Named(FetchBeginningCharacters.NAME) final UseCase<Void, List<Character>> fetchCharactersUseCase) {

        this.fetchCharactersUseCase = fetchCharactersUseCase;
    }

    @Override
    public void refreshCharacters() {
        if (getView() != null) {
            getView().showLoading();
        }
        fetchCharactersUseCase.execute(null, new DefaultObserver<List<Character>>() {

            @Override
            public void processOnNext(final List<Character> characters) {
                super.processOnNext(characters);
                if (getView() != null) {
                    getView().hideNoCharactersToShow();
                    getView().updateCharactersToShow(characters);
                    getView().hideLoading();
                }
            }

        });
    }

    @Override
    public void fetchMoreCharacters(int currentPage) {
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
