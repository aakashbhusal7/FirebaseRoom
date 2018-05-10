package practice.example.aakash.firebasesample.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import practice.example.aakash.firebasesample.data.entity.Person;


@Dao
public interface PersonDao {

    @Query("SELECT * FROM people ORDER BY name ASC" )
    LiveData<List<Person>> findAllPeople();

    @Query("SELECT * FROM people")
    List<Person> getAllPeople();

    @Query("SELECT * FROM people WHERE id=:id")
    Person findPerson(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPerson(Person person);

    @Update
    int updatePerson(Person person);

    @Update
    void updatePerson(List<Person> personList);

    @Delete
    void deletePerson(Person person);

    @Query("DELETE FROM people")
    void deleteAll();
}
