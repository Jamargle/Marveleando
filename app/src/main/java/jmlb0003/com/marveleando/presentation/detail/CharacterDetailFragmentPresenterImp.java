package jmlb0003.com.marveleando.presentation.detail;

import android.support.annotation.Nullable;

import javax.inject.Inject;
import javax.inject.Named;

import jmlb0003.com.marveleando.app.utils.FirebaseAnalyticsHelper;
import jmlb0003.com.marveleando.domain.interactor.DefaultObserver;
import jmlb0003.com.marveleando.domain.interactor.UpdateCharacter;
import jmlb0003.com.marveleando.domain.interactor.UseCase;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.model.MarvelUrl;
import jmlb0003.com.marveleando.presentation.BasePresenterImpl;

public final class CharacterDetailFragmentPresenterImp
        extends BasePresenterImpl<CharacterDetailFragmentPresenter.CharacterDetailFragmentView>
        implements CharacterDetailFragmentPresenter {

    private final UseCase<Character, Void> updateCharacterUseCase;
    private final FirebaseAnalyticsHelper firebaseAnalyticsHelper;

    @Nullable
    private Character character;

    @Inject
    public CharacterDetailFragmentPresenterImp(
            @Named(UpdateCharacter.NAME) final UseCase<Character, Void> updateCharacterUseCase,
            final FirebaseAnalyticsHelper analyticsHelper) {

        this.updateCharacterUseCase = updateCharacterUseCase;
        this.firebaseAnalyticsHelper = analyticsHelper;
    }

    @Override
    public void loadCharacterDetails(final Character character) {
        if (character == null) {
            return;
        } else {
            this.character = character;
        }

        final CharacterDetailFragmentView view = getView();
        if (view != null) {
            if (character.getName() != null) {
                view.showCharacterName(character.getName());
            }
            if (character.getDescription() != null) {
                view.showCharacterDescription(character.getDescription());
            }

            if (character.getImagePortrait() != null) {
                view.showCharacterImage(character.getImagePortrait());
            }

            if (character.getUrls() != null) {
                view.showCharacterLinks(character.getUrls());
            }

            view.swapFavoriteCharacterState(character.isFavorite());
        }
    }

    @Override
    public void onFavoriteButtonClicked() {
        if (character == null) {
            return;
        }
        character.setFavorite(!character.isFavorite());

        updateCharacterUseCase.execute(character, new DefaultObserver<Void>() {
            @Override
            public void processOnComplete() {
                firebaseAnalyticsHelper.logMarkedAsFavorite(character);
                if (getView() != null) {
                    getView().swapFavoriteCharacterState(character.isFavorite());
                }

                dispose();
            }

            @Override
            public void processOnError(final Throwable exception) {
                character.setFavorite(!character.isFavorite());
            }
        });
    }

    @Override
    public void onLinkClicked(final MarvelUrl url) {
        firebaseAnalyticsHelper.logCharacterLinkCLicked(character, url);
        if (getView() != null) {
            getView().goToShowLink(url);
        }
    }

}
