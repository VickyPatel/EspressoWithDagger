package ca.vickypatel.dagger_2.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import ca.vickypatel.dagger_2.extras.CustomScope;
import ca.vickypatel.dagger_2.extras.MyApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by vicky on 10/12/15.
 */
@Module
public class StorageModule {

    private final MyApplication application;

    public StorageModule(MyApplication application){
        this.application = application;
    }

    @CustomScope
    @Provides
    @Named("default")
    SharedPreferences provideDefaultSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @CustomScope
    @Provides
    @Named("secret")
    SharedPreferences provideSecretSharedPreferences(){
        return  application.getSharedPreferences("test_file", Context.MODE_PRIVATE);
    }
}
