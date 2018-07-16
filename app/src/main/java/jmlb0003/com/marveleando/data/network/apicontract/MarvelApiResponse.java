package jmlb0003.com.marveleando.data.network.apicontract;

import com.google.gson.annotations.SerializedName;

public final class MarvelApiResponse {

    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    @SerializedName("data") private CharacterDataContainer characterDataContainer;
    @SerializedName("etag") private String eTag;

    public String getCopyright() {
        return copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public CharacterDataContainer getData() {
        return characterDataContainer;
    }

}
