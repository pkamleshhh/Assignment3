package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.modal.Student;

import static com.example.myapplication.fragments.HomeFragment.INTENT_KEY;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etStudentName, etStudentClass, etStudentRollNo;
    private Button btnSubmit;
    Student st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_student);
        init();
        st=getIntent().getParcelableExtra(INTENT_KEY);
        setData();
    }
    public void init(){
        etStudentName = findViewById(R.id.et_student_name_add_student);
        etStudentClass = findViewById(R.id.et_class_add_student);
        etStudentRollNo = findViewById(R.id.et_rollno_add_student);
        btnSubmit = findViewById(R.id.btn_submit_add_student);
        btnSubmit.setText(getString(R.string.back_btn_view));
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit_add_student:
                finish();
        }
    }
    public void setData(){
        etStudentName.setText(st.getStudentName());
        etStudentClass.setText(String.valueOf(st.getStudentClass()));
        etStudentRollNo.setText(String.valueOf(st.getStudentRollNo()));
        etStudentName.setFocusable(false);
        etStudentClass.setFocusable(false);
        etStudentRollNo.setFocusable(false);

    }
}
