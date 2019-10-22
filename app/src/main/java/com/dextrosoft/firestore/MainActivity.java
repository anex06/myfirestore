package com.dextrosoft.firestore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextAddress, editTextMobile, editTextName;
    private FirebaseFirestore firebaseFirestore;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextName = findViewById(R.id.editTextName);
        firebaseFirestore = FirebaseFirestore.getInstance();
    }


    public void saveUser(View view) {
        if(TextUtils.isEmpty(editTextEmail.getText().toString())){
            editTextEmail.setError("Invalid email");
        }else if (TextUtils.isEmpty(editTextAddress.getText().toString())){
            editTextAddress.setError("Invalid address");
        }else if (TextUtils.isEmpty(editTextMobile.getText().toString())){
            editTextMobile.setError("Invalid mobile");
        }else if (TextUtils.isEmpty(editTextName.getText().toString())){
            editTextName.setError("Invalid name");
        }else {
            CollectionReference users = firebaseFirestore.collection("users");

            User mUser = new User(editTextEmail.getText().toString(),editTextAddress.getText().toString(),
                        editTextMobile.getText().toString(),editTextName.getText().toString());

            users.add(mUser).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.e("Saved to Firebase","Success");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("Excepteion",e.getMessage());
                }
            });

        }
    }

    public void getUser(View view) {
        startActivity(new Intent(MainActivity.this,UserViewActivity.class));
    }
}
