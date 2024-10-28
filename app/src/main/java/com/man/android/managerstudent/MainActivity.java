package com.man.android.managerstudent;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.man.android.managerstudent.Adapter.CustomAdapter;
import com.man.android.managerstudent.Data.DBManager;
import com.man.android.managerstudent.Model.Student;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText editId;
    private EditText editName;
    private EditText editCmnd;
    private Spinner spinner;
    private EditText editNoted;
    private Button btnSave;
    private Button btnShow;
    private boolean checkID=false;
    private boolean checkName=false;
    private boolean checkCMND=false;

    private DBManager dbManager;


    private String nameshow;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);

        initWidget();
        String[] Array = {"Đại Học", "Cao Đẳng", "Trung Cấp"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.my_spinner_style,R.id.textView1, Array);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        editId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() ==0) {
                    editId.setError("Bạn bắt buộc phải nhập Mã sinh viên");
                } else {
                    editId.setError(null);
                    checkID=true;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() ==0) {
                    editId.setError("Bạn bắt buộc phải nhập Mã sinh viên");
                } else {
                    editId.setError(null);
                    checkID=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() ==0) {
                    editId.setError("Bạn bắt buộc phải nhập Mã sinh viên");
                } else {
                    editId.setError(null);
                    checkID=true;
                }
            }
        });
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <3) {
                    editName.setError("Phải có ít nhất 3 kí tự");
                } else {
                    editName.setError(null);
                    checkName=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (s.length() ==0) {
//                    editName.setError("Bạn bắt buộc phải nhập Họ và Tên");
//                } else {
//                    editName.setError(null);
//                    checkName=true;
//                }
            }
        });


        editCmnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss =s.toString();

                try {
                    int check=Integer.parseInt(ss);
                    Log.i("",check+" is a number");
                    if (s.length() ==9) {
                        checkCMND=true;
                        editCmnd.setError(null);
                    } else {

                        editCmnd.setError("Số CMND có 9 số.");
                    }
                } catch (NumberFormatException e) {
                    Log.i("",ss+" is not a number");
                    editCmnd.setError("Bạn chỉ được nhập số");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkID&&checkName&&checkCMND){
                    Student student = createStudent();
                    if (student!=null){
                        // kiem tra de tranh bi loi, mac du khong xay ra
                        dbManager.addStudent(student);
                    }
                    Toast.makeText(MainActivity.this,
                            "Đã thêm "+ nameshow, Toast.LENGTH_SHORT)
                            .show();
                }else{
                    Toast.makeText(MainActivity.this,
                            "Nhập thông tin không hợp lệ.", Toast.LENGTH_SHORT)
                            .show();
                    btnSave.setError("Nhập thông tin không hợp lệ.");
                }


            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListStudent.class);
                startActivity(intent);

            }
        });
    }


    private Student createStudent(){
        // 3 kieu text sang String pho bien hien nay
        String id = editId.getText().toString(); // cach 1
        String name = editName.getText().toString();
        String cmnd = String.valueOf(editCmnd.getText()); // cach 2
        String level = spinner.getSelectedItem().toString() + ""; // cach 3
        String noted = editNoted.getText().toString();
        nameshow=editName.getText().toString();
        // dat ten bien model se co chu m o truoc, con o day khong co

        Student student = new Student(id, name, cmnd,level,noted);
        return student;
    }

    private void initWidget(){
        editId = (EditText) findViewById(R.id.edit_id);
        editName = (EditText) findViewById(R.id.edit_name);
        editCmnd = (EditText) findViewById(R.id.edit_cmnd);

        editNoted = (EditText) findViewById(R.id.edit_note);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnShow = (Button) findViewById(R.id.btn_show);

    }


}
