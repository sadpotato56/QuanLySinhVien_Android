package com.man.android.managerstudent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.man.android.managerstudent.Adapter.CustomAdapter;
import com.man.android.managerstudent.Data.DBManager;
import com.man.android.managerstudent.Model.Student;

import java.util.List;

public class ListStudent extends AppCompatActivity {
    private   CustomAdapter customAdapter;
    private List<Student> studentList;
    private   ListView lvStudent;
    private Button btrefresh;

    private    DBManager dbManager;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        dbManager = new DBManager(this);
        lvStudent= (ListView) findViewById(R.id.lv_student);
        btrefresh = (Button) findViewById(R.id.btrefresh);

        btrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentList.clear();
                studentList.addAll(dbManager.getAllStudent());
                setAdapter();
            }
        });
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) lvStudent.getItemAtPosition(position);
                Toast.makeText(context,position,Toast.LENGTH_SHORT).show();


            }
        });

//        studentList.addAll(dbManager.getAllStudent());
//
        studentList = dbManager.getAllStudent();

        setAdapter();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
       // studentList.clear(); // du lieu la 0 khong phai null
    }

    public void setAdapter(){
        if(customAdapter==null){
            customAdapter= new CustomAdapter(this,R.layout.item_list_student,studentList);
            lvStudent.setAdapter(customAdapter);
        } else{
            customAdapter.notifyDataSetChanged();
            lvStudent.setSelection(customAdapter.getCount()-1);
        }

    }

}
