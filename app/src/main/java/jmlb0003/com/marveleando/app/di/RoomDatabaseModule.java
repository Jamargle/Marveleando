package jmlb0003.com.marveleando.app.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jmlb0003.com.marveleando.MarveleandoApp;
import jmlb0003.com.marveleando.data.local.CharactersDb;
import jmlb0003.com.marveleando.data.local.dao.CharacterDao;
import jmlb0003.com.marveleando.data.local.dao.UrlsDao;

@Module
class RoomDatabaseModule {

    @Singleton
    @Provides
    CharactersDb providesRoomDatabase(final MarveleandoApp app) {
        return CharactersDb.getInstance(app);
    }

    @Singleton
    @Provides
    CharacterDao providesCharacterDao(final CharactersDb database) {
        return database.getCharacterDao();
    }

    @Singleton
    @Provides
    UrlsDao providesUrlsDao(final CharactersDb database) {
        return database.getUrlsDao();
    }

}
