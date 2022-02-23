package com.devthread.devthreadadmin.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devthread.devthreadadmin.R;
import com.devthread.devthreadadmin.databinding.FragmentTestingProjectsBinding;

public class TestingProjects extends Fragment {
    private FragmentTestingProjectsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTestingProjectsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
}