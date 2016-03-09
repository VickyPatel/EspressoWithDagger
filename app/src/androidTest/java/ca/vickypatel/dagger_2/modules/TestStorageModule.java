package ca.vickypatel.dagger_2.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;

import ca.vickypatel.dagger_2.TestMyApplication;
import ca.vickypatel.dagger_2.extras.CustomScope;
import ca.vickypatel.dagger_2.extras.MyApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by VickyPatel on 2016-02-26.
 */
@Module
public class TestStorageModule {

    private final TestMyApplication application;

    public TestStorageModule(TestMyApplication application){
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