package ca.vickypatel.dagger_2.modules;

import android.app.Application;

import javax.inject.Singleton;

import ca.vickypatel.dagger_2.extras.CustomScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by vicky on 14/12/15.
 */
@Module
public class ApplicationModule {

    private static Application mApplication;

    public ApplicationModule(Application application){
        mApplication = application;
    }

    @Singleton
    @Provides
    Application provideApplication(){
        return mApplication;
    }
}
