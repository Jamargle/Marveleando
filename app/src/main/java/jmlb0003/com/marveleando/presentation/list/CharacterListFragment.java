package jmlb0003.com.marveleando.presentation.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.app.utils.EndlessRecyclerOnScrollListener;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BaseFragment;
import jmlb0003.com.marveleando.presentation.list.adapter.MarvelCharacterAdapter;

public final class CharacterListFragment
        extends BaseFragment<CharacterListFragment.Callback, CharacterListFragmentPresenter> implements
        CharacterListFragmentPresenter.CharacterListFragmentView,
        MarvelCharacterAdapter.CharacterAdapterListener {

    private static final String SAVED_CURRENT_PAGE = "key:CharacterListFragment_current_page";
    private static final String SAVED_CURRENT_SEARCH = "key:CharacterListFragment_current_search";

    @BindView(R.id.character_grid) RecyclerView charactersRecyclerView;
    @BindView(R.id.characters_progress_loading) ProgressBar loadingView;
    @BindView(R.id.empty_list) TextView emptyListView;

    @Inject CharacterListFragmentPresenter presenter;

    private MarvelCharacterAdapter adapter;
    private int currentPage = 0;
    private String currentQueryText = null;
    private EndlessRecyclerOnScrollListener scrollListener;

    @Override
    public void onViewCreated(
            @NonNull final View view,
            @Nullable final Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        adapter = new MarvelCharacterAdapter(this);
        charactersRecyclerView.setAdapter(adapter);
        final GridLayoutManager layoutManager = (GridLayoutManager) charactersRecyclerView.getLayoutManager();
        scrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {

            @Override
            public void onLoadMore(final int currentPage) {
                CharacterListFragment.this.currentPage = currentPage;
                presenter.fetchMoreCharacters(currentPage, currentQueryText);
            }

        };
        charactersRecyclerView.addOnScrollListener(scrollListener);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            recoverState(savedInstanceState);
        } else {
            presenter.fetchCharacters(0);
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        outState.putInt(SAVED_CURRENT_PAGE, currentPage);
        outState.putString(SAVED_CURRENT_SEARCH, currentQueryText);

        super.onSaveInstanceState(outState);
    }

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
    public void setCharactersToShow(final List<Character> characters) {
        adapter.showCharacters(characters);
    }

    @Override
    public void addCharactersToShownList(final List<Character> characters) {
        adapter.addCharacters(characters);
    }

    @Override
    public void showNoCharactersToShow() {
        charactersRecyclerView.setVisibility(View.GONE);
        emptyListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoCharactersToShow() {
        charactersRecyclerView.setVisibility(View.VISIBLE);
        emptyListView.setVisibility(View.GONE);
    }

    @Override
    public void showNoInternetConnection() {
        // TODO Implement no internet connection message
    }

    @Override
    public void proceedToCharacterDetails(final Character character) {
        callback.onNavigateToCharacterDetails(character);
    }

    @Override
    public void onCharacterDataSetEmpty() {
        presenter.onCharacterDataSetEmpty();
    }

    @Override
    public void onCharacterClicked(final Character character) {
        presenter.onCharacterClicked(character);
    }

    public void searchCharacter(final String query) {
        currentQueryText = query;
        scrollListener.setCurrentPage(0);
        presenter.searchCharacterByName(0, query);
    }

    private void recoverState(final Bundle state) {
        if (state.containsKey(SAVED_CURRENT_PAGE)) {
            currentPage = state.getInt(SAVED_CURRENT_PAGE);
        }
        if (state.containsKey(SAVED_CURRENT_SEARCH)) {
            currentQueryText = state.getString(SAVED_CURRENT_SEARCH);
        }

        scrollListener.setCurrentPage(currentPage - 1);
        presenter.searchCharacterByName(currentPage - 1, currentQueryText);
    }

    interface Callback {

        void onNavigateToCharacterDetails(Character character);

    }

}
