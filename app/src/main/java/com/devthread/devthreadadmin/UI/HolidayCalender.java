package com.devthread.devthreadadmin.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devthread.devthreadadmin.Adapter.HolidayCalenderAdapter;
import com.devthread.devthreadadmin.Model.HolidayCalenderModel;
import com.devthread.devthreadadmin.R;
import com.devthread.devthreadadmin.databinding.FragmentHolidayCalenderBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HolidayCalender extends Fragment {
    private FragmentHolidayCalenderBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<HolidayCalenderModel> holidayCalenderModelList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentHolidayCalenderBinding.inflate(inflater,container,false);
       View view = binding.getRoot();
        binding.holidayRecycler.setHasFixedSize(true);
        binding.holidayRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        databaseReference.child("HolidayList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    holidayCalenderModelList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        HolidayCalenderModel holidayCalenderModel = dataSnapshot.getValue(HolidayCalenderModel.class);
                        holidayCalenderModelList.add(holidayCalenderModel);
                    }
                    HolidayCalenderAdapter holidayCalenderAdapter = new HolidayCalenderAdapter(getContext(),holidayCalenderModelList);
                    binding.holidayRecycler.setAdapter(holidayCalenderAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       return view;
    }
}