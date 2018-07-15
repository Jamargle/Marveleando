package jmlb0003.com.marveleando.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import static jmlb0003.com.marveleando.domain.model.Character.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public final class Character implements Parcelable {

    public static final String TABLE_NAME = "characters";

    private static final int URL_DEFAULT_COUNT = 3;
    private static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE_PORTRAIT = "image_portrait";
    private static final String COLUMN_IMAGE_LANDSCAPE = "image_landscape";
    public static final String COLUMN_IS_FAVORITE = "is_favorite";

    public Character(){}
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

    @ColumnInfo(name = COLUMN_IS_FAVORITE)
    private boolean favorite;

    @Ignore
    private final List<String> urls = new ArrayList<>(URL_DEFAULT_COUNT);

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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(final boolean favorite) {
        this.favorite = favorite;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(final List<String> urls) {
        if (urls != null) {
            this.urls.addAll(urls);
        }
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(final Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(final int size) {
            return new Character[size];
        }
    };

    protected Character(final Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        imagePortrait = in.readString();
        imageLandscape = in.readString();
        favorite = in.readInt() == 1;
        urls.addAll(in.createStringArrayList());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imagePortrait);
        dest.writeString(imageLandscape);
        dest.writeInt(favorite ? 1 : 0);
        dest.writeStringList(urls);
    }

}
