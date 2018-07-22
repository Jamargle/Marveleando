package jmlb0003.com.marveleando.domain.repository;

import android.support.annotation.Nullable;

import java.util.List;

import jmlb0003.com.marveleando.domain.model.Character;

/**
 * Interface for retrieving Marvel characters information from the Marvel API
 */
public interface CharacterNetworkRepository {

    /**
     * Retrieve a list of Marvel characters from the Marvel API.
     *
     * @return List of {@link Character} containing the beginning ones from the remote database.
     */
    List<Character> getCharacters();

    /**
     * Retrieve a list of Marvel characters from the Marvel API.
     *
     * @param currentPage Number of characters to be skipped by the service as they are
     *                    already shown. It has to be greater than 0.
     * @return List of {@link Character} containing the ones in the page passed as parameter.
     */
    List<Character> getCharacters(int currentPage);

    /**
     * Retrieve a list of Marvel characters from the Marvel API given a name or part of the name.
     *
     * @param currentPage Number of characters to be skipped by the service as they are
     *                    already shown. It has to be greater than 0.
     * @param name        {@link String} with the name or part of the name of the characters you
     *                    want to fetch.
     * @return List of {@link Character}
     */
    List<Character> getCharactersByName(
            int currentPage,
            @Nullable String name);

}