package com.devthread.devthreadadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import com.devthread.devthreadadmin.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        setContentView(view);
        binding.showPassBtn.setOnClickListener(this::showHidePass);
        binding.userBtnLogin.setOnClickListener(view1 -> {
            String email = binding.userTxtEmail.getText().toString();
            String password = binding.userTxtPassword.getText().toString();
            if (email.isEmpty()){
                Toast.makeText(this, "Enter email address.", Toast.LENGTH_SHORT).show();
            }else if (password.isEmpty()){
                Toast.makeText(this, "Enter password.", Toast.LENGTH_SHORT).show();
            }else {
                databaseReference.child("AdminEmail").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String adminEmail = snapshot.child("email").getValue().toString();
                            if (!email.equals(adminEmail)){
                                Toast.makeText(MainActivity.this, "You are not authorised to use this application.", Toast.LENGTH_SHORT).show();
                            }else if (email.equals(adminEmail)){
                                authenticateUser(email,password);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void authenticateUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                }else {
                    Toast.makeText(MainActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
        });
    }
    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser!=null)
        {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }
    }
    public void showHidePass(View view){

        if(view.getId()==R.id.show_pass_btn){

            if(binding.userTxtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){

                binding.userTxtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                binding.userTxtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
}