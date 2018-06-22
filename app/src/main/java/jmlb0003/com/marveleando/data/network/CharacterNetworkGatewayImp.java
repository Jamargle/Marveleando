package jmlb0003.com.marveleando.data.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jmlb0003.com.marveleando.data.network.apicontract.CharacterDataContainer;
import jmlb0003.com.marveleando.data.network.apicontract.CharacterResponse;
import jmlb0003.com.marveleando.data.network.apicontract.MarvelApiResponse;
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

        // TODO This method has a lot of improvements to do

        final List<Character> characters = new ArrayList<>();
        try {
            final MarvelApiResponse response = call.execute().body();
            if (response != null) {
                final CharacterDataContainer data = response.getData();
                final List<CharacterResponse> charactersReponse = data.getCharacters();
                for (CharacterResponse characterResponse : charactersReponse) {
                    final Character character = new Character();
                    character.setId(characterResponse.getId());
                    character.setName(characterResponse.getName());
                    character.setDescription(characterResponse.getDescription());
                    character.setImage(characterResponse.getThumbnail().getPath());
//                    character.setUrls(characterResponse.getUrls());

                    characters.add(character);
                }
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
