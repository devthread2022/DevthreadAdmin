package com.devthread.devthreadadmin.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devthread.devthreadadmin.Adapter.WorkHistoryAdapter;
import com.devthread.devthreadadmin.Model.DailyWorkUpdateModel;
import com.devthread.devthreadadmin.R;
import com.devthread.devthreadadmin.databinding.FragmentWorkUpdateBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WorkUpdate extends Fragment {
    private FragmentWorkUpdateBinding binding;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String uid;
    private List<DailyWorkUpdateModel> dailyWorkUpdateModelList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWorkUpdateBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
        binding.workUpdateRecycler.setHasFixedSize(false);
        binding.workUpdateRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("DailyEmployeeWork").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    dailyWorkUpdateModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        DailyWorkUpdateModel dailyWorkUpdateModel = dataSnapshot.getValue(DailyWorkUpdateModel.class);
                        dailyWorkUpdateModelList.add(dailyWorkUpdateModel);
                    }
                    WorkHistoryAdapter workHistoryAdapter = new WorkHistoryAdapter(getContext(),dailyWorkUpdateModelList);
                    binding.workUpdateRecycler.setAdapter(workHistoryAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}