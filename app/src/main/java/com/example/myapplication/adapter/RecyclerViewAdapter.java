package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activities.HomeActivity;
import com.example.myapplication.modal.Student;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Student> studentListRv;
    private ItemClicked mListener;

    public RecyclerViewAdapter(ArrayList<Student> list, final ItemClicked itemClicked) {
        studentListRv = list;
        this.mListener = itemClicked;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rvLayout;
        TextView tvRvName, tvRvClass;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvLayout = itemView.findViewById(R.id.ll_rv);
            tvRvName = itemView.findViewById(R.id.tv_rv_student_name);
            tvRvClass = itemView.findViewById(R.id.tv_rv_student_class);
        }

    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.ViewHolder holder, final int i) {

        holder.itemView.setTag(studentListRv.get(i));
        holder.tvRvName.setText(String.format("Student Name: %s", studentListRv.get(i).getStudentName()));
        holder.tvRvClass.setText(String.format("Student Class: %s", String.valueOf(studentListRv.get(i).getStudentRollNo())));
        holder.rvLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(holder.getAdapterPosition());
            }
        });
    }
    @Override
    public int getItemCount() {
        return studentListRv.size();
    }

    public interface ItemClicked {
        void onItemClicked(int position);
    }
}