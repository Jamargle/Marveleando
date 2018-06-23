package jmlb0003.com.marveleando.data.local;

import java.util.List;

import javax.inject.Inject;

import jmlb0003.com.marveleando.app.utils.SharedPreferencesHandler;
import jmlb0003.com.marveleando.data.local.dao.CharacterDao;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.repository.CharacterLocalRepository;

public final class LocalMarvelGatewayImp implements CharacterLocalRepository {

    private final CharacterDao characterDao;
    private final SharedPreferencesHandler preferencesHandler;

    private long lastRefresh;

    @Inject
    public LocalMarvelGatewayImp(
            final CharacterDao characterDao,
            final SharedPreferencesHandler preferencesHandler) {

        this.characterDao = characterDao;
        this.preferencesHandler = preferencesHandler;
        lastRefresh = preferencesHandler.getLastCharactersRefresh();
    }

    @Override
    public List<Character> getCharacters() {
        return characterDao.getCharacters();
    }

}
