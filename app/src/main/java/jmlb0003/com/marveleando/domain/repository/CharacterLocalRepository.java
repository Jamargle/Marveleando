package jmlb0003.com.marveleando.domain.repository;

import java.util.List;

import jmlb0003.com.marveleando.domain.model.Character;

/**
 * Interface for accessing to Marvel characters information in local sources
 */
public interface CharacterLocalRepository {

    /**
     * Retrieve the list of Marvel characters from local resources such the database
     *
     * @return List of {@link Character}
     */
    List<Character> getCharacters();

}
