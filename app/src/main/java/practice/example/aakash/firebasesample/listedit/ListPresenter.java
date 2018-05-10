package practice.example.aakash.firebasesample.listedit;


import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import practice.example.aakash.firebasesample.data.DatabaseManager;
import practice.example.aakash.firebasesample.data.dao.PersonDao;
import practice.example.aakash.firebasesample.data.entity.Person;

public class ListPresenter implements ListContract.Presenter {
    private ListContract.View view;
    DatabaseManager databaseManager;
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
        databaseManager.getPersonDao().findAllPeople().observeForever(new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> personList) {
                view.setPersons(personList);
                if(personList==null || personList.size()<1){
                    view.showEmptyMessage();
                }
            }
        });
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
