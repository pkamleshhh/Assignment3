package com.example.myapplication.modal;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    String studentName;
    int studentClass,studentRollNo;

    public Student(String studentName, int studentClass, int studentRollNo) {
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentRollNo = studentRollNo;
    }

    protected Student(Parcel in) {
        studentName = in.readString();
        studentClass = in.readInt();
        studentRollNo = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getStudentName() {
        return studentName;
    }

    public void setName(String name) {
        studentName = name;
    }

    public int getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(int studentClass) {
        this.studentClass = studentClass;
    }

    public int getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(int studentRollNo) {
        this.studentRollNo = studentRollNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(studentName);
        dest.writeInt(studentClass);
        dest.writeInt(studentRollNo);
    }
}
