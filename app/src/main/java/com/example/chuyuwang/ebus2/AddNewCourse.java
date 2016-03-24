package com.example.chuyuwang.ebus2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * Created by chuyuwang on 2/22/15.
 */
public class AddNewCourse extends ActionBarActivity implements View.OnClickListener{
    Button btsave;
    Button btback;
    Button btdelete;
    EditText editName;
    EditText editTime;
    EditText editProf;
    private int _Course_id = 0;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewcourse);

        btsave = (Button)findViewById(R.id.btsave);
        btback = (Button)findViewById(R.id.btback);
        btdelete = (Button)findViewById(R.id.btdelete);

        editName = (EditText)findViewById(R.id.editName);
        editTime = (EditText)findViewById(R.id.editTime);
        editProf = (EditText)findViewById(R.id.editProf);

        btsave.setOnClickListener(this);
        btback.setOnClickListener(this);
        btdelete.setOnClickListener(this);

        Intent intent = getIntent();
        _Course_id =intent.getIntExtra("course_Id", 0);
        Courseinfo info = new Courseinfo(this);
        Course course = new Course();
        course = info.getCourseById(_Course_id);

        editName.setText(course.name);
        editTime.setText(course.time);
        editProf.setText(course.professor);
    }

    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.addnewcourse,menu);
        return true;

    }

    public boolean onOptionItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        if(view == findViewById(R.id.btsave)) {
            Courseinfo info = new Courseinfo(this);
            Course course = new Course();
            course.name = editName.getText().toString();
            course.time = editTime.getText().toString();
            course.professor = editProf.getText().toString();
            course.course_Id = _Course_id;
            if (_Course_id == 0) {
                _Course_id = info.insert(course);

                Toast.makeText(this, "New Course Insert", Toast.LENGTH_SHORT).show();
            } else {
                info.update(course);
                Toast.makeText(this, "Course Update", Toast.LENGTH_SHORT).show();
                ;
            }
        }else if(view == findViewById(R.id.btdelete)){
            Courseinfo info = new Courseinfo(this);
            info.delete(_Course_id);
            Toast.makeText(this, "Course Delete", Toast.LENGTH_SHORT);
            finish();
        }else if(view == findViewById(R.id.btback)){
            finish();
        }

    }

}
