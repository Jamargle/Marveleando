package jmlb0003.com.marveleando.presentation.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BaseFragment;

public final class CharacterListFragment
        extends BaseFragment<CharacterListFragment.Callback, CharacterListFragmentPresenter> implements
        CharacterListFragmentPresenter.CharacterListFragmentView {

    @BindView(R.id.character_grid) RecyclerView charactersRecyclerView;
    @BindView(R.id.characters_progress_loading) ProgressBar loadingView;
    @BindView(R.id.empty_list) TextView emptyListView;

    @Inject CharacterListFragmentPresenter presenter;

    private Callback callback;

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
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void updateCharactersToShow(final List<Character> characters) {
    }

    @Override
    public void showNoCharactersToShow() {
        charactersRecyclerView.setVisibility(View.GONE);
        emptyListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoInternetConnection() {
        // TODO Implement no internet connection message
    }

    interface Callback {
    }

}
