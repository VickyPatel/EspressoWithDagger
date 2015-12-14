package ca.vickypatel.dagger_2.extras;

import android.app.Application;
import android.content.Context;

import ca.vickypatel.dagger_2.component.ApplicationComponent;
import ca.vickypatel.dagger_2.component.DaggerApplicationComponent;
import ca.vickypatel.dagger_2.component.DaggerNetworkComponent;
import ca.vickypatel.dagger_2.component.NetworkComponent;
import ca.vickypatel.dagger_2.component.StorageComponent;
import ca.vickypatel.dagger_2.modules.ApplicationModule;
import ca.vickypatel.dagger_2.modules.DatabaseModule;
import ca.vickypatel.dagger_2.modules.NetworkModule;
import ca.vickypatel.dagger_2.modules.StorageModule;

/**
 * Created by vicky on 10/12/15.
 */
public class MyApplication extends Application{

    ApplicationComponent applicationComponent;
    NetworkComponent networkComponent;
    private static MyApplication instance;

    @Override
    public void onCreate(){
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();


        networkComponent = DaggerNetworkComponent
                .builder()
                .applicationComponent(applicationComponent)
                .storageModule(new StorageModule(this))
                .networkModule(new NetworkModule())
                .databaseModule(new DatabaseModule(this))
                .build();

        instance = this;
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
    public NetworkComponent getComponent(){
        return networkComponent;
    }

    public static MyApplication getsInstance(){
        return instance;
    }

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }
}
