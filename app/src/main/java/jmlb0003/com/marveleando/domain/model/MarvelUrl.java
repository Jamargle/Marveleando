package jmlb0003.com.marveleando.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import jmlb0003.com.marveleando.data.network.UrlType;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static jmlb0003.com.marveleando.domain.model.MarvelUrl.COLUMN_CHARACTER_ID;
import static jmlb0003.com.marveleando.domain.model.MarvelUrl.COLUMN_URL;
import static jmlb0003.com.marveleando.domain.model.MarvelUrl.TABLE_NAME;

@Entity(
        tableName = TABLE_NAME,
        primaryKeys = {COLUMN_CHARACTER_ID, COLUMN_URL},
        foreignKeys = @ForeignKey(
                entity = Character.class,
                parentColumns = Character.COLUMN_ID,
                childColumns = COLUMN_CHARACTER_ID,
                onDelete = CASCADE
        )
)
public final class MarvelUrl implements Parcelable {

    public static final String TABLE_NAME = "urls";
    public static final String COLUMN_CHARACTER_ID = "character_id";
    public static final String COLUMN_URL = "url";
    private static final String COLUMN_URL_TYPE = "url_type";

    @ColumnInfo(index = true, name = COLUMN_CHARACTER_ID)
    private int characterId;

    @ColumnInfo(name = COLUMN_URL_TYPE)
    private String type;

    @ColumnInfo(name = COLUMN_URL)
    @NonNull
    private String url = "";

    public MarvelUrl() {
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(final int characterId) {
        this.characterId = characterId;
    }

    @NonNull
    @UrlType
    public String getType() {
        return type;
    }

    public void setType(@NonNull final String type) {
        this.type = type;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull final String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MarvelUrl> CREATOR = new Creator<MarvelUrl>() {
        @Override
        public MarvelUrl createFromParcel(final Parcel in) {
            return new MarvelUrl(in);
        }

        @Override
        public MarvelUrl[] newArray(final int size) {
            return new MarvelUrl[size];
        }
    };

    protected MarvelUrl(final Parcel in) {
        characterId = in.readInt();
        type = in.readString();
        url = in.readString();
    }
    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(characterId);
        dest.writeString(type);
        dest.writeString(url);
    }

}
