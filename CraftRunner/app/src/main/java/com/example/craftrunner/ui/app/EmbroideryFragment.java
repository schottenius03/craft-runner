package com.example.craftrunner.ui.app;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.craftrunner.R;
import com.example.craftrunner.databinding.FragmentDashboardBinding;
import com.example.craftrunner.databinding.FragmentEmbroideryBinding;

public class EmbroideryFragment extends Fragment {

    private EmbroideryViewModel mViewModel;
    private FragmentEmbroideryBinding binding;

    public static EmbroideryFragment newInstance() {
        return new EmbroideryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEmbroideryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
        //return inflater.inflate(R.layout.fragment_embroidery, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EmbroideryViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        binding.imageViewBackArrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_embroideryFragment_to_dashboardFragment);
            }
        });

        binding.textViewDMCBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_embroideryFragment_to_threadBlackFragment);
            }
        });

        binding.imageViewThreadBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_embroideryFragment_to_threadBlackFragment);
            }
        });

        binding.textViewDMCBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_embroideryFragment_to_threadBlueFragment);
            }
        });

        binding.imageViewThreadBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_embroideryFragment_to_threadBlueFragment);
            }
        });
    }
}