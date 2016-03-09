package ca.vickypatel.dagger_2.modules;

import android.content.Context;

import ca.vickypatel.dagger_2.adapter.DatabaseAdapter;
import ca.vickypatel.dagger_2.extras.CustomScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by VickyPatel on 2016-02-26.
 */
@Module
public class TestDatabaseModule {
    Context context;
    public TestDatabaseModule(Context context){
        this.context = context;
    }

    @CustomScope
    @Provides
    DatabaseAdapter provideDatabaseAdapter(){
        return new DatabaseAdapter(context);
    }
}