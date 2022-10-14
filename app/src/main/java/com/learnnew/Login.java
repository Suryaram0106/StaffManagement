package com.learnnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sql.DBHelper;

public class Login extends AppCompatActivity {

    EditText email , password;
    Button btnLogin;
    TextView createAccount;
    DBHelper dbHelper;


    @Override
    public void onBackPressed() {
        Login.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        setContentView(R.layout.activity_login);
        email=findViewById(R.id.edittxt_email_login);
        password=findViewById(R.id.edittxt_password_login);
        btnLogin = findViewById(R.id.btn_login);
        dbHelper = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailCheck = email.getText().toString();
                String passCheck = password.getText().toString();
                Cursor cursor = dbHelper.getData();
                if(cursor.getCount() == 0){
                    Toast.makeText(Login.this,"No entries Exists",Toast.LENGTH_LONG).show();
                }
                if (loginCheck(cursor,emailCheck,passCheck)) {
                    Intent intent = new Intent(Login.this,DisplayPage.class);
                    //intent.putExtra("email",emailCheck);
                    email.setText("");
                    password.setText("");
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setCancelable(true);
                    builder.setTitle("Try Again");
                    builder.setMessage("Wrong Credential");
                    builder.show();
                }
                /*Intent intent = new Intent(Login.this,DisplayPage.class);
                //intent.putExtra("email",emailCheck);
                email.setText("");
                password.setText("");
                startActivity(intent);*/
                dbHelper.close();
            }
        });
        createAccount=findViewById(R.id.txt_sign);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });


    }

    private boolean loginCheck(Cursor cursor, String emailCheck, String passCheck) {

            while (cursor.moveToNext()){
                if (cursor.getString(0).equals(emailCheck)) {
                    if (cursor.getString(2).equals(passCheck)) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }

}