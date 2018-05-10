//package practice.example.aakash.firebasesample.data.repository;
//
//import android.arch.lifecycle.LiveData;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import io.reactivex.Flowable;
//import practice.example.aakash.firebasesample.data.DatabaseManager;
//import practice.example.aakash.firebasesample.data.dao.PersonDao;
//import practice.example.aakash.firebasesample.data.entity.Person;
//
//public class PersonRepositoryImpl implements PersonRepository {
//
//      DatabaseManager databaseManager;
//
//      @Inject
//    public PersonRepositoryImpl(DatabaseManager databaseManager){
//        this.databaseManager=databaseManager;
//    }
//    public PersonDao personDao=databaseManager.getPersonDao();
//
//    @Override
//    public LiveData<List<Person>> findAllPeople() {
//        return personDao.findAllPeople();
//    }
//
//    @Override
//    public Flowable<List<Person>> getAllPeople() {
//        return personDao.getAllPeople();
//    }
//
//    @Override
//    public Person findPerson(long id) {
//        return personDao.findPerson(id);
//    }
//
//    @Override
//    public long insertPerson(Person person) {
//        return personDao.insertPerson(person);
//    }
//
//    @Override
//    public void updatePerson(List<Person> personList) {
//        personDao.updatePerson(personList);
//    }
//
//    @Override
//    public int updatePerson(Person person) {
//        return personDao.updatePerson(person);
//    }
//
//    @Override
//    public void deletePerson(Person person) {
//        personDao.deletePerson(person);
//    }
//
//    @Override
//    public void deleteAll() {
//        personDao.deleteAll();
//    }
//}
