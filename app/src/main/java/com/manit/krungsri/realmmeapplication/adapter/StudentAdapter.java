package com.manit.krungsri.realmmeapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.manit.krungsri.realmmeapplication.R;
import com.manit.krungsri.realmmeapplication.holder.StudentViewHolder;
import com.manit.krungsri.realmmeapplication.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MCNEWZ on 03-Dec-17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {


    private List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_students, parent, false);

        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        holder.setTvStudnt(students.get(position).getStudentName());
        holder.setTvDes1(students.get(position).getStudentId() + "");
        holder.setTvDes2(students.get(position).getStudentScore() + "");


        holder.setClickListener(new StudentViewHolder.ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Toast.makeText(view.getContext(), students.get(position).getStudentName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
