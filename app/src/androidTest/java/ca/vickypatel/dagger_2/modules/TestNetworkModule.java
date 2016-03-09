package ca.vickypatel.dagger_2.modules;

import com.android.volley.RequestQueue;

import ca.vickypatel.dagger_2.extras.CustomScope;
import ca.vickypatel.dagger_2.network.VolleySingleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by VickyPatel on 2016-02-26.
 */
@Module
public class TestNetworkModule {

    @CustomScope
    @Provides
    VolleySingleton provideVolleySingleton(){
        return VolleySingleton.getInstance();
    }

    @CustomScope
    @Provides
    RequestQueue provideRequestQueue(VolleySingleton singleton){
        return singleton.getRequestQueue();
    }
}