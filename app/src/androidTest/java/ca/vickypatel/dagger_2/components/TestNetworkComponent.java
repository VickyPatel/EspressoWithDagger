package ca.vickypatel.dagger_2.components;

import ca.vickypatel.dagger_2.Network.VolleyIdlingResource;
import ca.vickypatel.dagger_2.TestActivity.MainActivityTest;
import ca.vickypatel.dagger_2.activity.DisplayActivity;
import ca.vickypatel.dagger_2.activity.ScrollingActivity;
import ca.vickypatel.dagger_2.component.ApplicationComponent;
import ca.vickypatel.dagger_2.component.NetworkComponent;
import ca.vickypatel.dagger_2.extras.CustomScope;
import ca.vickypatel.dagger_2.modules.DatabaseModule;
import ca.vickypatel.dagger_2.modules.NetworkModule;
import ca.vickypatel.dagger_2.modules.StorageModule;
import ca.vickypatel.dagger_2.modules.TestDatabaseModule;
import ca.vickypatel.dagger_2.modules.TestNetworkModule;
import ca.vickypatel.dagger_2.modules.TestStorageModule;
import dagger.Component;

/**
 * Created by VickyPatel on 2016-02-26.
 */

@CustomScope
@Component(modules = {TestNetworkModule.class, TestStorageModule.class, TestDatabaseModule.class})
public interface TestNetworkComponent extends NetworkComponent {
        void inject(MainActivityTest mainActivityTest);
        void inject(VolleyIdlingResource volleyIdlingResource);
}