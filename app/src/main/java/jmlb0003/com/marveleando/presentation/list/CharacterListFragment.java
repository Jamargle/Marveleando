package jmlb0003.com.marveleando.presentation.list;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BaseFragment;

public final class CharacterListFragment
        extends BaseFragment implements
        CharacterListFragmentPresenter.CharacterListFragmentView {

    @Inject CharacterListFragmentPresenter presenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_character_list;
    }

    @NonNull
    @Override
    protected CharacterListFragmentPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void updateCharactersToShow(final List<Character> characters) {

    }

    @Override
    public void showNoCharactersToShow() {

    }

    @Override
    public void showNoInternetConnection() {

    }

}
