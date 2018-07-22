package jmlb0003.com.marveleando.app.di;

import dagger.Binds;
import dagger.Module;
import jmlb0003.com.marveleando.presentation.detail.CharacterDetailActivityPresenter;
import jmlb0003.com.marveleando.presentation.detail.CharacterDetailActivityPresenterImp;

@Module
abstract class CharacterDetailActivityModule {

    @Binds
    public abstract CharacterDetailActivityPresenter provideCharacterDetailActivityPresenter(CharacterDetailActivityPresenterImp presenter);

}
