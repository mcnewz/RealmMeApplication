package com.manit.krungsri.realmmeapplication.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.manit.krungsri.realmmeapplication.R;
import com.manit.krungsri.realmmeapplication.databinding.ActivityMainBinding;
import com.manit.krungsri.realmmeapplication.model.Person;
import com.manit.krungsri.realmmeapplication.model.Student;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        super.onCreate(savedInstanceState);
            
        initinstance();
        realm = Realm.getDefaultInstance(); // opens "myrealm.realm"

//        SaveDataAsynchronous("Pang", 23);
//        selectPersonAll();

//        SaveDataSynchronous();
        binding.btnGenerate.setOnClickListener(buttonClick);
//        updateStudent();
        queryRealmTest();
    }

    private void initinstance() {
        binding.btnRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, RecycleViewActivity.class);
                startActivity(i);

            }
        });
    }

    private void queryRealmTest() {
        // Or alternatively do the same all at once (the "Fluent interface"):
//        RealmResults<Student> result2 = realm.where(Student.class)
//                .equalTo("studentName", "John")
//                .or()
//                .equalTo("studentName", "Peter")
//                .findAll();

        Log.d("student", "------------");

        RealmResults<Student> result22 = realm.where(Student.class)
                .equalTo("studentName", "Raj Koothrappali")
                .findAll();

        result22 = result22.sort("studentName");
        for(Student s : result22){
            Log.d("student", s.getStudentName() );

        }

        Log.d("student", "------------");
        //size
        Log.d("student", String.valueOf(result22.size()));

        Log.d("student", "------------");


        RealmResults<Student> result3 = realm.where(Student.class)
                .findAll();

        realm.beginTransaction();
        for (int i = 0; i<result3.size(); i++) {
            Log.d("student", String.valueOf(result3.get(i).getStudentName()));
        }
        realm.commitTransaction();



        Student result4 = realm.where(Student.class)
                .findFirst();

        Log.d("student", String.valueOf(result4));

    }

    private void generateStudent() {
        clearStudent();
        insertStudent();
    }

    public ArrayList<Student> getSampleStudentData(int size) {
        String name[] = {"Raj Koothrappali", "Penny Hofstadter", "Leonard Hopstater", "Sheldon cooper", "Howard Wolowitz"};

        ArrayList<Student> listStudent = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            Student student = new Student();
            student.setStudentId(i);
            student.setStudentName(name[i % name.length]);
            student.setStudentScore((int) (Math.random() * 100));
            listStudent.add(student);
        }
        return listStudent;
    }

    private void insertStudent() {
        final ArrayList<Student> listStudentGenerate = getSampleStudentData(10);

        realm.executeTransactionAsync(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        String text = "";
                        for (final Student s : listStudentGenerate) {
                            Student student = realm.createObject(Student.class, s.getStudentId());
//                            student.setStudentId(s.getStudentId());
                            student.setStudentName(s.getStudentName());
                            student.setStudentScore(s.getStudentScore());
//                            Log.d("student", "Add student id = " + s.getStudentId());
                            text += s.toString();
                        }

                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Generate student complete.", Toast.LENGTH_SHORT).show();
                        showStudent();
                    }

                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("student", error.getMessage());
                    }
                });
    }

    private void showStudent() {
        RealmResults<Student> listStudent = getAllStudent();

        Log.d("student", "SIZE = " + listStudent.size());

        String str = "";
        for (Student student : listStudent) {
            str += "\nID = " + student.getStudentId()+
                    " , " + student.getStudentName() +
                    " , (" + student.getStudentScore() + ")";
        }

        Log.d("student", str);

    }

    private void updateStudent(){
        final Student student = new Student();
        student.setStudentId(10);
        student.setStudentName("123456789");
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(student);
            }
        });
    }

    private void clearStudent() {
        realm.beginTransaction();
        realm.delete(Student.class);
        realm.commitTransaction();
    }


    private void insertStudentxxx() {
//        realm.beginTransaction();
//
//        Student student = realm.createObject(Student.class);
//        student.setStudentId(2);
//        student.setStudentName("manit");
//        student.setStudentScore(99);
//
//        realm.commitTransaction();
    }


    private RealmResults<Student> getAllStudent() {

        RealmResults<Student> result = realm.where(Student.class).findAll();
        return result;
    }

    private void SaveDataSynchronous() {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

            }
        });
    }

    private void selectPersonAll() {
        // read from DB show in textView_log
        StringBuilder output = new StringBuilder();
        RealmResults<Person> personRealmResults = realm.where(Person.class).findAll();

        for (Person person : personRealmResults) {
            output.append(person.toString());
        }

        Log.d("database", output.toString());
        binding.tvHello.setText(output.toString());

    }

    private void SaveDataAsynchronous(final String name, final int age) {
        //  realmasyncTask
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

                Person person = bgRealm.createObject(Person.class);
                person.setName(name);
                person.setAge(age);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.d("database", ">>> stored ok<<<");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.d("database", error.getMessage());
            }
        });
    }

    View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnGenerate:

                    Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                    generateStudent();

                    break;

                case R.id.btnRecyclerView:



                    break;
                default:
                    Toast.makeText(MainActivity.this, "Main", Toast.LENGTH_SHORT).show();
                    break;

            }

        }
    };


    @Override
    protected void onStop() {
        super.onStop();
        realm.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }


}
