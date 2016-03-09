package ca.vickypatel.dagger_2;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by VickyPatel on 2016-03-09.
 */
public class TestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(@NonNull ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestMyApplication.class.getName(), context);
    }
}
