package jmlb0003.com.marveleando.app.di;

import java.util.List;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import jmlb0003.com.marveleando.domain.interactor.FetchBeginningCharacters;
import jmlb0003.com.marveleando.domain.interactor.UseCase;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.list.CharacterListFragmentPresenter;
import jmlb0003.com.marveleando.presentation.list.CharacterListFragmentPresenterImp;

@Module
public abstract class CharacterListFragmentModule {

    @Binds
    @Named(FetchBeginningCharacters.NAME)
    public abstract UseCase<Void, List<Character>> provideFetchCharactersUseCase(FetchBeginningCharacters useCase);

    @Binds
    public abstract CharacterListFragmentPresenter provideCharacterListFragmentPresenter(CharacterListFragmentPresenterImp presenter);

}
