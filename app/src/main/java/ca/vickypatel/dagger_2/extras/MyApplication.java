package ca.vickypatel.dagger_2.extras;

import android.app.Application;

import ca.vickypatel.dagger_2.component.DaggerStorageComponent;
import ca.vickypatel.dagger_2.component.StorageComponent;
import ca.vickypatel.dagger_2.modules.StorageModule;

/**
 * Created by vicky on 10/12/15.
 */
public class MyApplication extends Application{

    StorageComponent component;

    @Override
    public void onCreate(){
        super.onCreate();
        component = DaggerStorageComponent
                .builder()
                .storageModule(new StorageModule(this))
                .build();

    }

    public StorageComponent getComponent(){
        return component;
    }
}
