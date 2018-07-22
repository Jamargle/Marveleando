package jmlb0003.com.marveleando.data.network.apicontract;

public final class MarvelImageResponse {

    public static final String SIZE_PORTRAIT_UNCANNY = "portrait_uncanny";
    public static final String SIZE_LANDSCAPE_INCREDIBLE = "landscape_incredible";

    private String path;
    private String extension;

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

}
