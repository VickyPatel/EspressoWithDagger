package ca.vickypatel.dagger_2.component;

import javax.inject.Singleton;

import ca.vickypatel.dagger_2.activity.DisplayActivity;
import ca.vickypatel.dagger_2.activity.ScrollingActivity;
import ca.vickypatel.dagger_2.modules.DatabaseModule;
import ca.vickypatel.dagger_2.modules.NetworkModule;
import ca.vickypatel.dagger_2.modules.StorageModule;
import dagger.Component;

/**
 * Created by vicky on 11/12/15.
 */

@Singleton
@Component(modules = {NetworkModule.class, StorageModule.class, DatabaseModule.class})
public interface NetworkComponent {
    void inject(ScrollingActivity scrollingActivity);
    void inject(DisplayActivity displayActivity);
}
