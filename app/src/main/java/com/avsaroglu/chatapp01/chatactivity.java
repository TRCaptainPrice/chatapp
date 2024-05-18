package com.avsaroglu.chatapp01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseKt;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthCredential;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class chatactivity extends AppCompatActivity {

    EditText message;
    Button sendBtn;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatactivity);

        sendBtn=findViewById(R.id.send);
        ArrayList al= new ArrayList();
        al.clear();
        list=findViewById(R.id.list);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        message=findViewById(R.id.EditTextMessage);
        ArrayAdapter adapter =  new ArrayAdapter(chatactivity.this, android.R.layout.simple_list_item_1,al);
        adapter.clear();



        db.getReference("Messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //Toast.makeText(ChatActivity.this, "Message Added! " + snapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                al.add(snapshot.getValue().toString());
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendBtn.setOnClickListener(v -> {

            FirebaseAuth auth = FirebaseAuth.getInstance();
            Date getdate= Calendar.getInstance().getTime();
            String newMessage = message.getText().toString();
            db.getReference("Messages").child(auth.getUid()+getdate.toString()).setValue(newMessage);
            message.setText(""); // Gönderdikten sonra EditText'i temizle

        });

    }
}