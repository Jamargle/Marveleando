package jmlb0003.com.marveleando.app.di;

import dagger.Binds;
import dagger.Module;
import jmlb0003.com.marveleando.presentation.detail.CharacterDetailFragmentPresenter;
import jmlb0003.com.marveleando.presentation.detail.CharacterDetailFragmentPresenterImp;

@Module
public abstract class CharacterDetailFragmentModule {

    @Binds
    public abstract CharacterDetailFragmentPresenter provideCharacterDetailFragmentPresenter(CharacterDetailFragmentPresenterImp presenter);

}
