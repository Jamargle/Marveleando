package jmlb0003.com.marveleando.app.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jmlb0003.com.marveleando.app.utils.ServiceGenerator;
import jmlb0003.com.marveleando.app.utils.SharedPreferencesHandler;
import jmlb0003.com.marveleando.data.local.LocalMarvelGatewayImp;
import jmlb0003.com.marveleando.data.network.CharacterNetworkGatewayImp;
import jmlb0003.com.marveleando.data.network.MarvelApiClient;
import jmlb0003.com.marveleando.domain.repository.CharacterLocalRepository;
import jmlb0003.com.marveleando.domain.repository.CharacterNetworkRepository;

@Module
public final class RepositoryModule {

    @Provides
    @Singleton
    public SharedPreferencesHandler provideSharedPreferencesHandler(final Context context) {
        return new SharedPreferencesHandler(context);
    }

    @Provides
    @Singleton
    public CharacterLocalRepository providesLocalRecipesGateway(final LocalMarvelGatewayImp repository) {
        return repository;
    }

    @Provides
    @Singleton
    public CharacterNetworkRepository providesCharacterNetworkRepository() {
        return new CharacterNetworkGatewayImp(ServiceGenerator.createService(MarvelApiClient.class));
    }

}
