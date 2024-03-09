package com.mrsjdeveloper.crudsqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    Button addSubjectBtn;
    EditText edtSubject,edtDesc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_main);

        addSubjectBtn=findViewById(R.id.add_subject);
        edtSubject=findViewById(R.id.edt_subject);
        edtDesc=findViewById(R.id.edt_desc);
        dbManager=new DBManager(this);
        dbManager.open();

        addSubjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String subjectName=edtSubject.getText().toString();
                final String desc=edtDesc.getText().toString();
                if (inputAreCorrect(subjectName,desc)){

                    dbManager.insert(subjectName,desc);
                    Toast.makeText(MainActivity.this, "Add Sucessfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,SubjectListActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                }


            }
        });


    }
    private boolean inputAreCorrect(String name,String desc){
        if (name.isEmpty())
        {
            edtSubject.setError("Please Enter Subject Name");
            edtSubject.requestFocus();
            return false;
        }
        if (desc.isEmpty()){
            edtDesc.setError("Can't be empty");
            edtDesc.requestFocus();
            return false;
        }
        return true;
    }
}