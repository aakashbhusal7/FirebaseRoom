package practice.example.aakash.firebasesample.data;

import android.content.Context;

import javax.inject.Inject;

import practice.example.aakash.firebasesample.data.dao.PersonDao;

public class DatabaseManager {
     Context context;
     AppDatabase appDatabase;

    @Inject
    public DatabaseManager(Context context){
       this.context=context;
       appDatabase=AppDatabase.getAppDatabase(context);
    }

    public PersonDao getPersonDao(){
        return appDatabase.getPersonDao();
    }
}
