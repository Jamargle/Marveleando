package jmlb0003.com.marveleando.presentation.detail;

import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BasePresenter;

public interface CharacterDetailFragmentPresenter extends BasePresenter<CharacterDetailFragmentPresenter.CharacterDetailFragmentView> {

    void loadCharacterDetails(Character character);

    interface CharacterDetailFragmentView extends BasePresenter.BaseView {

        void showCharacterImage(String imagePath);

        void showCharacterName(String name);

        void showCharacterDescription(String description);

    }

}
