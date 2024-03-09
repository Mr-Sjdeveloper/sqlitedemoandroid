package com.mrsjdeveloper.crudsqlitedemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class ModifySubjectActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mTitletext, mDescText;
    private long mID;
    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modity Record");
        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();

        mTitletext = findViewById(R.id.subject_edt);
        mDescText = findViewById(R.id.description_edt);
        Button updateBtn = findViewById(R.id.btn_update);
        Button mDeleteBtn = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");

        mID = Long.parseLong(id);
        mTitletext.setText(name);
        mDescText.setText(desc);

        updateBtn.setOnClickListener(this);
        mDeleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_update:
                String title = mTitletext.getText().toString();
                String desc = mDescText.getText().toString();

                AlertDialog.Builder updateDialog=new AlertDialog.Builder(this);
                updateDialog.setTitle("Are You sure to Update ? ");
                updateDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbManager.update(mID, title, desc);
                        Toast.makeText(ModifySubjectActivity.this, "Update Succesfully..", Toast.LENGTH_SHORT).show();
                        returnHome();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ModifySubjectActivity.this, "Not updated..", Toast.LENGTH_SHORT).show();
                                returnHome();
                            }
                        }).show();

               // this.returnHome();
                break;

            case R.id.btn_delete:
                AlertDialog.Builder deleteDialog=new AlertDialog.Builder(this);
                deleteDialog.setTitle("Are You sure to Delete ? ");
                deleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbManager.delete(mID);
                        Toast.makeText(ModifySubjectActivity.this, "Delete Succesfully..", Toast.LENGTH_SHORT).show();
                        returnHome();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(ModifySubjectActivity.this, "Not Deleted..", Toast.LENGTH_SHORT).show();
                    returnHome();

                }
            }).show();

                // this.returnHome();

        }
    }

    private void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), SubjectListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);

    }

}
