package com.manit.krungsri.realmmeapplication.realm;

import android.util.Log;

import com.manit.krungsri.realmmeapplication.model.Student;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by MCNEWZ on 03-Dec-17.
 */

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //WRITE
    public void save(final Student student) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Student s = realm.copyToRealm(student);
            }
        });
    }

    //READ
    public List<Student> retrieve() {
        List<Student> listStudents = new ArrayList<>();
        RealmResults<Student> students = realm.where(Student.class).findAll();

        for (Student s : students) {
            Log.d("student", s.getStudentName());
            listStudents.add(s);
        }
        return listStudents;
    }
}
