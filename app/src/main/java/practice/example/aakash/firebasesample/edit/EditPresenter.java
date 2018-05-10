package practice.example.aakash.firebasesample.edit;

import com.facebook.stetho.common.Util;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import practice.example.aakash.firebasesample.data.DatabaseManager;
import practice.example.aakash.firebasesample.data.entity.Person;
import practice.example.aakash.firebasesample.utils.Constants;
import practice.example.aakash.firebasesample.utils.ValidUtil;

public class EditPresenter implements EditContract.Presenter {
    private EditContract.View view;
    //private PersonRepositoryImpl personRepositoryImpl;
     private  DatabaseManager databaseManager;
    @Inject
    public EditPresenter(EditContract.View view, DatabaseManager databaseManager){
        this.view=view;
        this.view.setPresenter(this);
        this.databaseManager=databaseManager;
    }
    @Override
    public void save(Person person) {
       this.databaseManager.getPersonDao().insertPerson(person);
        view.close();
    }

    @Override
    public boolean validate(Person person) {
       view.clearPreErrors();
       if(person.name.isEmpty() || !ValidUtil.isValidName(person.name)){
           view.showErrorMessage(Constants.FIELD_NAME);
           return false;
       }
       if (person.address.isEmpty()){
           view.showErrorMessage(Constants.FIELD_ADDRESS);
           return false;
       }
       if(person.phone.isEmpty() || !ValidUtil.isValidPhone(person.phone)){
           view.showErrorMessage(Constants.FIELD_PHONE);
           return false;
       }
       if(person.email.isEmpty() || !ValidUtil.isValidEmail(person.email)){
           view.showErrorMessage(Constants.FIELD_EMAIL);
           return false;
       }
       return true;
    }

    @Override
    public void getPersonAndPopulate(long id) {
        Person person=databaseManager.getPersonDao().findPerson(id);
        if (person!=null){
            view.populate(person);
        }
    }

    @Override
    public void update(Person person) {
        int id= this.databaseManager.getPersonDao().updatePerson(person);
        view.close();
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }
}
