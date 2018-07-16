package jmlb0003.com.marveleando.data.network.mapper;

import android.support.annotation.NonNull;

import jmlb0003.com.marveleando.data.network.apicontract.MarvelImageResponse;

public final class CharacterImageHelper {

    private CharacterImageHelper() {
    }

    @NonNull
    public static String getPortraitImageUrl(final MarvelImageResponse response) throws IllegalArgumentException {
        if (response == null) {
            throw new IllegalArgumentException("The response to map was null");
        }
        return response.getPath() + "/" + MarvelImageResponse.SIZE_PORTRAIT_UNCANNY +
                "." + response.getExtension();
    }

    @NonNull
    public static String getLandscapeImageUrl(final MarvelImageResponse response) throws IllegalArgumentException {
        if (response == null) {
            throw new IllegalArgumentException("The response to map was null");
        }
        return response.getPath() + "/" + MarvelImageResponse.SIZE_LANDSCAPE_INCREDIBLE +
                "." + response.getExtension();
    }

}
