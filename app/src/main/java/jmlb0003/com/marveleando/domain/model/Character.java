package jmlb0003.com.marveleando.domain.model;

import java.util.ArrayList;
import java.util.List;

public final class Character {

    private static final int URL_DEFAULT_COUNT = 3;

    private int id;
    private String name;
    private String description;
    private String image;
    private List<String> urls = new ArrayList<>(URL_DEFAULT_COUNT);

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(final List<String> urls) {
        this.urls = urls;
    }

}
