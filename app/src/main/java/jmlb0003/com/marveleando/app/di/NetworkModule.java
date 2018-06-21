package jmlb0003.com.marveleando.app.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jmlb0003.com.marveleando.app.utils.ServiceGenerator;
import jmlb0003.com.marveleando.data.network.CharacterNetworkGatewayImp;
import jmlb0003.com.marveleando.data.network.MarvelApiClient;
import jmlb0003.com.marveleando.domain.repository.CharacterNetworkRepository;

@Module
public final class NetworkModule {

    @Provides
    @Singleton
    public CharacterNetworkRepository providesCharacterNetworkRepository() {
        return new CharacterNetworkGatewayImp(ServiceGenerator.createService(MarvelApiClient.class));
    }

}
