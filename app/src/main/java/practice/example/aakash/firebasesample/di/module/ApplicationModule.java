package practice.example.aakash.firebasesample.di.module;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import practice.example.aakash.firebasesample.data.DatabaseManager;
import practice.example.aakash.firebasesample.di.scope.PerApplication;

@Module
public class ApplicationModule {
    @Provides
    @PerApplication
    Context provideContext(Application application){
        return application;
    }

    @Provides
    @PerApplication
    DatabaseManager provideDatabaseManager(Context context){
        return new DatabaseManager(context);
    }
}
