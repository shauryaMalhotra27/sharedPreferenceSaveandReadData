package com.example.sharedpreferencesavereaddata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView nameView, ageView;
    EditText nameEdit, ageEdit;
    Button submitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calling the declared variables using their id
        nameView = findViewById(R.id.nameView);
        ageView = findViewById(R.id.ageView);
        nameEdit = findViewById(R.id.nameEdit);
        ageEdit = findViewById(R.id.ageEdit);
        submitBtn = findViewById(R.id.submitBtn);
        
        
        readDataFromSharedPreferences();


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nameEdit.getText().toString().isEmpty() || ageEdit.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Something is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    saveDataInSP(nameEdit.getText().toString().trim(), ageEdit.getText().toString().trim());
                }
            }
        });
    }

    private void readDataFromSharedPreferences() {

        SharedPreferences sP = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String name = sP.getString("NAME", "");
        String age = sP.getString("AGE", "");

        if(name.isEmpty() || age.isEmpty()){
            nameView.setText("No Data Present");
            ageView.setText("No Data Present");
            submitBtn.setText("Save Data");
        }
        else {
            nameView.setText(name);
            ageView.setText(age);
            submitBtn.setText("Update Data");
        }




    }

    private void saveDataInSP(String name, String age) {

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("NAME", name);
        myEdit.putString("AGE", age);
        myEdit.commit();

        Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();


        nameView.setText(null);
        ageView.setText(null);
        nameEdit.setText(null);
        ageEdit.setText(null);
    
    }
}


