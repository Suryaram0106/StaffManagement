package com.learnnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sql.DBHelper;

public class SignUp extends AppCompatActivity {

    EditText name,email,pass,dob,mobile,address;
    DBHelper dbHelper;


    @Override
    public void onBackPressed() {
        SignUp.this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email=findViewById(R.id.edittxt_email);
        name=findViewById(R.id.edittxt_name);
        pass=findViewById(R.id.edittxt_password);
        dob=findViewById(R.id.edittxt_dob);
        mobile=findViewById(R.id.edittxt_mobile);
        address=findViewById(R.id.edittxt_address);


        Button signUp = findViewById(R.id.btn_signup);
        dbHelper = new DBHelper(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_txt = email.getText().toString();
                String name_txt = name.getText().toString();
                String pass_txt = pass.getText().toString();
                String  dob_txt = dob.getText().toString();
                String  mobile_txt = mobile.getText().toString();
                String  address_txt = address.getText().toString();

                boolean b =dbHelper.insetStaffData(email_txt,name_txt,pass_txt,dob_txt,mobile_txt,address_txt);
                if (b){
                    Toast.makeText(SignUp.this,"Data inserted..Login Now...",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUp.this,Login.class);
                    startActivity(i);
                }else {
                    Toast.makeText(SignUp.this,"Failed To Sign Up",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}