package jmlb0003.com.marveleando.data.local;

import java.util.List;

import javax.inject.Inject;

import jmlb0003.com.marveleando.data.local.dao.CharacterDao;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.repository.CharacterLocalRepository;

public final class LocalMarvelGatewayImp implements CharacterLocalRepository {

    private final CharacterDao characterDao;

    @Inject
    public LocalMarvelGatewayImp(final CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    @Override
    public List<Character> getCharacters() {
        return characterDao.getCharacters();
    }

}
