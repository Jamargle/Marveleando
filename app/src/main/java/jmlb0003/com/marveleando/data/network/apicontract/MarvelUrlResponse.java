package jmlb0003.com.marveleando.data.network.apicontract;

import jmlb0003.com.marveleando.data.network.UrlType;

public final class MarvelUrlResponse {

    private String type;
    private String url;

    @UrlType
    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

}
