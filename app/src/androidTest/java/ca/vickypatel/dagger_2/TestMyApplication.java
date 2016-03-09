package ca.vickypatel.dagger_2;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import ca.vickypatel.dagger_2.components.DaggerTestNetworkComponent;
import ca.vickypatel.dagger_2.components.TestNetworkComponent;
import ca.vickypatel.dagger_2.extras.MyApplication;
import ca.vickypatel.dagger_2.modules.DatabaseModule;
import ca.vickypatel.dagger_2.modules.NetworkModule;
import ca.vickypatel.dagger_2.modules.StorageModule;
import ca.vickypatel.dagger_2.modules.TestDatabaseModule;
import ca.vickypatel.dagger_2.modules.TestNetworkModule;
import ca.vickypatel.dagger_2.modules.TestStorageModule;

/**
 * Created by VickyPatel on 2016-02-26.
 */
public class TestMyApplication extends MyApplication {

    TestNetworkComponent testNetworkComponent;
    private static TestMyApplication instance;

    @Override
    public void onCreate(){
        super.onCreate();

//        testNetworkComponent = DaggerTestNetworkComponent
//                .builder()
//                .testStorageModule(new TestStorageModule(this))
//                .testDatabaseModule(new TestDatabaseModule(this))
//                .testNetworkModule(new TestNetworkModule())
//                .build();

        instance = this;
    }



    public void setTestNetworkComponent(){

        testNetworkComponent = DaggerTestNetworkComponent
                .builder()
                .testStorageModule(new TestStorageModule(this))
                .testDatabaseModule(new TestDatabaseModule(this))
                .testNetworkModule(new TestNetworkModule())
                .build();

    }
    public TestNetworkComponent getTestNetworkComponent(){
        return testNetworkComponent;
    }

    public static TestMyApplication getsInstance(){
        return instance;
    }

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }
}
