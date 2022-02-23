package com.devthread.devthreadadmin.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devthread.devthreadadmin.Adapter.AttendanceHistoryAdapter;
import com.devthread.devthreadadmin.Model.AttendanceModel;
import com.devthread.devthreadadmin.R;
import com.devthread.devthreadadmin.databinding.FragmentAttendanceBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Attendance extends Fragment {
    private FragmentAttendanceBinding binding;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String uid;
    private List<AttendanceModel> attendanceModelList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAttendanceBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        binding.attendanceRecycler.setHasFixedSize(false);
        binding.attendanceRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("EmployeeAttendance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    attendanceModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        AttendanceModel attendanceModel = dataSnapshot.getValue(AttendanceModel.class);
                        attendanceModelList.add(attendanceModel);
                    }
                    AttendanceHistoryAdapter attendanceHistoryAdapter = new AttendanceHistoryAdapter(getContext(),attendanceModelList);
                    binding.attendanceRecycler.setAdapter(attendanceHistoryAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}