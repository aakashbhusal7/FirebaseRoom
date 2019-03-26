package practice.example.aakash.firebasesample.listedit;


import android.os.Looper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import practice.example.aakash.firebasesample.data.DatabaseManager;
import practice.example.aakash.firebasesample.data.entity.Person;

public class ListPresenter implements ListContract.Presenter {
    public final String TAG=ListPresenter.class.getSimpleName();
    private ListContract.View view;
    DatabaseManager databaseManager;
    private CompositeDisposable compositeDisposable;

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
    public void delete(final long personId) {

                compositeDisposable=new CompositeDisposable();
                compositeDisposable.add(databaseManager.getPersonDao().findPerson(personId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Person>() {
                    @Override
                    public void accept(Person person) throws Exception {

                    }
                }));
               Completable.fromAction(new Action() {
                   @Override
                   public void run() throws Exception {
                       Person person=databaseManager.getPersonDao().findSpecificPerson(personId);
                       databaseManager.getPersonDao().deletePerson(person);
                   }
               }).subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe();

    }


    @Override
    public void start() {
    }


    @Override
    public void stop() {
    }
}
