package jmlb0003.com.marveleando.app.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jmlb0003.com.marveleando.MarveleandoApp;
import jmlb0003.com.marveleando.data.JobExecutor;
import jmlb0003.com.marveleando.domain.interactor.PostExecutionThread;
import jmlb0003.com.marveleando.domain.interactor.ThreadExecutor;
import jmlb0003.com.marveleando.presentation.UiThread;

@Module
public final class ApplicationModule {

    @Provides
    @Singleton
    public ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }

    @Provides
    @Singleton
    public PostExecutionThread providePostExecutionThread() {
        return new UiThread();
    }

    @Provides
    @Singleton
    Context provideApplicationContext(final MarveleandoApp application) {
        return application;
    }

}
