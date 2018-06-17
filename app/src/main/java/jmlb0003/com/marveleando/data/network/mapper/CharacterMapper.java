package jmlb0003.com.marveleando.data.network.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import jmlb0003.com.marveleando.data.network.apicontract.CharacterResponse;
import jmlb0003.com.marveleando.data.network.apicontract.MarvelApiResponse;
import jmlb0003.com.marveleando.data.network.apicontract.MarvelUrlResponse;
import jmlb0003.com.marveleando.domain.model.Character;

public final class CharacterMapper {

    private CharacterMapper() {
    }

    @NonNull
    public static List<Character> mapToModel(final MarvelApiResponse response) throws IllegalArgumentException {
        if (response == null) {
            throw new IllegalArgumentException("The response to map was null");
        }
        final List<Character> characters = new ArrayList<>();
        final List<CharacterResponse> charactersToMap = response.getData().getCharacters();
        for (CharacterResponse characterResponse : charactersToMap) {
            final Character model = new Character();
            model.setId(characterResponse.getId());
            model.setName(characterResponse.getName());
            model.setDescription(characterResponse.getDescription());
            model.setImage(characterResponse.getThumbnail().getPath());
            model.setUrls(mapUrls(characterResponse.getUrls()));

            characters.add(model);
        }
        return characters;
    }

    private static List<String> mapUrls(final List<MarvelUrlResponse> urlsFromResponse) {
        final List<String> urls = new ArrayList<>();
        if (urlsFromResponse != null) {
            for (MarvelUrlResponse url : urlsFromResponse) {
                urls.add(url.getUrl());
            }
        }
        return urls;
    }

}
