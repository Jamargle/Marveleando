package jmlb0003.com.marveleando.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import static jmlb0003.com.marveleando.domain.model.Character.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public final class Character {

    public static final String TABLE_NAME = "characters";

    private static final int URL_DEFAULT_COUNT = 3;
    private static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE_PORTRAIT = "image_portrait";
    private static final String COLUMN_IMAGE_LANDSCAPE = "image_landscape";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private int id;

    @ColumnInfo(name = COLUMN_NAME)
    private String name;

    @ColumnInfo(name = COLUMN_DESCRIPTION)
    private String description;

    @ColumnInfo(name = COLUMN_IMAGE_PORTRAIT)
    private String imagePortrait;

    @ColumnInfo(name = COLUMN_IMAGE_LANDSCAPE)
    private String imageLandscape;

    @Ignore
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

    public String getImagePortrait() {
        return imagePortrait;
    }

    public void setImagePortrait(final String imagePortrait) {
        this.imagePortrait = imagePortrait;
    }

    public String getImageLandscape() {
        return imageLandscape;
    }

    public void setImageLandscape(final String imageLandscape) {
        this.imageLandscape = imageLandscape;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(final List<String> urls) {
        this.urls = urls;
    }

}
