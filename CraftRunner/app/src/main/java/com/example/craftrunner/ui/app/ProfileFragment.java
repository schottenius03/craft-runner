package com.example.craftrunner.ui.app;
import com.example.craftrunner.ui.app.LoginViewModel;

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
import com.example.craftrunner.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private FragmentProfileBinding binding;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
        //return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get shared ViewModel scoped to the activity
        LoginViewModel viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        // Observe the loginCount LiveData and update the TextView when it changes
        viewModel.getLoginCount().observe(getViewLifecycleOwner(), count -> {
            // display username
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String email = user.getEmail();
                binding.textBoxUserName.setText(email);
            } else {
                binding.textBoxUserName.setText("Not logged in");
            }

            binding.textViewLoginCount.setText("Logins: " + count);
        });

        // Set up the reset button to reset login count when clicked
        binding.buttonReset.setOnClickListener(v -> {
            viewModel.resetLoginCount();
        });

        binding.textViewSearch2.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_profileFragment_to_dashboardFragment);
        });

        binding.textViewSettings2.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_profileFragment_to_settingsFragment);
        });


    }
}