package jmlb0003.com.marveleando.app.di;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import jmlb0003.com.marveleando.domain.interactor.UpdateCharacter;
import jmlb0003.com.marveleando.domain.interactor.UseCase;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.detail.CharacterDetailFragmentPresenter;
import jmlb0003.com.marveleando.presentation.detail.CharacterDetailFragmentPresenterImp;

@Module
public abstract class CharacterDetailFragmentModule {

    @Binds
    @Named(UpdateCharacter.NAME)
    public abstract UseCase<Character, Void> provideUpdateCharacterUseCase(UpdateCharacter useCase);

    @Binds
    public abstract CharacterDetailFragmentPresenter provideCharacterDetailFragmentPresenter(CharacterDetailFragmentPresenterImp presenter);

}
