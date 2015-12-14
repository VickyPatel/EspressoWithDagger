package ca.vickypatel.dagger_2.modules;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

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


    @Provides
    SharedPreferences provideSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
