package practice.example.aakash.firebasesample.di.module.activity;

import dagger.Module;
import dagger.Provides;
import practice.example.aakash.firebasesample.data.DatabaseManager;
import practice.example.aakash.firebasesample.data.dao.PersonDao;
import practice.example.aakash.firebasesample.di.scope.PerActivity;
import practice.example.aakash.firebasesample.listedit.ListActivity;
import practice.example.aakash.firebasesample.listedit.ListContract;
import practice.example.aakash.firebasesample.listedit.ListPresenter;

@Module
public class ListActivityModule {
    @PerActivity
    @Provides
    ListContract.View provideListView(ListActivity listActivity){
        return listActivity;
    }
//    @PerActivity
//    @Provides
//    ListContract.Presenter provideListPresenter(ListContract.View view, PersonRepositoryImpl personRepositoryImpl){
//        return new ListPresenter(view,personRepositoryImpl);
//    }
    @PerActivity
    @Provides
    ListContract.Presenter provideListPresenter(ListContract.View view, DatabaseManager databaseManager){
        return new ListPresenter(view,databaseManager);
    }

}
