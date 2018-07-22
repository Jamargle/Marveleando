package jmlb0003.com.marveleando.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import jmlb0003.com.marveleando.domain.model.Character;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CharacterDao {

    @Query("SELECT * FROM " + Character.TABLE_NAME + " ORDER BY " + Character.COLUMN_NAME + " LIMIT 20")
    List<Character> getBeginningCharacters();

    @Query("SELECT * FROM " + Character.TABLE_NAME + " WHERE " + Character.COLUMN_IS_FAVORITE + " =1" + " ORDER BY " + Character.COLUMN_NAME)
    List<Character> getFavoriteCharacters();

    @Query("SELECT * FROM " + Character.TABLE_NAME + " WHERE " + Character.COLUMN_ID + " =:characterId")
    Character getCharacter(int characterId);

    @Insert(onConflict = REPLACE)
    void addCharacter(Character character);

    @Delete
    void deleteCharacter(Character character);

    @Update(onConflict = REPLACE)
    void updateCharacter(Character character);

}
