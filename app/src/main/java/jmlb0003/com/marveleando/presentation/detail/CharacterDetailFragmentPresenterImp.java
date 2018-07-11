package jmlb0003.com.marveleando.presentation.detail;

import javax.inject.Inject;

import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BasePresenterImpl;

public final class CharacterDetailFragmentPresenterImp
        extends BasePresenterImpl<CharacterDetailFragmentPresenter.CharacterDetailFragmentView>
        implements CharacterDetailFragmentPresenter {

    @Inject
    public CharacterDetailFragmentPresenterImp() {
    }

    @Override
    public void loadCharacterDetails(final Character character) {
        if (character == null) {
            return;
        }
        final CharacterDetailFragmentView view = getView();
        if (view != null) {
            if (character.getName() != null) {
                view.showCharacterName(character.getName());
            }
            if (character.getDescription() != null) {
                view.showCharacterDescription(character.getDescription());
            }

            if (character.getImageLandscape() != null) {
                view.showCharacterImage(character.getImagePortrait());
            }
        }
    }

}
