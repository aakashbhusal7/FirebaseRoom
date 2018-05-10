package practice.example.aakash.firebasesample.edit;


import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface EditActivitySubComponent extends AndroidInjector<EditActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<EditActivity>{}
}
