package com.devthread.devthreadadmin.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devthread.devthreadadmin.Adapter.NotificationAdapter;
import com.devthread.devthreadadmin.Model.NotificationModel;
import com.devthread.devthreadadmin.R;
import com.devthread.devthreadadmin.databinding.FragmentNotification2Binding;
import com.devthread.devthreadadmin.databinding.FragmentNotificationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notification extends Fragment {
    private FragmentNotification2Binding binding;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String uid;
    private List<NotificationModel> notificationModelList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotification2Binding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();;
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        binding.notificationRecycler.setHasFixedSize(false);
        binding.notificationRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("Admin").child(uid).child("Notifications").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    notificationModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        NotificationModel notificationModel = dataSnapshot.getValue(NotificationModel.class);
                        notificationModelList.add(notificationModel);
                    }
                    NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(),notificationModelList);
                    binding.notificationRecycler.setAdapter(notificationAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}