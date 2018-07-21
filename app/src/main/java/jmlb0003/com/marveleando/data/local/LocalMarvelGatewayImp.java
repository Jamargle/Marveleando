package jmlb0003.com.marveleando.data.local;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import jmlb0003.com.marveleando.app.utils.SharedPreferencesHandler;
import jmlb0003.com.marveleando.data.local.dao.CharacterDao;
import jmlb0003.com.marveleando.data.local.dao.UrlsDao;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.model.MarvelUrl;
import jmlb0003.com.marveleando.domain.repository.CharacterLocalRepository;

public final class LocalMarvelGatewayImp implements CharacterLocalRepository {

    private static final long REFRESH_EVERY_10_DAYS = 10 * 24 * 60 * 60 * 1000L;

    private final CharacterDao characterDao;
    private final UrlsDao urlsDao;
    private final SharedPreferencesHandler preferencesHandler;

    private long lastRefresh;

    @Inject
    public LocalMarvelGatewayImp(
            final CharacterDao characterDao,
            final UrlsDao urlsDao,
            final SharedPreferencesHandler preferencesHandler) {

        this.characterDao = characterDao;
        this.urlsDao = urlsDao;
        this.preferencesHandler = preferencesHandler;
        lastRefresh = preferencesHandler.getLastCharactersRefresh();
    }

    @Override
    public List<Character> getBeginningCharacters() {
        final List<Character> characters = characterDao.getBeginningCharacters();
        setUrlsToCharacters(characters);
        return characters;
    }

    @Override
    public List<Character> getFavoriteCharacters() {
        final List<Character> favorites = characterDao.getFavoriteCharacters();
        setUrlsToCharacters(favorites);
        return favorites;
    }

    private void setUrlsToCharacters(final List<Character> characters) {
        for (Character character : characters) {
            final List<MarvelUrl> urls = urlsDao.getUrlsByCharacter(character.getId());
            character.setUrls(urls);
        }
    }

    @Override
    public boolean beginningCharactersAreValid() {
        final long currentTime = new Date().getTime();
        return (currentTime - lastRefresh) <= REFRESH_EVERY_10_DAYS;
    }

    @Override
    public void refreshBeginningCharactersIfNeeded(final List<Character> characters) {
        if (!beginningCharactersAreValid()) {
            persist(characters);
            lastRefresh = new Date().getTime();
            preferencesHandler.setLastCharactersRefresh(lastRefresh);
        }
    }

    @Override
    public void persist(final List<Character> characters) {
        for (final Character character : characters) {
            characterDao.addCharacter(character);   // Add first the character to have an id as foreign key for the url
            for (MarvelUrl url : character.getUrls()) {
                urlsDao.addUrl(url);
            }
        }
    }

    @Override
    public void update(final Character characterToUpdate) {
        if (characterDao.getCharacter(characterToUpdate.getId()) != null) {
            characterDao.updateCharacter(characterToUpdate);
        } else {
            characterDao.addCharacter(characterToUpdate);
        }
    }

    @Override
    public void delete(final Character characterToDelete) {
        characterDao.deleteCharacter(characterToDelete);
    }

}
