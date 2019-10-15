package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPagerAdapter;
import com.example.myapplication.fragments.AddStudentFragment;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.modal.FragmentDetails;
import com.example.myapplication.modal.Student;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private int index;
    TabLayout tabLayout;
    public ViewPager viewPager;
    public ViewPagerAdapter viewPagerAdapter;
    private TextView tvTitleToolbar;
    private ImageButton ibSort, ibLayout;
    private ArrayList<FragmentDetails> mFragmentList = new ArrayList<>();
    private boolean mIsListShowing=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_home);
        init();
        setUpViewPager();

    }

    public void init() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        tvTitleToolbar = findViewById(R.id.tv_toolbar_title);
        ibSort = findViewById(R.id.ib_toolbar_sort);
        ibLayout = findViewById(R.id.ib_toolbar_layout);
        ibLayout.setOnClickListener(this);
        ibSort.setOnClickListener(this);
        ibLayout.setVisibility(View.VISIBLE);
        ibSort.setVisibility(View.VISIBLE);
        tvTitleToolbar.setText(getString(R.string.home));
    }

    public void setUpViewPager(){
        mFragmentList.add(new FragmentDetails(new HomeFragment(), getString(R.string.student_list)));
        mFragmentList.add(new FragmentDetails(new AddStudentFragment(), getString(R.string.add_or_update)));
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position== 1) {
                    ibLayout.setVisibility(View.INVISIBLE);
                    ibSort.setVisibility(View.INVISIBLE);
                } else {
                    ibLayout.setVisibility(View.VISIBLE);
                    ibSort.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
    public void switchPage() {
        if (viewPager.getCurrentItem() == 0) {
            viewPager.setCurrentItem(1, true);
        } else {
            viewPager.setCurrentItem(0, true);
        }
    }


    public void getInputData(Student student) {
      HomeFragment homeFragment=(HomeFragment) mFragmentList.get(0).getFragment();
      homeFragment.addStudent(student);
    }
    public void updateData(Student student,int position){
        HomeFragment homeFragment=(HomeFragment) mFragmentList.get(0).getFragment();
        homeFragment.updateList(student,position);
    }

    //Open Popup Function.
    public void openPopup() {
            PopupMenu popupMenu = new PopupMenu(HomeActivity.this, ibSort);
            popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.sort_name:
                            Toast.makeText(HomeActivity.this, getString(R.string.sorted_by_name), Toast.LENGTH_SHORT).show();
                            HomeFragment homeFragment1=(HomeFragment) mFragmentList.get(0).getFragment();
                            homeFragment1.sortByName();
                            return true;
                        case R.id.sort_roll_no:
                            Toast.makeText(HomeActivity.this, getString(R.string.sorted_by_roll), Toast.LENGTH_SHORT).show();
                            HomeFragment homeFragment2=(HomeFragment) mFragmentList.get(0).getFragment();
                            homeFragment2.sortByRollNo();
                            return true;
                        default:
                            return false;
                    }
                }
            });
            popupMenu.show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_toolbar_sort:
                openPopup();
                break;
            case R.id.ib_toolbar_layout:
                ibToggle();
                HomeFragment homeFragment=(HomeFragment) mFragmentList.get(0).getFragment();
                homeFragment.viewChanger();
        }
    }
    public void ibToggle(){
        if (mIsListShowing) {
            Toast.makeText(this, R.string.list_view, Toast.LENGTH_SHORT).show();
            ibLayout.setBackground(getResources().getDrawable(R.drawable.ic_grid));
            mIsListShowing = false;
        } else {
            Toast.makeText(this, R.string.grid_view, Toast.LENGTH_SHORT).show();
            ibLayout.setBackground(getResources().getDrawable(R.drawable.ic_list));
            mIsListShowing = true;
        }
    }
    public void abc(Student st,int position){
        AddStudentFragment addStudentFragment=(AddStudentFragment) mFragmentList.get(1).getFragment();
        addStudentFragment.setEditLayout(st);
        index=position;
        pqr();
    }
    public void fun(Student st){
        updateData(st,index);
    }
    public void xyz(){
        AddStudentFragment addStudentFragment=(AddStudentFragment) mFragmentList.get(1).getFragment();
        addStudentFragment.getData(1);
    }
    public void pqr(){
        AddStudentFragment addStudentFragment=(AddStudentFragment) mFragmentList.get(1).getFragment();
        addStudentFragment.getData(2);
    }
}
