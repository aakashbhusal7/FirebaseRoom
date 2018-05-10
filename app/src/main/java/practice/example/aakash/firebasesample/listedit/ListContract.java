package practice.example.aakash.firebasesample.listedit;

import java.util.List;

import practice.example.aakash.firebasesample.BasePresenter;
import practice.example.aakash.firebasesample.BaseView;
import practice.example.aakash.firebasesample.data.entity.Person;

public interface ListContract {
    interface Presenter extends BasePresenter{
        void addNewPerson();

        void result(int requestCode, int resultCode);

        void populatePeople();

        void openEditScreen(Person person);

        void openConfirmDeleteDialog(Person person);

        void delete(long personId);
    }

    interface View extends BaseView<ListContract.Presenter>{
        void showAddPerson();

        void setPersons(List<Person> persons);

        void showEditScreen(long id);

        void showDeleteConfirmDialog(Person person);

        void showEmptyMessage();
    }
    interface OnItemClickListener {

        void clickItem(Person person);

        void clickLongItem(Person person);
    }

    interface DeleteListener {
        void setConfirm(boolean confirm, long personId);
    }

}
