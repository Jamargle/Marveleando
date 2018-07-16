package jmlb0003.com.marveleando.data.network;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jmlb0003.com.marveleando.data.network.apicontract.MarvelApiResponse;
import jmlb0003.com.marveleando.data.network.mapper.CharacterMapper;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.repository.CharacterNetworkRepository;
import retrofit2.Call;

public final class CharacterNetworkGatewayImp implements CharacterNetworkRepository {

    private static final String MAX_CHARACTERS_TO_DOWNLOAD = "20";
    private final MarvelApiClient apiClient;

    @Inject
    public CharacterNetworkGatewayImp(final MarvelApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public List<Character> getCharacters() {
        return getCharacters(1);
    }

    @Override
    public List<Character> getCharacters(final int currentPage) {
        return getCharactersByName(currentPage, null);
    }

    @Override
    public List<Character> getCharactersByName(
            final int currentPage,
            @Nullable final String name) {

        final String offset;
        if (currentPage <= 1) {
            offset = "0";
        } else {
            final int max = Integer.parseInt(MAX_CHARACTERS_TO_DOWNLOAD);
            final int currentCharactersShown = max * (currentPage - 1);
            offset = String.valueOf(currentCharactersShown);
        }
        final String searchText;
        if (name != null && name.length() == 0) {
            searchText = null;
        } else {
            searchText = name;
        }
        final Call<MarvelApiResponse> call = apiClient.getListOfCharacters(searchText, null, offset);
        return parseCharactersFromResponse(call);
    }

    private List<Character> parseCharactersFromResponse(final Call<MarvelApiResponse> call) {
        final List<Character> characters = new ArrayList<>();
        try {
            final MarvelApiResponse response = call.execute().body();
            if (response != null) {
                characters.addAll(CharacterMapper.mapToModel(response));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characters;
    }

}
