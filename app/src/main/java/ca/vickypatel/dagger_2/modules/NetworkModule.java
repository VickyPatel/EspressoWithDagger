package ca.vickypatel.dagger_2.modules;

import com.android.volley.RequestQueue;

import javax.inject.Singleton;

import ca.vickypatel.dagger_2.network.VolleySingleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by vicky on 11/12/15.
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    VolleySingleton provideVolleySingleton(){
        return VolleySingleton.getInstance();
    }

    @Singleton
    @Provides
    RequestQueue provideRequestQueue(VolleySingleton singleton){
        return singleton.getRequestQueue();
    }
}
