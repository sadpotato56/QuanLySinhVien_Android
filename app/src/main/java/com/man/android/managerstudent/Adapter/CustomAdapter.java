package com.man.android.managerstudent.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.man.android.managerstudent.Data.DBManager;
import com.man.android.managerstudent.ListStudent;
import com.man.android.managerstudent.Model.Student;
import com.man.android.managerstudent.R;

import java.util.List;




public class CustomAdapter extends ArrayAdapter<Student> {

    private Context context;
    private int resource;
    private List<Student> listStudent;
    private FloatingActionButton fabRemove;
    private   DBManager dbManager;
    private String getMaSV;
    private int pos;
    private ListStudent listStudentAC;




    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listStudent = objects;
        dbManager = new DBManager(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_student,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvCmnd = (TextView) convertView.findViewById(R.id.tv_cmnd);
            viewHolder.tvLevel = (TextView) convertView.findViewById(R.id.tv_level);
            viewHolder.tvNoted = (TextView) convertView.findViewById(R.id.tv_noted);

            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        Student student = listStudent.get(position);
        viewHolder.tvId.setText(String.valueOf(student.getmID()));
        viewHolder.tvName.setText(student.getmName());
        viewHolder.tvCmnd.setText(student.getmCMND());
        viewHolder.tvLevel.setText(student.getmLevel());
        viewHolder.tvNoted.setText(student.getmNoted());

        fabRemove = (FloatingActionButton) convertView.findViewById(R.id.fb_remove);


        fabRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = listStudent.get(position);
               getMaSV = student.getmID();
                dbManager.removeId(getMaSV);
                Toast.makeText(getContext(),
                        "Đã xóa " + getMaSV, Toast.LENGTH_SHORT)
                        .show();

            }
        });

        return convertView;
    }

    // Tranh truong hop item list view qua nhieu se bi lag nen dung cai duoi
    // khai bao nhung view se su dung o item nay
    public class ViewHolder{
        private TextView tvId;
        private TextView tvName;
        private TextView tvCmnd;
        private TextView tvLevel;
        private TextView tvNoted;
    }


}
