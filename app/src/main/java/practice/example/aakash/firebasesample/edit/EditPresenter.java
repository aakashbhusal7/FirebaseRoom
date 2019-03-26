package practice.example.aakash.firebasesample.edit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import practice.example.aakash.firebasesample.data.DatabaseManager;
import practice.example.aakash.firebasesample.data.entity.Person;
import practice.example.aakash.firebasesample.utils.Constants;
import practice.example.aakash.firebasesample.utils.ValidUtil;

public class EditPresenter implements EditContract.Presenter {
    private EditContract.View view;
    //private PersonRepositoryImpl personRepositoryImpl;
     private  DatabaseManager databaseManager;
     private CompositeDisposable compositeDisposable;
    @Inject
    public EditPresenter(EditContract.View view, DatabaseManager databaseManager){
        this.view=view;
        this.view.setPresenter(this);
        this.databaseManager=databaseManager;
    }
    @Override
    public void save(final Person person) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                databaseManager.getPersonDao().insertPerson(person);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
//       this.databaseManager.getPersonDao().insertPerson(person);
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
    public void getPersonAndPopulate(final long id) {
        compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(databaseManager.getPersonDao().findPerson(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Person>() {
            @Override
            public void accept(Person person) throws Exception {
                view.populate(person);
            }
        }));

    }


    @Override
    public void update(final Person person) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                int id= databaseManager.getPersonDao().updatePerson(person);
                view.close();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        //this.databaseManager.getPersonDao().updatePerson(person);
        //view.close();
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }
}
