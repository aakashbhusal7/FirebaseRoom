package practice.example.aakash.firebasesample.edit;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import practice.example.aakash.firebasesample.R;
import practice.example.aakash.firebasesample.data.AppDatabase;
import practice.example.aakash.firebasesample.data.DatabaseManager;
import practice.example.aakash.firebasesample.data.entity.Person;
import practice.example.aakash.firebasesample.databinding.ActivityEditBinding;
import practice.example.aakash.firebasesample.listedit.ListActivity;
import practice.example.aakash.firebasesample.utils.Constants;

public class EditActivity extends AppCompatActivity implements EditContract.View {

    @Inject
    EditContract.Presenter presenter;

    ActivityEditBinding activityEditBinding;
    private Person person;
    private boolean mEditMode = false;

    @Inject
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        activityEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        person = new Person();
        checkMode();
//        AppDatabase db= AppDatabase.getAppDatabase(getApplication());
        new EditPresenter(this, databaseManager);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mEditMode) {
            presenter.getPersonAndPopulate(person.id);
        }
    }
    private void checkMode() {
        if (getIntent().getExtras() != null) {
            person.id = getIntent().getLongExtra(Constants.PERSON_ID, 0);
            mEditMode = true;
        }
    }

    private void initViews() {
        activityEditBinding.fab.setImageResource(mEditMode ? R.drawable.ic_refresh : R.drawable.ic_done);
        activityEditBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.name = activityEditBinding.nameEditText.getText().toString();
                person.address = activityEditBinding.addressEditText.getText().toString();
                person.email = activityEditBinding.emailEditText.getText().toString();
                person.phone = activityEditBinding.phoneEditText.getText().toString();
                boolean valid = presenter.validate(person);
                if (!valid) return;
                if (mEditMode) {
                    presenter.update(person);
                } else {
                    presenter.save(person);
                }
            }
        });
    }

    @Override
    public void setPresenter(EditContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showErrorMessage(int field) {
        if (field == Constants.FIELD_NAME) {
            activityEditBinding.nameTextInputLayout.setError("Invalid Name");
        } else if (field == Constants.FIELD_EMAIL) {
            activityEditBinding.emailTextInputLayout.setError(getString(R.string.invalid_email));
        } else if (field == Constants.FIELD_PHONE) {
            activityEditBinding.phoneTextInputLayout.setError(getString(R.string.invalid_phone));
        } else if (field == Constants.FIELD_ADDRESS) {
            activityEditBinding.addressTextInputLayout.setError(getString(R.string.invalid_address));
        }
    }

    @Override
    public void clearPreErrors() {
        activityEditBinding.nameTextInputLayout.setErrorEnabled(false);
        activityEditBinding.addressTextInputLayout.setErrorEnabled(false);
        activityEditBinding.phoneTextInputLayout.setErrorEnabled(false);
        activityEditBinding.emailTextInputLayout.setErrorEnabled(false);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void populate(Person person) {
        this.person = person;
        activityEditBinding.nameEditText.setText(person.name);
        activityEditBinding.phoneEditText.setText(person.phone);
        activityEditBinding.emailEditText.setText(person.email);
        activityEditBinding.addressEditText.setText(person.address);
    }

}
