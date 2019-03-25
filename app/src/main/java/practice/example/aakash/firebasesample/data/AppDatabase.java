package practice.example.aakash.firebasesample.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import practice.example.aakash.firebasesample.data.dao.PersonDao;
import practice.example.aakash.firebasesample.data.entity.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;
    public abstract PersonDao getPersonDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.
                    databaseBuilder(context.getApplicationContext(), AppDatabase.class,"persons")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;

    }

}


