package com.example.craftrunner.ui.app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    // MutableLiveData to hold login count
    private final MutableLiveData<Integer> loginCount = new MutableLiveData<>(0);

    // Method to increment login count
    public void incrementLoginCount() {
        Integer currentCount = loginCount.getValue();
        if (currentCount == null) currentCount = 0;
        loginCount.setValue(currentCount + 1);
    }

    // Method to get the LiveData for login count (read-only for external classes)
    public LiveData<Integer> getLoginCount() {
        return loginCount;
    }

    // Method to reset the login count to zero
    public void resetLoginCount() {
        loginCount.setValue(0);
    }
}
