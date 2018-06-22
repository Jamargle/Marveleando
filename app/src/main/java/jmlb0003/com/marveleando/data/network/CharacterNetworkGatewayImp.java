package jmlb0003.com.marveleando.data.network;

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

    private final MarvelApiClient apiClient;

    @Inject
    public CharacterNetworkGatewayImp(final MarvelApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public List<Character> getCharacters(final int maxCharacters) {
        final String maxCharactersToFetch;
        if (maxCharacters > 0 && maxCharacters < 100) {
            maxCharactersToFetch = String.valueOf(maxCharacters);
        } else {
            maxCharactersToFetch = null;
        }
        final Call<MarvelApiResponse> call = apiClient.getListOfCharacters(null, maxCharactersToFetch, null);

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

    @Override
    public List<Character> getCharactersByName(int maxCharacters, String name) {
        // TODO Implement get characters by name
        return null;
    }

}
