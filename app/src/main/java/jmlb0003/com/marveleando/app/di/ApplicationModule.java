package jmlb0003.com.marveleando.app.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import jmlb0003.com.marveleando.MarveleandoApp;
import jmlb0003.com.marveleando.data.JobExecutor;
import jmlb0003.com.marveleando.domain.interactor.PostExecutionThread;
import jmlb0003.com.marveleando.domain.interactor.ThreadExecutor;
import jmlb0003.com.marveleando.presentation.UiThread;

@Module
public abstract class ApplicationModule {

    @Binds
    @Singleton
    public abstract ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor);

    @Binds
    @Singleton
    public abstract PostExecutionThread providePostExecutionThread(UiThread uiThread);

    @Binds
    @Singleton
    public abstract Context provideApplicationContext(MarveleandoApp application);

}
