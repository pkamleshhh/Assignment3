package com.example.myapplication.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activities.HomeActivity;
import com.example.myapplication.modal.Student;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddStudentFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private EditText etStudentName, etStudentClass, etStudentRollNo;
    private int code=1;
    Button btnSubmit;
    View v;

    public AddStudentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Defining the variables
        v=inflater.inflate(R.layout.fragment_add_student, container, false);
        init();




        return v;
    }
    public void getData(int code){
        String studentName=etStudentName.getText().toString().trim();
        String stringStudentClass=etStudentClass.getText().toString().trim();
        String stringStudentRollNo=etStudentRollNo.getText().toString().trim();
        if(studentName.isEmpty()){
            etStudentName.setError(getString(R.string.empty_message));
        }
        else if(!studentName.matches("[a-zA-Z]+\\.?")){
            etStudentName.setError(getString(R.string.valid_name_error_msg));
        }
        else if(stringStudentClass.isEmpty()){
            etStudentClass.setError(getString(R.string.empty_message));
        }
        else if(!stringStudentClass.matches("^[0-9]*$")){
            etStudentClass.setError(getString(R.string.class_error_msg));
        }
        else if(Integer.parseInt(stringStudentClass)>12 || Integer.parseInt(stringStudentClass)<1){
            etStudentClass.setError(getString(R.string.lessthan12_error_msg));
        }
        else if(stringStudentRollNo.isEmpty()){
            etStudentRollNo.setError(getString(R.string.empty_message));
        }
        else if(!stringStudentRollNo.matches("^[0-9]*$")){
            etStudentRollNo.setError(getString(R.string.proper_roll_no));
        }
        else{
           if(code==1){
               Student st=new Student(studentName,Integer.parseInt(stringStudentClass),Integer.parseInt(stringStudentRollNo));
               Toast.makeText(mContext, getString(R.string.student_added), Toast.LENGTH_SHORT).show();
               ((HomeActivity)mContext).getInputData(st);
               ((HomeActivity)mContext).switchPage();
               clearData();
           }
           else{
               Student st=new Student(studentName,Integer.parseInt(stringStudentClass),Integer.parseInt(stringStudentRollNo));
               Toast.makeText(mContext, getString(R.string.student_added), Toast.LENGTH_SHORT).show();
               ((HomeActivity)mContext).fun(st);
           }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit_add_student:
                ((HomeActivity)mContext).xyz();
        }

    }
    public void init(){
        etStudentName = v.findViewById(R.id.et_student_name_add_student);
        etStudentClass = v.findViewById(R.id.et_class_add_student);
        etStudentRollNo = v.findViewById(R.id.et_rollno_add_student);
        btnSubmit = v.findViewById(R.id.btn_submit_add_student);
        btnSubmit.setOnClickListener(this);
    }
    public void clearData(){
        etStudentName.getText().clear();
        etStudentClass.getText().clear();
        etStudentRollNo.getText().clear();
    }
    public void setEditLayout(Student st){
        etStudentName.setText(st.getStudentName());
        etStudentClass.setText(String.valueOf(st.getStudentClass()));
        etStudentRollNo.setText(String.valueOf(st.getStudentRollNo()));

    }
}
