package jmlb0003.com.marveleando.app.di;

import dagger.Binds;
import dagger.Module;
import jmlb0003.com.marveleando.presentation.list.CharacterListFragmentPresenter;
import jmlb0003.com.marveleando.presentation.list.CharacterListFragmentPresenterImp;

@Module
public abstract class CharacterListFragmentModule {

    @Binds
    public abstract CharacterListFragmentPresenter provideCharacterListFragmentPresenter(CharacterListFragmentPresenterImp presenter);

}
