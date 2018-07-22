package jmlb0003.com.marveleando.data.network.apicontract;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class CharacterDataContainer {

    private int offset;
    private int limit;
    private int total;
    private int count;
    @SerializedName("results") private List<CharacterResponse> characters;

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public List<CharacterResponse> getCharacters() {
        return characters;
    }

}
