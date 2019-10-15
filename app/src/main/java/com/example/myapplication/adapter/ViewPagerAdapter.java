package com.example.myapplication.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.fragments.AddStudentFragment;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.modal.FragmentDetails;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<FragmentDetails> mFragmentDetailsList = new ArrayList<FragmentDetails>();

    public ViewPagerAdapter(FragmentManager fm, final ArrayList<FragmentDetails> fragmentDetailsList) {
        super(fm);
        this.mFragmentDetailsList = fragmentDetailsList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentDetailsList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mFragmentDetailsList.size();
    }

    /*public void addViewPager(Fragment fragment, String string) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(string);
    }*/

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentDetailsList.get(position).getTitle();
    }
}
