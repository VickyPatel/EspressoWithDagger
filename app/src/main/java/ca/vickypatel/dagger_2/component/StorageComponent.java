package ca.vickypatel.dagger_2.component;

import ca.vickypatel.dagger_2.activity.ScrollingActivity;
import ca.vickypatel.dagger_2.modules.StorageModule;
import dagger.Component;

/**
 * Created by vicky on 10/12/15.
 */

@Component(modules = StorageModule.class)
public interface StorageComponent {

    void inject(ScrollingActivity scrollingActivity);
}
