package jmlb0003.com.marveleando.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import jmlb0003.com.marveleando.domain.model.MarvelUrl;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UrlsDao {

    @Query("SELECT * FROM " + MarvelUrl.TABLE_NAME + " WHERE " + MarvelUrl.COLUMN_CHARACTER_ID + " =:characterId")
    List<MarvelUrl> getUrlsByCharacter(int characterId);

    @Insert(onConflict = REPLACE)
    void addUrl(MarvelUrl url);

    @Delete
    void deleteUrl(MarvelUrl url);

    @Update(onConflict = REPLACE)
    void updateUrl(MarvelUrl url);

}
