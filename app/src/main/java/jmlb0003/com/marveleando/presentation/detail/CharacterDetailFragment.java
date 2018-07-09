package jmlb0003.com.marveleando.presentation.detail;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.presentation.BaseFragment;

public final class CharacterDetailFragment
        extends BaseFragment<CharacterDetailFragment.Callback, CharacterDetailFragmentPresenter> implements
        CharacterDetailFragmentPresenter.CharacterDetailFragmentView {

    @Inject CharacterDetailFragmentPresenter presenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_character_details;
    }

    @NonNull
    @Override
    protected CharacterDetailFragmentPresenter getPresenter() {
        return presenter;
    }

    interface Callback {
    }

}
