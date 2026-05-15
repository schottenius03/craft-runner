package com.example.craftrunner.ui.app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.craftrunner.R;
import com.example.craftrunner.databinding.FragmentItemConfigurationBinding;
import com.example.craftrunner.ui.app.db.room.Item;

public class ItemConfigurationFragment extends Fragment {

    private ItemConfigurationViewModel mViewModel;
    private FragmentItemConfigurationBinding binding;

    public static ItemConfigurationFragment newInstance() {
        return new ItemConfigurationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentItemConfigurationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ViewModel with AndroidViewModelFactory to pass Application context
        mViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(ItemConfigurationViewModel.class);

        // Observe toast messages from ViewModel and show as Toasts
        mViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            if (message != null && !message.isEmpty()) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        // Observe found item to update UI fields or clear them if null
        mViewModel.getFoundItem().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                binding.editTextItemCode.setText(String.valueOf(item.itemCode));
                binding.editTextItemName.setText(item.itemName);
                binding.editTextItemPrice.setText(String.valueOf(item.itemPrice));
                binding.editTextItemQuantity.setText(String.valueOf(item.itemQty));
            } else {
                binding.editTextItemCode.setText("");
                binding.editTextItemName.setText("");
                binding.editTextItemPrice.setText("");
                binding.editTextItemQuantity.setText("");
            }
        });

        // Back arrow navigation
        binding.imageViewBackArrow5.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_addNewItemFragment_to_dashboardFragment);
        });

        // Add new item button click
        binding.buttonAddNewItem.setOnClickListener(v -> {
            String itemCodeStr = binding.editTextItemCode.getText().toString().trim();
            String itemNameStr = binding.editTextItemName.getText().toString().trim();
            String itemPriceStr = binding.editTextItemPrice.getText().toString().trim();
            String itemQtyStr = binding.editTextItemQuantity.getText().toString().trim();

            if (itemCodeStr.isEmpty() || itemNameStr.isEmpty() || itemPriceStr.isEmpty() || itemQtyStr.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int iItemCode, iItemPrice, iItemQuantity;
            try {
                iItemCode = Integer.parseInt(itemCodeStr);
                iItemPrice = Integer.parseInt(itemPriceStr);
                iItemQuantity = Integer.parseInt(itemQtyStr);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Invalid number format", Toast.LENGTH_SHORT).show();
                return;
            }

            Item item = new Item();
            item.itemCode = iItemCode;
            item.itemName = itemNameStr;
            item.itemPrice = iItemPrice;
            item.itemQty = iItemQuantity;

            mViewModel.insertItem(item);
        });

        // Delete item button click
        binding.buttonDelete.setOnClickListener(v -> {
            String itemCodeStr = binding.editTextItemCode.getText().toString().trim();

            if (itemCodeStr.isEmpty()) {
                Toast.makeText(getContext(), "Please enter the item code to delete", Toast.LENGTH_SHORT).show();
                return;
            }

            int iItemCode;
            try {
                iItemCode = Integer.parseInt(itemCodeStr);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Invalid item code", Toast.LENGTH_SHORT).show();
                return;
            }

            mViewModel.deleteItem(iItemCode);
        });

        // Clear fields button click
        binding.buttonClear.setOnClickListener(v -> {
            binding.editTextItemCode.setText("");
            binding.editTextItemName.setText("");
            binding.editTextItemPrice.setText("");
            binding.editTextItemQuantity.setText("");
        });

        // Search button click
        binding.buttonSearch.setOnClickListener(v -> {
            String itemCodeStr = binding.editTextItemCode.getText().toString().trim();
            String itemNameStr = binding.editTextItemName.getText().toString().trim();

            Integer iItemCode = null;
            if (!itemCodeStr.isEmpty()) {
                try {
                    iItemCode = Integer.parseInt(itemCodeStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Invalid item code", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            String searchName = itemNameStr.isEmpty() ? null : itemNameStr;

            if (iItemCode == null && searchName == null) {
                Toast.makeText(getContext(), "Enter at least one search field", Toast.LENGTH_SHORT).show();
                return;
            }

            mViewModel.searchItem(iItemCode, searchName);
        });

        // Update item button click
        binding.buttonUpdate.setOnClickListener(v -> {
            String itemCodeStr = binding.editTextItemCode.getText().toString().trim();
            String itemNameStr = binding.editTextItemName.getText().toString().trim();
            String itemPriceStr = binding.editTextItemPrice.getText().toString().trim();
            String itemQtyStr = binding.editTextItemQuantity.getText().toString().trim();

            if (itemCodeStr.isEmpty() || itemNameStr.isEmpty() || itemPriceStr.isEmpty() || itemQtyStr.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int iItemCode, iItemPrice, iItemQuantity;
            try {
                iItemCode = Integer.parseInt(itemCodeStr);
                iItemPrice = Integer.parseInt(itemPriceStr);
                iItemQuantity = Integer.parseInt(itemQtyStr);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Invalid number format", Toast.LENGTH_SHORT).show();
                return;
            }

            mViewModel.updateItem(iItemCode, itemNameStr, iItemPrice, iItemQuantity);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // avoid memory leaks
    }
}