package com.example.stresstest.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.stresstest.R;
import com.example.stresstest.databinding.StressTestFragmentBinding;

public class StressTestFragment extends Fragment {


    private StressTestFragmentBinding binding;
    private StressTestViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(requireActivity()).get(StressTestViewModel.class);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setDataLineChart();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.stress_test_fragment, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getStartTest().observe(getViewLifecycleOwner(), voidEvent -> {
            if (voidEvent.isHandled()) {
                Toast.makeText(requireActivity(), "start", Toast.LENGTH_SHORT).show();

            }
        });

        viewModel.getStopTest().observe(getViewLifecycleOwner(), voidEvent -> {
            if (voidEvent.isHandled()) {
                Toast.makeText(requireActivity(), "stop", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
