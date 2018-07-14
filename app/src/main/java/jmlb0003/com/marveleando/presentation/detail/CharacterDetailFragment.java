package jmlb0003.com.marveleando.presentation.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BaseFragment;

public final class CharacterDetailFragment
        extends BaseFragment<CharacterDetailFragment.Callback, CharacterDetailFragmentPresenter> implements
        CharacterDetailFragmentPresenter.CharacterDetailFragmentView {

    private static final String CHARACTER_TO_SHOW = "CharacterDetailFragment:Character_to_show";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.character_image) ImageView characterImageView;
    @BindView(R.id.character_name) TextView characterNameView;
    @BindView(R.id.character_description) TextView characterDescriptionView;

    @Inject CharacterDetailFragmentPresenter presenter;

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
        initToolbar();
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

    @NonNull
    @Override
    protected CharacterDetailFragmentPresenter getPresenter() {
        return presenter;
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

    interface Callback {
    }

}
