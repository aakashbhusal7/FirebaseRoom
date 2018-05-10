package practice.example.aakash.firebasesample.di.component;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import practice.example.aakash.firebasesample.MyApplication;
import practice.example.aakash.firebasesample.di.module.ApplicationModule;
import practice.example.aakash.firebasesample.di.module.provider.ActivityBindingModule;
import practice.example.aakash.firebasesample.di.scope.PerApplication;

@PerApplication
@Component(modules={ApplicationModule.class,AndroidInjectionModule.class,
        ActivityBindingModule.class})
public interface ApplicationComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
    void inject(MyApplication app);

}

