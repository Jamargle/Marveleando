package jmlb0003.com.marveleando.presentation.list.adapter;

import android.support.v4.util.Pair;
import android.view.View;

import jmlb0003.com.marveleando.domain.model.Character;

public final class CharacterTransitionObject {

    private final Pair<View, String> imageForTransition;
    private final Pair<View, String> nameForTransition;
    private final Character character;

    public CharacterTransitionObject(
            final Pair<View, String> imageForTransition,
            final Pair<View, String> nameForTransition,
            final Character character) {

        this.imageForTransition = imageForTransition;
        this.nameForTransition = nameForTransition;
        this.character = character;
    }

    public Pair<View, String> getImageForTransition() {
        return imageForTransition;
    }

    public Pair<View, String> getNameForTransition() {
        return nameForTransition;
    }

    public Character getCharacter() {
        return character;
    }

}
