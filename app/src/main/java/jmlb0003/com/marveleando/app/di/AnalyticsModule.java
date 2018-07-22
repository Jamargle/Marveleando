package jmlb0003.com.marveleando.app.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jmlb0003.com.marveleando.app.utils.FirebaseAnalyticsHelper;

@Module
public final class AnalyticsModule {

    @Provides
    @Singleton
    public FirebaseAnalyticsHelper provideFirebaseAnalyticsHelper(final Context applicationContext) {
        return new FirebaseAnalyticsHelper(applicationContext);
    }

}
