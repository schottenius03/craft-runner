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
import com.example.craftrunner.databinding.FragmentEmbroideryBinding;
import com.example.craftrunner.databinding.FragmentHookCrochetBinding;

public class HookCrochetFragment extends Fragment {

    private HookCrochetViewModel mViewModel;
    private FragmentHookCrochetBinding binding;

    public static HookCrochetFragment newInstance() {
        return new HookCrochetFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHookCrochetBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
        // return inflater.inflate(R.layout.fragment_hook_crochet, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HookCrochetViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        binding.imageViewBackArrow8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_hookCrochetFragment_to_dashboardFragment);
            }
        });

        // hook five
        binding.textViewHookFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_hookCrochetFragment_to_hook_fiveFragment);
            }
        });

        binding.imageViewHookFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_hookCrochetFragment_to_hook_fiveFragment);
            }
        });

        // hook six
        binding.textViewHookSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_hookCrochetFragment_to_hook_sixFragment);
            }
        });

        binding.imageViewHookSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_hookCrochetFragment_to_hook_sixFragment);
            }
        });
    }
}