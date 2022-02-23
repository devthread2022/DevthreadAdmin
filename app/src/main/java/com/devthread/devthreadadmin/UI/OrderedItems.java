package com.devthread.devthreadadmin.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devthread.devthreadadmin.R;
import com.devthread.devthreadadmin.databinding.FragmentOrderedItemsBinding;

public class OrderedItems extends Fragment {
    private FragmentOrderedItemsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderedItemsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
}