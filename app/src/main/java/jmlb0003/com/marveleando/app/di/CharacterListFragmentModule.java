package jmlb0003.com.marveleando.app.di;

import java.util.List;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import jmlb0003.com.marveleando.domain.interactor.FetchFavoriteCharacters;
import jmlb0003.com.marveleando.domain.interactor.SearchCharacters;
import jmlb0003.com.marveleando.domain.interactor.UpdateCharacter;
import jmlb0003.com.marveleando.domain.interactor.UseCase;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.list.CharacterListFragmentPresenter;
import jmlb0003.com.marveleando.presentation.list.CharacterListFragmentPresenterImp;

@Module
public abstract class CharacterListFragmentModule {

    @Binds
    @Named(SearchCharacters.NAME)
    public abstract UseCase<SearchCharacters.Input, List<Character>> provideSearchCharactersUseCase(SearchCharacters useCase);

    @Binds
    @Named(FetchFavoriteCharacters.NAME)
    public abstract UseCase<Void, List<Character>> provideFetchFavoriteCharactersUseCase(FetchFavoriteCharacters useCase);

    @Binds
    @Named(UpdateCharacter.NAME)
    public abstract UseCase<Character, Void> provideUpdateCharacterUseCase(UpdateCharacter useCase);

    @Binds
    public abstract CharacterListFragmentPresenter provideCharacterListFragmentPresenter(CharacterListFragmentPresenterImp presenter);

}
