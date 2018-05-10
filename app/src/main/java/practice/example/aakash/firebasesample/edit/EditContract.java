package practice.example.aakash.firebasesample.edit;

import practice.example.aakash.firebasesample.BasePresenter;
import practice.example.aakash.firebasesample.BaseView;
import practice.example.aakash.firebasesample.data.entity.Person;

public interface EditContract {
    interface Presenter extends BasePresenter{
        void save(Person person);
        boolean validate(Person person);
        void getPersonAndPopulate(long id);
        void update(Person person);
    }
    interface View extends BaseView<EditContract.Presenter>{
        void showErrorMessage(int field);
        void clearPreErrors();
        void close();
        void populate(Person person);
    }
}
