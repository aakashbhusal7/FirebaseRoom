//package practice.example.aakash.firebasesample.data.repository;
//
//import android.arch.lifecycle.LiveData;
//
//import java.util.List;
//
//import io.reactivex.Flowable;
//import practice.example.aakash.firebasesample.data.dao.PersonDao;
//import practice.example.aakash.firebasesample.data.entity.Person;
//
//public interface PersonRepository {
//
//    LiveData<List<Person>> findAllPeople();
//    Flowable<List<Person>>getAllPeople();
//    Person findPerson(long id);
//    long insertPerson(Person person);
//    int updatePerson(Person person);
//    void updatePerson(List<Person>personList);
//    void deletePerson(Person person);
//    void deleteAll();
//}
