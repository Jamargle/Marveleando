package jmlb0003.com.marveleando.domain.repository;

import java.util.List;

/**
 * Interface for retrieving Marvel characters information from the Marvel API
 */
public interface CharacterNetworkRepository {

    /**
     * Retrieve a list of Marvel characters from the Marvel API.
     *
     * @param maxCharacters Maximum number of characters returned by the service. It has to be
     *                      a value from 0 to 100.
     * @return List of {@link Character}
     */
    List<Character> getCharacters(int maxCharacters);

    /**
     * Retrieve a list of Marvel characters from the Marvel API given a name or part of the name.
     *
     * @param maxCharacters Maximum number of characters returned by the service. It has to be
     *                      a value from 0 to 100.
     * @param name          {@link String} with the name or part of the name of the characters you
     *                      want to fetch.
     * @return
     */
    List<Character> getCharactersByName(
            int maxCharacters,
            String name);

}