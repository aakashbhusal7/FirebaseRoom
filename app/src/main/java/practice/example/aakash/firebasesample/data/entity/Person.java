package practice.example.aakash.firebasesample.data.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "people")
public class Person{

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String phone;
    public String address;
    public String email;

    @Ignore
    public Person(){
        this.name="";
        this.phone="";
        this.address="";
        this.email="";
    }
    public Person(String name, String phone, String address, String email){
        this.name=name;
        this.phone=phone;
        this.address=address;
        this.email=email;
    }
}

