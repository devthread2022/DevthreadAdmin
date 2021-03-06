package com.devthread.devthreadadmin.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devthread.devthreadadmin.Adapter.LeaveHistoryAdapter;
import com.devthread.devthreadadmin.Model.LeaveModel;
import com.devthread.devthreadadmin.R;
import com.devthread.devthreadadmin.databinding.FragmentLeaveBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Leave extends Fragment {
    private FragmentLeaveBinding binding;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String uid;
    private List<LeaveModel> leaveModelList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLeaveBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        binding.leaveRecycler.setHasFixedSize(false);
        binding.leaveRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("AppliedLeave").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    leaveModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        LeaveModel leaveModel = dataSnapshot.getValue(LeaveModel.class);
                        leaveModelList.add(leaveModel);
                    }
                    LeaveHistoryAdapter leaveHistoryAdapter = new LeaveHistoryAdapter(getContext(),leaveModelList);
                    binding.leaveRecycler.setAdapter(leaveHistoryAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}