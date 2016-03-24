package com.example.chuyuwang.ebus2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity implements View.OnClickListener{

    Button btadd,btlist,btclear;
    TextView course_Id;
    DBHelper db;


    public void onClick(View view) {
        if (view == (Button) findViewById(R.id.btadd)) {

            Intent intent = new Intent(this, AddNewCourse.class);
            intent.putExtra("course_Id", 0);
            startActivity(intent);

        } else {
            Courseinfo info = new Courseinfo(this);
            ArrayList<HashMap<String, String>> courseList = info.getCourseList();
            if (courseList.size() != 0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        course_Id = (TextView) view.findViewById(R.id.course_Id);
                        String courseId = course_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), AddNewCourse.class);
                        objIndent.putExtra("course_Id", Integer.parseInt(courseId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(MainActivity.this, courseList, R.layout.courseinfo, new String[]{"id", "name"}, new int[]{R.id.course_Id, R.id.course_name});
                setListAdapter(adapter);
            } else {
                Toast.makeText(this, "No Course!", Toast.LENGTH_SHORT).show();
            }

        }
    }

//         if(view == (Button)findViewById(R.id.btclear)){
//
//             Toast.makeText(this, "Clear!", Toast.LENGTH_SHORT);
//             finish();
//              }
//        }


//    public void Clear(View view){
//        db.deleteAll()
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btadd = (Button) findViewById(R.id.btadd);
        btadd.setOnClickListener(this);

        btlist = (Button) findViewById(R.id.btlist);
        btlist.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
