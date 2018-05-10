package practice.example.aakash.firebasesample.di.component.activity;


import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import practice.example.aakash.firebasesample.MainActivity;

@Subcomponent
public interface MainActivitySubComponent extends AndroidInjector<MainActivity>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
