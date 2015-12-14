package ca.vickypatel.dagger_2.modules;

import android.content.Context;

import javax.inject.Singleton;

import ca.vickypatel.dagger_2.adapter.DatabaseAdapter;
import ca.vickypatel.dagger_2.extras.CustomScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by vicky on 13/12/15.
 */
@Module
public class DatabaseModule {
    Context context;
    public DatabaseModule(Context context){
        this.context = context;
    }

    @CustomScope
    @Provides
    DatabaseAdapter provideDatabaseAdapter(){
        return new DatabaseAdapter(context);
    }
}
