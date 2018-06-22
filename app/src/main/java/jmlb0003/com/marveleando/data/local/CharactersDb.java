package jmlb0003.com.marveleando.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import jmlb0003.com.marveleando.data.local.dao.CharacterDao;
import jmlb0003.com.marveleando.domain.model.Character;

@Database(entities = {
        Character.class
},
        version = 1,
        exportSchema = false)
public abstract class CharactersDb extends RoomDatabase {

    private static final String DATABASE_NAME = "marvel_characters_db";

    private static CharactersDb dbInstance;

    public static synchronized CharactersDb getInstance(final Context context) {
        if (dbInstance == null) {
            dbInstance = Room
                    .databaseBuilder(context.getApplicationContext(), CharactersDb.class, DATABASE_NAME)
                    .build();
        }
        return dbInstance;
    }

    public abstract CharacterDao getCharacterDao();

}
