package jmlb0003.com.marveleando.domain.repository;

import java.util.List;

import jmlb0003.com.marveleando.domain.model.Character;

/**
 * Interface for accessing to Marvel characters information in local sources
 */
public interface CharacterLocalRepository {

    /**
     * Retrieve the list of 20 Marvel characters from local database
     *
     * @return List of {@link Character}
     */
    List<Character> getBeginningCharacters();

    List<Character> getFavoriteCharacters();

    boolean beginningCharactersAreValid();

    void refreshBeginningCharactersIfNeeded(List<Character> characters);

    void persist(List<Character> characters);

    void update(Character characterToUpdate);

    void delete(Character characterToDelete);

}
