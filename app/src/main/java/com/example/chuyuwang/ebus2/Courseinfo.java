package com.example.chuyuwang.ebus2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chuyuwang on 2/23/15.
 */
public class Courseinfo {

    private  DBHelper dbHelper;
    public Courseinfo (Context context){dbHelper = new DBHelper(context);}

    public int insert(Course course){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Course.KEY_NAME, course.name);
        values.put(Course.KEY_TIME,course.time);
        values.put(Course.KEY_PROF, course.professor);

        long course_Id = db.insert(Course.Table, null, values);
        db.close();
        return (int) course_Id;
    }

    public void delete(int course_Id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Course.Table, Course.KEY_ID + "= ?", new String[] { String.valueOf(course_Id) });
        db.close();
    }

//    public void clear(){
//        Cursor c = getAllRows();
//        long id = c.getColumnIndexOrThrow(Course.KEY_ID);
//        if(c.moveToFirst()){
//            do{
//                deleteRow(c.getLong((int) id));
//            }while(c.moveToNext());
//        }
//        c.close();
//    }

//    public Cursor getAllRows(){
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String where = null;
//        Cursor c = db.query(true,D)
//
//    }

    public void update(Course course){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Course.KEY_NAME, course.name);
        values.put(Course.KEY_TIME,course.time);
        values.put(Course.KEY_PROF, course.professor);

        db.update(Course.Table, values, Course.KEY_ID + "= ?", new String[] { String.valueOf(course.course_Id) });
        db.close();

    }

    public void clear(int course_Id){

    }

    public ArrayList<HashMap<String,String>> getCourseList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Course.KEY_ID + "," +
                Course.KEY_NAME + "," +
                Course.KEY_TIME + "," +
                Course.KEY_PROF +
                " FROM " + Course.Table;

        ArrayList<HashMap<String, String>> courseList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> course = new HashMap<String, String>();
                course.put("id", cursor.getString(cursor.getColumnIndex(Course.KEY_ID)));
                course.put("name", cursor.getString(cursor.getColumnIndex(Course.KEY_NAME)));
                courseList.add(course);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return courseList;
    }

    public Course getCourseById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Course.KEY_ID + "," +
                Course.KEY_NAME + "," +
                Course.KEY_TIME + "," +
                Course.KEY_PROF +
                " FROM " + Course.Table
                +" WHERE "+Course.KEY_ID+" =?";

        int iCount =0;
        Course course = new Course();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );


        if (cursor.moveToFirst()) {
            do {
               course.course_Id = cursor.getInt(cursor.getColumnIndex(Course.KEY_ID));
               course.name = cursor.getString(cursor.getColumnIndex(Course.KEY_NAME));
               course.time = cursor.getString(cursor.getColumnIndex(Course.KEY_TIME));
               course.professor = cursor.getString(cursor.getColumnIndex(Course.KEY_PROF));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return course;
    }
}
