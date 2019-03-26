package practice.example.aakash.firebasesample.listedit;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import practice.example.aakash.firebasesample.R;
import practice.example.aakash.firebasesample.data.DatabaseManager;
import practice.example.aakash.firebasesample.data.entity.Person;
import practice.example.aakash.firebasesample.databinding.ActivityListBinding;
import practice.example.aakash.firebasesample.edit.EditActivity;
import practice.example.aakash.firebasesample.utils.Constants;

public class ListActivity extends AppCompatActivity implements ListContract.View, ListContract.OnItemClickListener,
ListContract.DeleteListener{

    @Inject
    ListContract.Presenter listPresenter;
    private PeopleAdapter peopleAdapter;
    ActivityListBinding activityListBinding;

    @Inject
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        activityListBinding= DataBindingUtil.setContentView(this, R.layout.activity_list);
        activityListBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPresenter.addNewPerson();
            }
        });
        activityListBinding.list.setHasFixedSize(true);
        activityListBinding.list.setLayoutManager(new LinearLayoutManager(this));
        peopleAdapter=new PeopleAdapter(this);
        activityListBinding.list.setAdapter(peopleAdapter);
//        AppDatabase db= AppDatabase.getAppDatabase(getApplication());
        new ListPresenter(this,databaseManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        listPresenter.populatePeople();
    }

    @Override
    public void showAddPerson() {
        Intent intent= new Intent(this, EditActivity.class);
        startActivityForResult(intent,0);
    }

    @Override
    public void setPersons(List<Person> persons) {
        activityListBinding.emptyTextView.setVisibility(View.GONE);
        peopleAdapter.setValues(persons);
    }

    @Override
    public void showEditScreen(long id) {
        Intent intent=new Intent(this,EditActivity.class);
        intent.putExtra(Constants.PERSON_ID,id);
        startActivity(intent);
    }

    @Override
    public void showDeleteConfirmDialog(Person person) {
        DeleteFragment fragment = new DeleteFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.PERSON_ID, person.id);
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), "confirmDialog");
    }

    @Override
    public void showEmptyMessage() {
        activityListBinding.emptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void clickItem(Person person) {
        listPresenter.openEditScreen(person);
    }

    @Override
    public void clickLongItem(Person person) {
        listPresenter.openConfirmDeleteDialog(person);
    }

    @Override
    public void setConfirm(boolean confirm, long personId) {
        if(confirm){
            listPresenter.delete(personId);
        }
    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {
       this.listPresenter=presenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        listPresenter.populatePeople();

    }
}
