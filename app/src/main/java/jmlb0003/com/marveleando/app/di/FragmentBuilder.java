package jmlb0003.com.marveleando.app.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import jmlb0003.com.marveleando.presentation.detail.CharacterDetailFragment;
import jmlb0003.com.marveleando.presentation.list.CharacterListFragment;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = {CharacterListFragmentModule.class})
    public abstract CharacterListFragment bindCharacterListFragmentFragment();

    @ContributesAndroidInjector(modules = {CharacterDetailFragmentModule.class})
    public abstract CharacterDetailFragment bindCharacterDetailFragment();

}
