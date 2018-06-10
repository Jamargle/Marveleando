package jmlb0003.com.marveleando.app.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import jmlb0003.com.marveleando.presentation.list.CharacterListActivity;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    public abstract CharacterListActivity bindCharacterListActivity();

}
