package ca.vickypatel.dagger_2.TestActivity;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.android.volley.RequestQueue;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.inject.Named;

import ca.vickypatel.dagger_2.DaggerActivityTestRule;
import ca.vickypatel.dagger_2.Network.VolleyIdlingResource;
import ca.vickypatel.dagger_2.R;
import ca.vickypatel.dagger_2.TestMyApplication;
import ca.vickypatel.dagger_2.activity.DisplayActivity;
import ca.vickypatel.dagger_2.activity.ScrollingActivity;
import ca.vickypatel.dagger_2.adapter.DatabaseAdapter;
import ca.vickypatel.dagger_2.component.DaggerNetworkComponent;
import ca.vickypatel.dagger_2.components.DaggerTestNetworkComponent;
import ca.vickypatel.dagger_2.components.TestNetworkComponent;
import ca.vickypatel.dagger_2.extras.MyApplication;
import ca.vickypatel.dagger_2.modules.NetworkModule;
import ca.vickypatel.dagger_2.modules.TestDatabaseModule;
import ca.vickypatel.dagger_2.modules.TestNetworkModule;
import ca.vickypatel.dagger_2.modules.TestStorageModule;
import ca.vickypatel.dagger_2.network.VolleySingleton;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by VickyPatel on 2016-02-25.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private TestNetworkComponent mTestNetworkComponent;

    @Inject
    @Named("default")
    public SharedPreferences defaultSharedPreferences;

    @Inject
    @Named("secret")
    public SharedPreferences secretSharedPreferences;

    @Inject
    public VolleySingleton volleySingleton;

    @Inject
    public RequestQueue requestQueue;

    @Inject
    public DatabaseAdapter adapter;


    @Rule
    public ActivityTestRule<ScrollingActivity> mActivityRule = new ActivityTestRule<>(ScrollingActivity.class);
//            new DaggerActivityTestRule<>(ScrollingActivity.class, new DaggerActivityTestRule.OnBeforeActivityLaunchedListener<ScrollingActivity>() {
//                @Override
//                public void beforeActivityLaunched(@NonNull Application application, @NonNull ScrollingActivity activity) {
//
//                    TestMyApplication app = (TestMyApplication) getInstrumentation().getTargetContext().getApplicationContext();
//                    TestNetworkComponent  testNetworkComponent = DaggerTestNetworkComponent
//                           .builder()
//                           .testStorageModule(new TestStorageModule(TestMyApplication.getsInstance()))
//                           .testDatabaseModule(new TestDatabaseModule(TestMyApplication.getsInstance()))
//                           .testNetworkModule(new TestNetworkModule())
//                           .build();
//
//                    ((MyApplication) application).setNetworkComponent(testNetworkComponent);
//              }
//            });

    @Before
    public void setUp() {
        TestMyApplication app = (TestMyApplication) getInstrumentation().getTargetContext().getApplicationContext();
        app.setTestNetworkComponent();
        app.getTestNetworkComponent().inject(this);

    }

    @Test
    public void networkCallTest() {

        onView(withId(R.id.makeNetworkCall)).perform(click());

        VolleyIdlingResource volleyResources = null;
        try {
            volleyResources = new VolleyIdlingResource("VolleyCalls");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        registerIdlingResources(volleyResources);

        Assert.assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", secretSharedPreferences.getString("response", ""));


    }


}
