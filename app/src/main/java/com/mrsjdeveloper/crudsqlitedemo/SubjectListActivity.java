package com.mrsjdeveloper.crudsqlitedemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubjectListActivity extends AppCompatActivity {

    private DBManager mDBManager;
    private ListView mListView;
    private SimpleCursorAdapter adapter;
    final String[] from=new String[]{DBHelper._ID,DBHelper.SUBJECT,DBHelper.DESC};
    final int[] to=new int[]{R.id.id,R.id.title,R.id.desc};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        mDBManager=new DBManager(this);
        mDBManager.open();
        Cursor cursor=mDBManager.fetch();

        mListView=findViewById(R.id.list_view);
        mListView.setEmptyView(findViewById(R.id.empty));

        adapter=new SimpleCursorAdapter(this,R.layout.subject_list_rows,cursor,from,to,0);
        adapter.notifyDataSetChanged();

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long viewId) {
                TextView idTextView=view.findViewById(R.id.id);
                TextView titleTextView=view.findViewById(R.id.title);
                TextView descTextView=view.findViewById(R.id.desc);

                String id=idTextView.getText().toString();
                String title=titleTextView.getText().toString();
                String desc=descTextView.getText().toString();

                Intent modify_intent=new Intent(getApplicationContext(),ModifySubjectActivity.class);
                modify_intent.putExtra("name",title);
                modify_intent.putExtra("desc",desc);
                modify_intent.putExtra("id",id);

                startActivity(modify_intent);



            }
        });


    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if (id==R.id.add_subject){
            Intent addIntent=new Intent(this,MainActivity.class);
            startActivity(addIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}