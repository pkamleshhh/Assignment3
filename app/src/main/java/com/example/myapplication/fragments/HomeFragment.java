package com.example.myapplication.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activities.HomeActivity;
import com.example.myapplication.activities.ViewActivity;
import com.example.myapplication.adapter.RecyclerViewAdapter;
import com.example.myapplication.adapter.ViewPagerAdapter;
import com.example.myapplication.modal.Student;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, RecyclerViewAdapter.ItemClicked {
    private TextView tvEmptyList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Student> studentListRv = new ArrayList<Student>();
    private Button btnAddStudent;
    private View v;
    private Context mContext;
    public static String INTENT_KEY="Key";
    private boolean mIsListShowing=true;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_home, container, false);
        studentListRv.add(new Student("Kamlesh", 3, 2));
        studentListRv.add(new Student("Pandey", 3, 2));

        //Initialising Ui Components.
        init();
        checkRvList();
        btnAddStudent.setOnClickListener(this);

        return v;
    }

    public void init() {
        btnAddStudent = v.findViewById(R.id.btn_add_student);
        recyclerView = v.findViewById(R.id.rv_studentList);
        tvEmptyList = v.findViewById(R.id.tv_empty_list);
        tvEmptyList.setText(R.string.emptylist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new RecyclerViewAdapter(studentListRv, this);
        recyclerView.setAdapter(myAdapter);
        btnAddStudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_student:
                ((HomeActivity) mContext).switchPage();
                break;
        }
    }


    public void checkRvList() {
        if (studentListRv.size() == 0) {
            recyclerView.setVisibility(View.INVISIBLE);
            tvEmptyList.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvEmptyList.setVisibility(View.INVISIBLE);
        }
    }

    public void addStudent(Student student) {
        studentListRv.add(student);
        myAdapter.notifyDataSetChanged();
    }
    public void updateList(Student student,int position){
        studentListRv.set(position,student);
        myAdapter.notifyDataSetChanged();
    }
    @Override
    public void onItemClicked(final int position) {
        final Dialog dialog = new Dialog(mContext);
        final Student st=studentListRv.get(position);
        dialog.setContentView(R.layout.dialog_layout);
        Button btnView = dialog.findViewById(R.id.btn_view);
        Button btnEdit = dialog.findViewById(R.id.btn_edit);
        Button btnDelete = dialog.findViewById(R.id.btn_delete);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ViewActivity.class);
                intent.putExtra(INTENT_KEY,st);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity)mContext).switchPage();
                ((HomeActivity)mContext).abc(st,position);
                dialog.dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentListRv.remove(position);
                Toast.makeText(mContext, R.string.student_removed, Toast.LENGTH_SHORT).show();
                myAdapter.notifyDataSetChanged();
                checkRvList();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void sortByName(){
        Collections.sort(studentListRv, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getStudentName().compareToIgnoreCase(o2.getStudentName());
            }
        });
        myAdapter.notifyDataSetChanged();
    }
    public void sortByRollNo(){
        Collections.sort(studentListRv, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                String rollNo1 = String.valueOf(o1.getStudentRollNo());
                String rollNo2 = String.valueOf(o2.getStudentRollNo());
                return rollNo1.compareToIgnoreCase(rollNo2);
            }
        });
        myAdapter.notifyDataSetChanged();
    }
    public void viewChanger(){
        if (mIsListShowing) {
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            myAdapter.notifyDataSetChanged();
            mIsListShowing = false;
        } else {
            layoutManager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            myAdapter.notifyDataSetChanged();
            mIsListShowing = true;
        }
    }

}
