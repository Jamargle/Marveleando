package jmlb0003.com.marveleando.app.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import jmlb0003.com.marveleando.presentation.detail.CharacterDetailActivity;
import jmlb0003.com.marveleando.presentation.list.CharacterListActivity;
import jmlb0003.com.marveleando.presentation.splash.SplashActivity;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {SplashActivityModule.class})
    public abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {CharacterListActivityModule.class, FragmentBuilder.class})
    public abstract CharacterListActivity bindCharacterListActivity();

    @ContributesAndroidInjector(modules = {CharacterDetailActivityModule.class, FragmentBuilder.class})
    public abstract CharacterDetailActivity bindCharacterDetailActivity();

}
