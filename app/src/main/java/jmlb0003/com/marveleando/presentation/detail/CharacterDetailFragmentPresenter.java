package jmlb0003.com.marveleando.presentation.detail;

import android.support.annotation.NonNull;

import java.util.List;

import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.model.MarvelUrl;
import jmlb0003.com.marveleando.presentation.BasePresenter;

public interface CharacterDetailFragmentPresenter extends BasePresenter<CharacterDetailFragmentPresenter.CharacterDetailFragmentView> {

    void loadCharacterDetails(Character character);

    void onFavoriteButtonClicked();

    interface CharacterDetailFragmentView extends BasePresenter.BaseView {

        void showCharacterImage(@NonNull String imagePath);

        void showCharacterName(@NonNull String name);

        void showCharacterDescription(@NonNull String description);

        void swapFavoriteCharacterState(boolean isFavorite);

        void showCharacterLinks(List<MarvelUrl> urls);

    }

}
