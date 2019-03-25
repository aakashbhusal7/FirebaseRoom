package practice.example.aakash.firebasesample.listedit;


import android.arch.lifecycle.Observer;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import practice.example.aakash.firebasesample.data.DatabaseManager;
import practice.example.aakash.firebasesample.data.dao.PersonDao;
import practice.example.aakash.firebasesample.data.entity.Person;

public class ListPresenter implements ListContract.Presenter {
    public final String TAG=ListPresenter.class.getSimpleName();
    private ListContract.View view;
    DatabaseManager databaseManager;
    private CompositeDisposable compositeDisposable;
    // PersonRepositoryImpl personRepositoryImpl;

//    @Inject
//    public ListPresenter(ListContract.View view,PersonRepositoryImpl personRepositoryImpl){
//        this.view=view;
//        this.view.setPresenter(this);
//        this.personRepositoryImpl=personRepositoryImpl;
//    }
    @Inject
    public ListPresenter(ListContract.View view,DatabaseManager databaseManager){
        this.view=view;
        this.view.setPresenter(this);
        this.databaseManager=databaseManager;
    }


    @Override
    public void addNewPerson() {
        view.showAddPerson();
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void populatePeople() {

        System.out.println("is main thread?= "+(Looper.myLooper()==Looper.getMainLooper()));
        compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(databaseManager.getPersonDao().findAllPeople()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Person>>() {
            @Override
            public void accept(List<Person> people) throws Exception {
                view.setPersons(people);
                if(people==null || people.size()<1){
                    view.showEmptyMessage();
                }
            }
        }));


//        databaseManager.getPersonDao().findAllPeople().observeForever(new Observer<List<Person>>() {
//            @Override
//            public void onChanged(@Nullable List<Person> personList) {
//                view.setPersons(personList);
//                if(personList==null || personList.size()<1){
//                    view.showEmptyMessage();
//                }
//            }
//        });
    }

    @Override
    public void openEditScreen(Person person) {
        view.showEditScreen(person.id);
    }

    @Override
    public void openConfirmDeleteDialog(Person person) {
        view.showDeleteConfirmDialog(person);
    }

    @Override
    public void delete(long personId) {
        Person person=databaseManager.getPersonDao().findPerson(personId);
        databaseManager.getPersonDao().deletePerson(person);
    }


    @Override
    public void start() {
    }


    @Override
    public void stop() {
    }
}
