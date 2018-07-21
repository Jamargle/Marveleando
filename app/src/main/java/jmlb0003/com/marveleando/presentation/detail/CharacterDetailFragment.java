package jmlb0003.com.marveleando.presentation.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.model.MarvelUrl;
import jmlb0003.com.marveleando.presentation.BaseFragment;
import jmlb0003.com.marveleando.presentation.detail.adapter.LinkAdapter;

public final class CharacterDetailFragment
        extends BaseFragment<CharacterDetailFragment.Callback, CharacterDetailFragmentPresenter> implements
        CharacterDetailFragmentPresenter.CharacterDetailFragmentView,
        LinkAdapter.LinkAdapterListener {

    private static final String CHARACTER_TO_SHOW = "CharacterDetailFragment:Character_to_show";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.character_image) ImageView characterImageView;
    @BindView(R.id.character_name) TextView characterNameView;
    @BindView(R.id.character_description) TextView characterDescriptionView;
    @BindView(R.id.link_list_view) RecyclerView characterLinks;
    @BindView(R.id.favorite_button) FloatingActionButton favoriteButton;

    @Inject CharacterDetailFragmentPresenter presenter;

    private boolean isFavorite = false;

    public static CharacterDetailFragment newInstance(@NonNull final Character character) {
        final Bundle args = new Bundle();
        args.putParcelable(CHARACTER_TO_SHOW, character);
        final CharacterDetailFragment fragment = new CharacterDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(
            @NonNull final View view,
            @Nullable final Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        if (!getResources().getBoolean(R.bool.is_tablet)) {
            initToolbar();
        }
        if (getArguments() != null) {
            presenter.loadCharacterDetails((Character) getArguments().getParcelable(CHARACTER_TO_SHOW));
        }
    }

    private void initToolbar() {
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_character_details;
    }

    @Override
    protected boolean isToBeRetained() {
        return true;
    }

    @NonNull
    @Override
    protected CharacterDetailFragmentPresenter getPresenter() {
        return presenter;
    }

    @OnClick(R.id.favorite_button)
    protected void favoriteButtonClicked() {
        presenter.onFavoriteButtonClicked();
    }

    @Nullable
    public Character getCharacterStatus() {
        final Character character = getArguments().getParcelable(CHARACTER_TO_SHOW);
        if (character != null) {
            character.setFavorite(isFavorite);
        }
        return character;
    }

    @Override
    public void showCharacterImage(@NonNull final String imagePath) {
        Picasso.with(getActivity())
                .load(imagePath).fit().centerCrop()
                .placeholder(R.drawable.ic_marvel_balloon)
                .error(R.drawable.ic_marvel_balloon)
                .into(characterImageView);
    }

    @Override
    public void showCharacterName(@NonNull final String name) {
        characterNameView.setText(name);
    }

    @Override
    public void showCharacterDescription(@NonNull final String description) {
        if (description.isEmpty()) {
            characterDescriptionView.setText(
                    getString(R.string.character_description_empty, characterNameView.getText()));
        } else {
            characterDescriptionView.setText(description);
        }
    }

    @Override
    public void swapFavoriteCharacterState(final boolean isFavorite) {
        this.isFavorite = isFavorite;
        if (isFavorite) {
            favoriteButton.setImageResource(R.drawable.ic_not_favorite);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favorite);
        }
        callback.onFavoriteStateChanged(isFavorite);
    }

    @Override
    public void showCharacterLinks(final List<MarvelUrl> urls) {
        characterLinks.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        characterLinks.setAdapter(new LinkAdapter(urls, this));
    }

    @Override
    public void onLinkClicked(final MarvelUrl url) {
        callback.openLink(url.getUrl());
    }

    public interface Callback {

        void onFavoriteStateChanged(boolean isFavorite);

        void openLink(String url);

    }

}
