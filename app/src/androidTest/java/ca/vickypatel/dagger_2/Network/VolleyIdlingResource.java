package ca.vickypatel.dagger_2.Network;

import android.support.test.espresso.IdlingResource;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import java.lang.reflect.Field;
import java.util.Set;

import javax.inject.Inject;

import ca.vickypatel.dagger_2.TestMyApplication;
import ca.vickypatel.dagger_2.network.VolleySingleton;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by VickyPatel on 2016-03-09.
 */
public final class VolleyIdlingResource implements IdlingResource {

    @Inject
    public VolleySingleton volleySingleton;

    @Inject
    public RequestQueue requestQueue;


    private static final String TAG = "VolleyIdlingResource";
    private final String resourceName;

    // written from main thread, read from any thread.
    private volatile ResourceCallback resourceCallback;

    private Field mCurrentRequests;

    public VolleyIdlingResource(String resourceName) throws SecurityException, NoSuchFieldException {
        this.resourceName = resourceName;
        mCurrentRequests = RequestQueue.class.getDeclaredField("mCurrentRequests");
        mCurrentRequests.setAccessible(true);

        TestMyApplication app = (TestMyApplication) getInstrumentation().getTargetContext().getApplicationContext();
        app.getTestNetworkComponent().inject(this);
    }

    @Override
    public String getName() {
        return resourceName;
    }

    @Override
    public boolean isIdleNow() {
        try {
            Set<Request> set = (Set<Request>) mCurrentRequests.get(requestQueue);
            int count = set.size();
            if (set != null) {

                if (count == 0) {
                    Log.d(TAG, "Volley is idle now! with: " + count);
                    resourceCallback.onTransitionToIdle();
                } else {
                    Log.d(TAG, "Not idle... " + count);
                }
                return count == 0;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d(TAG, "Eita porra.. ");
        return true;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

}
