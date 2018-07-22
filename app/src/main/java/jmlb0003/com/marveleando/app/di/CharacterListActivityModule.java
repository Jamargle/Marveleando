package jmlb0003.com.marveleando.app.di;

import dagger.Binds;
import dagger.Module;
import jmlb0003.com.marveleando.presentation.list.CharacterListActivityPresenter;
import jmlb0003.com.marveleando.presentation.list.CharacterListActivityPresenterImp;

@Module
abstract class CharacterListActivityModule {

    @Binds
    public abstract CharacterListActivityPresenter provideCharacterListActivityPresenter(CharacterListActivityPresenterImp presenter);

}
