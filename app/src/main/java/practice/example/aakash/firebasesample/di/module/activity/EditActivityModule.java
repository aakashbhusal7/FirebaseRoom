package practice.example.aakash.firebasesample.di.module.activity;

import dagger.Module;
import dagger.Provides;
import practice.example.aakash.firebasesample.data.DatabaseManager;
import practice.example.aakash.firebasesample.data.dao.PersonDao;
import practice.example.aakash.firebasesample.di.scope.PerActivity;
import practice.example.aakash.firebasesample.edit.EditActivity;
import practice.example.aakash.firebasesample.edit.EditContract;
import practice.example.aakash.firebasesample.edit.EditPresenter;

@Module
public class EditActivityModule {
    @PerActivity
    @Provides
    EditContract.View provideEditView(EditActivity editActivity) {
        return editActivity;
    }

//    @PerActivity
//    @Provides
//    EditContract.Presenter provideEditPresenter(EditContract.View view, PersonRepositoryImpl personRepositoryImpl) {
//        return new EditPresenter(view, personRepositoryImpl);
//    }
//    @Provides
//    PersonRepository providePersonRepository(DatabaseManager databaseManager){
//        return new PersonRepositoryImpl(databaseManager);
//    }
    @PerActivity
    @Provides
    EditContract.Presenter provideEditPresenter(EditContract.View view, DatabaseManager databaseManager){
        return new EditPresenter(view,databaseManager);
    }
}
