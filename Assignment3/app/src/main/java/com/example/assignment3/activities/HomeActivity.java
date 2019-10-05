package com.example.assignment3.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.R;
import com.example.assignment3.adapter.AdapterRv;
import com.example.assignment3.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class HomeActivity extends AppCompatActivity implements AdapterRv.ItemClicked, View.OnClickListener {
    private static final int ADD_REQUEST_CODE = 2;
    int a;
    private TextView tvEmptyList,tvTitleToolbar;
    RecyclerView recyclerView;
    public RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Student> studentListRv = new ArrayList<Student>();
    Button btnAddStudent;
    ImageButton ibSort,ibLayout;
    private boolean mIsListShowing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initialising the ui components.
        init();

    }


    // Ui Initialiser.
    public void init(){
        btnAddStudent = findViewById(R.id.btn_add_student);
        recyclerView = findViewById(R.id.rv_studentList);
        tvEmptyList = findViewById(R.id.tv_empty_list);
        tvTitleToolbar=findViewById(R.id.tv_toolbar_title);
        ibLayout=findViewById(R.id.ib_toolbar_layout);
        ibSort=findViewById(R.id.ib_toolbar_sort);
        tvEmptyList.setText(R.string.emptylist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new AdapterRv(this, studentListRv);
        recyclerView.setAdapter(myAdapter);
        btnAddStudent.setOnClickListener(this);
        ibSort.setOnClickListener(this);
        ibLayout.setOnClickListener(this);
        ibSort.setVisibility(View.VISIBLE);
        ibLayout.setVisibility(View.VISIBLE);
        tvTitleToolbar.setText(R.string.home);
    }
    public void updateList() {
        myAdapter.notifyDataSetChanged();
        if (studentListRv.size() == 0) {
            recyclerView.setVisibility(View.INVISIBLE);
            tvEmptyList.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvEmptyList.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                if(requestCode==ADD_REQUEST_CODE) {
                    Student st = data.getParcelableExtra("Object");
                    studentListRv.add(st);
                    myAdapter.notifyDataSetChanged();
                    updateList();
                }
            }

    }


    @Override
    public void onItemClicked(final int position) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        Button btnView = dialog.findViewById(R.id.btn_view);
        Button btnEdit = dialog.findViewById(R.id.btn_edit);
        Button btnDelete = dialog.findViewById(R.id.btn_delete);
        final Student st=studentListRv.get(position);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=2;
                Intent i=new Intent(HomeActivity.this,AddStudentActivity.class);
                i.putExtra("Code",a);
                i.putExtra("Object",st);
                startActivity(i);
                dialog.dismiss();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=3;
                Intent i=new Intent(HomeActivity.this,AddStudentActivity.class);
                i.putExtra("Code",a);
                i.putExtra("Object",st);

                startActivity(i);
                dialog.dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentListRv.remove(position);
                myAdapter.notifyDataSetChanged();
                updateList();
                Toast.makeText(HomeActivity.this, "Student Removed", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_student:
                Intent intent = new Intent(HomeActivity.this, AddStudentActivity.class);
                startActivityForResult(intent, ADD_REQUEST_CODE);
                break;
            case R.id.ib_toolbar_sort:
                openPopup();
            case R.id.ib_toolbar_layout:
                if(mIsListShowing){
                    Toast.makeText(this, "Showing List View", Toast.LENGTH_SHORT).show();
                    ibLayout.setBackground(getResources().getDrawable(R.drawable.ic_grid));
                    layoutManager=new LinearLayoutManager(HomeActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    myAdapter.notifyDataSetChanged();
                    mIsListShowing=false;
                }
                else{
                    Toast.makeText(this, "Showing Grid View", Toast.LENGTH_SHORT).show();
                    ibLayout.setBackground(getResources().getDrawable(R.drawable.ic_list));
                    layoutManager=new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    myAdapter.notifyDataSetChanged();
                    mIsListShowing=true;
                }
                default:
                    break;
        }
    }

    //Open Popup Function.
    public void openPopup(){
        PopupMenu popupMenu=new PopupMenu(HomeActivity.this,ibSort);
        popupMenu.getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.sort_name:
                        Toast.makeText(HomeActivity.this, "Sorted By Name", Toast.LENGTH_SHORT).show();
                        Collections.sort(studentListRv, new Comparator<Student>() {
                            @Override
                            public int compare(Student o1, Student o2) {
                                return o1.getStudentName().compareToIgnoreCase(o2.getStudentName());
                            }
                        });
                        myAdapter.notifyDataSetChanged();
                        return true;
                    case R.id.sort_roll_no:
                        Toast.makeText(HomeActivity.this, "Sorted By Roll No", Toast.LENGTH_SHORT).show();
                        Collections.sort(studentListRv, new Comparator<Student>() {
                            @Override
                            public int compare(Student o1, Student o2) {
                                String rollNo1=String.valueOf(o1.getStudentRollNo());
                                String rollNo2=String.valueOf(o2.getStudentRollNo());
                                return rollNo1.compareToIgnoreCase(rollNo2);
                            }
                        });
                        myAdapter.notifyDataSetChanged();
                        return true;
                        default:
                            return false;
                }
            }
        });
        popupMenu.show();
    }
}
