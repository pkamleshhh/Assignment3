package com.example.assignment3.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.R;
import com.example.assignment3.model.Student;

import java.util.ArrayList;

public class AdapterRv extends RecyclerView.Adapter<AdapterRv.ViewHolder> {

    private ArrayList<Student>studentListRv;
    ItemClicked activity;

    public AdapterRv(Context context,ArrayList<Student>list){
        studentListRv=list;
        activity=(ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvRvName,tvRvClass;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRvName=itemView.findViewById(R.id.tv_rv_student_name);
            tvRvClass=itemView.findViewById(R.id.tv_rv_student_class);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            activity.onItemClicked(studentListRv.indexOf((Student) v.getTag()));
        }
    }

    @NonNull
    @Override
    public AdapterRv.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRv.ViewHolder holder, int i) {

        holder.itemView.setTag(studentListRv.get(i));
        holder.tvRvName.setText(String.format("Student Name: %s", studentListRv.get(i).getStudentName()));
        holder.tvRvClass.setText(String.format("Student Class: %s", String.valueOf(studentListRv.get(i).getStudentRollNo())));
    }

    @Override
    public int getItemCount() {
        return studentListRv.size();
    }

    public interface ItemClicked{
        void onItemClicked(int position);
    }

}
