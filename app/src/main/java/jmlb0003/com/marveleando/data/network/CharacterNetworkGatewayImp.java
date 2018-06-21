package jmlb0003.com.marveleando.data.network;

import java.util.List;

import javax.inject.Inject;

import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.repository.CharacterNetworkRepository;

public final class CharacterNetworkGatewayImp implements CharacterNetworkRepository {

    @Inject
    public CharacterNetworkGatewayImp() {

    }

    @Override
    public List<Character> getCharacters(int maxCharacters) {
        return null;
    }

    @Override
    public List<Character> getCharactersByName(int maxCharacters, String name) {
        return null;
    }
}
