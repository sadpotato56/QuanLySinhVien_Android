package com.man.android.managerstudent.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.man.android.managerstudent.Model.Student;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DBManager extends SQLiteOpenHelper {

    private final String TAG = "DBManager";
    private static final String DATABASE_NAME="students_manager";
    private static final String TABLE_NAME="students";
    private static final String ID ="id";
    private static final String NAME="name";
    private static final String CMND="cmnd";
    private static final String LEVEL="level";
    private static final String NOTED="noted";
    private static int VERSON = 1;

    private Context context;


    private String SQLQuery="CREATE TABLE " + TABLE_NAME +" (" +
            ID + " TEXT primary key, "+
            NAME + " TEXT, "+
            CMND + " TEXT, "+
            LEVEL + " TEXT, "+
            NOTED + " TEXT)";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSON);
        this.context=context;
        Log.d(TAG,"DBManage: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        Log.d(TAG,"onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.d(TAG,"onUpdate: ");
    }


    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID,student.getmID());
        values.put(NAME,student.getmName());
        values.put(CMND,student.getmCMND());
        values.put(LEVEL,student.getmLevel());
        values.put(NOTED,student.getmNoted());
        db.insert(TABLE_NAME,null, values);
        db.close(); // mo phai dong cho an toan
        Log.d(TAG,"addStudent Successfuly");
    }
    public void removeId(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where ID='" + ID + "'");
        db.close();
        Log.d(TAG,"RemoveID Successfuly");
    }
    public List<Student> getAllStudent(){
        List<Student> listStudent = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null); //Cursor dung de hung ket qua tra ve
        if (cursor.moveToFirst()){ // phuong thuc nay false la danh sach khong co gi (rong)
            do{
                Student student = new Student();
                student.setmID(cursor.getString(0));
                student.setmName(cursor.getString(1));
                student.setmCMND(cursor.getString(2));
                student.setmLevel(cursor.getString(3));
                student.setmNoted(cursor.getString(4));
                listStudent.add(student);

            }while(cursor.moveToNext()); // sau ket qua co it nhat mot ket qua
        }
        db.close();
        return listStudent;
    }

}
