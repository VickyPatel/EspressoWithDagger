package ca.vickypatel.dagger_2.components;

import android.app.Application;

import javax.inject.Singleton;

import ca.vickypatel.dagger_2.TestActivity.MainActivityTest;
import ca.vickypatel.dagger_2.component.ApplicationComponent;
import ca.vickypatel.dagger_2.modules.TestApplicationModule;
import dagger.Component;

/**
 * Created by VickyPatel on 2016-02-26.
 */
@Singleton
@Component(modules = TestApplicationModule.class)
public interface TestApplicationComponent extends ApplicationComponent{
    Application getApplication();
//    void inject(MainActivityTest mainActivityTest);
}
