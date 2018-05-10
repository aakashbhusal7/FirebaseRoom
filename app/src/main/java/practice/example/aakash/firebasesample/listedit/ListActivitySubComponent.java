package practice.example.aakash.firebasesample.listedit;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import practice.example.aakash.firebasesample.LoginActivity;

@Subcomponent
public interface ListActivitySubComponent extends AndroidInjector<ListActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ListActivity>{}
}
