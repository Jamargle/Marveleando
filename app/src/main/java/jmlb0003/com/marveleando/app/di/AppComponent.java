package jmlb0003.com.marveleando.app.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import jmlb0003.com.marveleando.MarveleandoApp;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        AndroidInjectionModule.class,
        ActivityBuilder.class,
        RepositoryModule.class,
        RoomDatabaseModule.class
})
public interface AppComponent extends AndroidInjector<MarveleandoApp> {

    void inject(Application application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(MarveleandoApp application);

        AppComponent build();

    }

}
