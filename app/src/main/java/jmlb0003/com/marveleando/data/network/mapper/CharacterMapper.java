package jmlb0003.com.marveleando.data.network.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import jmlb0003.com.marveleando.data.network.apicontract.CharacterResponse;
import jmlb0003.com.marveleando.data.network.apicontract.MarvelApiResponse;
import jmlb0003.com.marveleando.data.network.apicontract.MarvelUrlResponse;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.model.MarvelUrl;

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
            model.setImagePortrait(CharacterImageHelper.getPortraitImageUrl(characterResponse.getThumbnail()));
            model.setImageLandscape(CharacterImageHelper.getLandscapeImageUrl(characterResponse.getThumbnail()));
            model.setUrls(mapUrls(model.getId(), characterResponse.getUrls()));

            characters.add(model);
        }
        return characters;
    }

    private static List<MarvelUrl> mapUrls(
            final int characterId,
            final List<MarvelUrlResponse> urlsFromResponse) {

        final List<MarvelUrl> urls = new ArrayList<>();
        if (urlsFromResponse != null) {
            for (final MarvelUrlResponse urlResponse : urlsFromResponse) {
                final MarvelUrl url = new MarvelUrl();
                url.setCharacterId(characterId);
                url.setType(urlResponse.getType());
                url.setUrl(urlResponse.getUrl());
                urls.add(url);
            }
        }
        return urls;
    }

}
