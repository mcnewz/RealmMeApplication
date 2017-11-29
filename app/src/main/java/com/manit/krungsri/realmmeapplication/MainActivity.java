package com.manit.krungsri.realmmeapplication;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.manit.krungsri.realmmeapplication.databinding.ActivityMainBinding;
import com.manit.krungsri.realmmeapplication.model.Person;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);

        super.onCreate(savedInstanceState);


        realm = Realm.getDefaultInstance(); // opens "myrealm.realm"
        // TODO: add validation for stuff.
//        save_into_database("Pang", 23);
        refresh_view();
    }

    private void refresh_view() {
        // read from DB show in textView_log

        RealmResults<Person> personRealmResults = realm.where(Person.class).findAll();

        StringBuilder output = new StringBuilder();
        for (Person person : personRealmResults) {
            output.append(person.toString());
        }

        Log.d("database", output.toString());
        binding.tvHello.setText(output.toString());
    }

    private void save_into_database(final String name, final int age) {

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }


}
