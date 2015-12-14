package ca.vickypatel.dagger_2.component;

import android.app.Application;

import javax.inject.Singleton;

import ca.vickypatel.dagger_2.modules.ApplicationModule;
import dagger.Component;

/**
 * Created by vicky on 14/12/15.
 */

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Application getApplication();
}
