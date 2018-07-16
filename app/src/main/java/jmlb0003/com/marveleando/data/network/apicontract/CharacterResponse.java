package jmlb0003.com.marveleando.data.network.apicontract;

import java.util.Date;
import java.util.List;

public final class CharacterResponse {

    private int id;
    private String name;
    private String description;
    private Date modified;
    private String resourceURI;
    private List<MarvelUrlResponse> urls;
    private MarvelImageResponse thumbnail;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getModified() {
        return modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public List<MarvelUrlResponse> getUrls() {
        return urls;
    }

    public MarvelImageResponse getThumbnail() {
        return thumbnail;
    }

}
