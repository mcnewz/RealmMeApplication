package com.manit.krungsri.realmmeapplication.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import com.manit.krungsri.realmmeapplication.R;
import com.manit.krungsri.realmmeapplication.adapter.StudentAdapter;
import com.manit.krungsri.realmmeapplication.model.Student;
import com.manit.krungsri.realmmeapplication.realm.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class RecycleViewActivity extends AppCompatActivity {

    private Student student;


    private Realm realm;
    private LayoutInflater inflater;
    private FloatingActionButton fab;
    private RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        recycler = findViewById(R.id.recycler);
        StudentAdapter adapter  = new StudentAdapter();

        List<Student> listStudent = showDataRealm();
        adapter.setStudents(listStudent);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Student> showDataRealm() {
        realm = Realm.getDefaultInstance(); // opens "myrealm.realm"

        RealmHelper helper = new RealmHelper(realm);
        List<Student> realmData = helper.retrieve();

        return realmData;
    }

    private List<Student> showDataStudent() {
        List<Student> data = new ArrayList<Student>();

        student = new Student();
        student.setStudentId(1);
        student.setStudentName("hello");
        student.setStudentScore(1011);

        Log.d("student",student.getStudentName());
        data.add(student);
        return data;
    }
}
