package practice.example.aakash.firebasesample.di.module.provider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import practice.example.aakash.firebasesample.di.module.activity.EditActivityModule;
import practice.example.aakash.firebasesample.di.module.activity.ListActivityModule;
import practice.example.aakash.firebasesample.di.scope.PerActivity;
import practice.example.aakash.firebasesample.edit.EditActivity;
import practice.example.aakash.firebasesample.listedit.ListActivity;

@Module
public abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = EditActivityModule.class)
    abstract EditActivity bindEditActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = ListActivityModule.class)
    abstract ListActivity bindListActivity();
}



