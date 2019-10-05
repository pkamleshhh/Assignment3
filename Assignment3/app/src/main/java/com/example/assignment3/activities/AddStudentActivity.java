package com.example.assignment3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.assignment3.R;
import com.example.assignment3.model.Student;


public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {
   private EditText etStudentName, etStudentClass, etStudentRollNo;
    private  Button btnSubmit;
    private TextView tvTitle;
    private ImageButton ibSort, ibLayout;
    private int KEY_SUBMIT_BUTTON = 212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        //Initialising the ui components.
        init();

        int code = getIntent().getIntExtra("Code", 0);
        final Student st = getIntent().getParcelableExtra("Object");
        switch (code) {
            case 2:
                KEY_SUBMIT_BUTTON = code;
                tvTitle.setText(R.string.tb_view_student);
                etStudentName.setText(st.getStudentName());
                etStudentClass.setText(String.valueOf(st.getStudentClass()));
                etStudentRollNo.setText(String.valueOf(st.getStudentRollNo()));
                etStudentName.setBackground(getResources().getDrawable(R.drawable.et_view));
                etStudentClass.setBackground(getResources().getDrawable(R.drawable.et_view));
                etStudentRollNo.setBackground(getResources().getDrawable(R.drawable.et_view));
                etStudentName.setInputType(InputType.TYPE_NULL);
                etStudentClass.setInputType(InputType.TYPE_NULL);
                etStudentRollNo.setInputType(InputType.TYPE_NULL);
                btnSubmit.setText(R.string.back_btn_view);
                break;
            case 3:
                KEY_SUBMIT_BUTTON = code;
                tvTitle.setText(R.string.title_tb_edit);
                etStudentName.requestFocus();
                etStudentName.setText(st.getStudentName());
                etStudentClass.setText(String.valueOf(st.getStudentClass()));
                etStudentRollNo.setText(String.valueOf(st.getStudentRollNo()));
                etStudentRollNo.setInputType(InputType.TYPE_NULL);
                btnSubmit.setText(R.string.btn_set_change);
                break;
            default:
                tvTitle.setText(R.string.add_student);
                break;
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (KEY_SUBMIT_BUTTON){
                    case 2:
                        finish();
                        break;
                    case 3:

                        String studentName = etStudentName.getText().toString();
                        int studentClass = Integer.parseInt(etStudentClass.getText().toString());
                        int studentRollNo = st.getStudentRollNo();
                       Student st=new Student(studentName,studentClass,studentRollNo);
                        Intent intent = new Intent(AddStudentActivity.this, HomeActivity.class);
                        intent.putExtra("Object", st);
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                        default:
                            onSubmit();
                            break;
                }
            }
        });
    }

    //Onclick switch case.
    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    //Ui initialiser function.
    public void init() {
        tvTitle = findViewById(R.id.tv_toolbar_title);
        ibSort = findViewById(R.id.ib_toolbar_sort);
        ibLayout = findViewById(R.id.ib_toolbar_layout);
        etStudentName = findViewById(R.id.et_student_name_add_student);
        etStudentClass = findViewById(R.id.et_class_add_student);
        etStudentRollNo = findViewById(R.id.et_rollno_add_student);
        btnSubmit = findViewById(R.id.btn_submit_add_student);
        ibLayout.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        ibLayout.setVisibility(View.INVISIBLE);
        ibSort.setVisibility(View.INVISIBLE);
    }


    //Onsubmit function.
    public void onSubmit() {
        String studentName = etStudentName.getText().toString();
        int studentClass = Integer.parseInt(etStudentClass.getText().toString());
        int studentRollNo = Integer.parseInt(etStudentRollNo.getText().toString());
       if (isValidate()){

            Student student = new Student(studentName, studentClass, studentRollNo);
            Intent intent = new Intent(AddStudentActivity.this, HomeActivity.class);
            intent.putExtra("Object", student);
            setResult(RESULT_OK, intent);
            finish();
        }

    }
    private boolean isValidate(){
        String etAddName = etStudentName.getText().toString().trim();
        String etAddClass = etStudentClass.getText().toString().trim();
        String etAddRollNo = etStudentRollNo.getText().toString().trim();
        String namePattern = ("^[a-zA-Z\\s]*$");
        if (etAddName.isEmpty()) {
            etStudentName.setError(getString(R.string.empty_message));
            return false;
        } else if (etAddName.equals(namePattern)){
            etStudentName.setError(getString(R.string.valid_name_error_msg));
            return false;
        }
        else if (etAddClass.isEmpty()) {
            etStudentClass.setError(getString(R.string.empty_message));
            return false;
        }
        else if (etAddRollNo.isEmpty()) {
            etStudentRollNo.setError(getString(R.string.empty_message));
            return false;
        }else if (Integer.parseInt(etAddClass)>12){
            etStudentClass.setError(getString(R.string.lessthan12_error_msg));
            return false;
        }else if (Integer.parseInt(etAddClass)<0){
            etStudentClass.setError(getString(R.string.class_error_msg));
            return false;
        }else if(Integer.parseInt(etAddRollNo)<0){
            etStudentRollNo.setError(getString(R.string.proper_roll_no));
            return false;
        }

        else {
            return true;
        }

    }

}
