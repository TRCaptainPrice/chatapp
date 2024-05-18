package com.avsaroglu.chatapp01;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button sign,login;
    EditText email,passwrod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign=findViewById(R.id.sign);
        login=findViewById(R.id.login);
        email=findViewById(R.id.email);
        passwrod=findViewById(R.id.password);
        FirebaseAuth auth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(email.getText().toString(),passwrod.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"giris yapildi",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(MainActivity.this,chatactivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(MainActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(email.getText().toString(),passwrod.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"kayit basrili",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(MainActivity.this,chatactivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(MainActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }


}