package com.learnnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sql.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayPage extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    public void onBackPressed() {
        DisplayPage.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_page);

        dbHelper = new DBHelper(this);

        ArrayList<HashMap<String, String>> userList = dbHelper.GetStaffNames();
        ListView lv = (ListView) findViewById(R.id.staff_list);
        ListAdapter adapter = new SimpleAdapter(DisplayPage.this, userList, R.layout.list_row,new String[]{"name"}, new int[]{R.id.name});
        lv.setAdapter(adapter);

    }
}