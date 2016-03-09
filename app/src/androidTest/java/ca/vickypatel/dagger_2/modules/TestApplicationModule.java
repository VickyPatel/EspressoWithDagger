package ca.vickypatel.dagger_2.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VickyPatel on 2016-02-26.
 */
@Module
public class TestApplicationModule {

    private static Application mApplication;

    public TestApplicationModule(Application application){
        mApplication = application;
    }

    @Singleton
    @Provides
    Application provideApplication(){
        return mApplication;
    }
}