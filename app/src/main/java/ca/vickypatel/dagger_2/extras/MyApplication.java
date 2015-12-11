package ca.vickypatel.dagger_2.extras;

import android.app.Application;
import android.content.Context;

import ca.vickypatel.dagger_2.component.DaggerNetworkComponent;
import ca.vickypatel.dagger_2.component.NetworkComponent;
import ca.vickypatel.dagger_2.component.StorageComponent;
import ca.vickypatel.dagger_2.modules.NetworkModule;
import ca.vickypatel.dagger_2.modules.StorageModule;

/**
 * Created by vicky on 10/12/15.
 */
public class MyApplication extends Application{

    NetworkComponent component;
    private static MyApplication instance;

    @Override
    public void onCreate(){
        super.onCreate();
        component = DaggerNetworkComponent.builder().storageModule(new StorageModule(this)).networkModule(new NetworkModule()).build();

        instance = this;
    }

    public NetworkComponent getComponent(){
        return component;
    }

    public static MyApplication getsInstance(){
        return instance;
    }

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }
}
