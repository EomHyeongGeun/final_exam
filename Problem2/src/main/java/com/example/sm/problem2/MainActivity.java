package com.example.sm.problem2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyBaseAdapter adapter;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Employee> emp_list = new ArrayList<Employee>();

        emp_list.add(new Employee("Employee0",20,0));
        emp_list.add(new Employee("Employee1",21,10000));
        emp_list.add(new Employee("Employee2",22,20000));
        emp_list.add(new Employee("Employee3",23,30000));
        emp_list.add(new Employee("Employee4",24,40000));
        emp_list.add(new Employee("Employee5",25,50000));
        emp_list.add(new Employee("Employee6",26,60000));
        emp_list.add(new Employee("Employee7",27,70000));
        emp_list.add(new Employee("Employee8",28,80000));
        emp_list.add(new Employee("Employee9",29,90000));
        emp_list.add(new Employee("Employee10", 30, 100000));

        adapter = new MyBaseAdapter(this, emp_list); // emp_list = ArrayList
        listview = (ListView) findViewById(R.id.listView1) ;
        listview.setAdapter(adapter);
        listview.setOnItemClickListener((AdapterView.OnItemClickListener)adapter);
    }
    @Override
    public void onClick(View v){
        EditText edit_name = (EditText) findViewById(R.id.edit_name);
        EditText edit_age = (EditText) findViewById(R.id.edit_age);
        EditText edit_salary = (EditText) findViewById(R.id.edit_salary);

        Employee employee;

        switch (v.getId()){
            case R.id.btn_inc:
                // need something here
                break;

            case R.id.btn_dec:
                // need something here
                break;

            case R.id.btn_store:
                // need something here
                break;

            case R.id.btn_modify:
                // need something here
                break;

            case R.id.btn_delete:
                // need something here

                break;
        }
    }
}

interface Payment {
    void increase();
    void decrease();
}
