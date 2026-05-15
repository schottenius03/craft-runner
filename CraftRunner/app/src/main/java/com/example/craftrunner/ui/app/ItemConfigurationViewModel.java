package com.example.craftrunner.ui.app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.craftrunner.ui.app.db.room.AppDatabase;
import com.example.craftrunner.ui.app.db.room.Item;
import com.example.craftrunner.ui.app.db.room.ItemDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemConfigurationViewModel extends AndroidViewModel {

    private final ItemDao itemDao;
    private final ExecutorService executorService;

    private final MutableLiveData<Item> foundItem = new MutableLiveData<>();
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public ItemConfigurationViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);  // Singleton DB instance
        itemDao = db.ItemDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<Item> getFoundItem() {
        return foundItem;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void insertItem(Item item) {
        executorService.execute(() -> {
            // Check if item with same code exists
            List<Item> existingItems = itemDao.searchItems(item.itemCode, null);
            if (existingItems != null && !existingItems.isEmpty()) {
                toastMessage.postValue("Item code already exists");
                return;
            }

            itemDao.insertAll(item);
            toastMessage.postValue("Item added successfully");
            foundItem.postValue(item);
        });
    }

    public void deleteItem(int itemCode) {
        executorService.execute(() -> {
            Item item = itemDao.getItemByCode(itemCode);
            if (item != null) {
                itemDao.deleteByItemCode(itemCode);
                toastMessage.postValue("Item deleted successfully");
                foundItem.postValue(null);
            } else {
                toastMessage.postValue("No item found with that code");
            }
        });
    }

    public void searchItem(Integer itemCode, String itemName) {
        executorService.execute(() -> {
            List<Item> items = itemDao.searchItems(itemCode, itemName);
            if (items != null && !items.isEmpty()) {
                foundItem.postValue(items.get(0));
                toastMessage.postValue("Item found");
            } else {
                foundItem.postValue(null);
                toastMessage.postValue("No matching item found");
            }
        });
    }

    public void updateItem(int itemCode, String name, int price, int qty) {
        executorService.execute(() -> {
            Item existingItem = itemDao.getItemByCode(itemCode);
            if (existingItem != null) {
                itemDao.updateItemByCode(itemCode, name, price, qty);
                toastMessage.postValue("Item updated successfully");

                Item updatedItem = new Item();
                updatedItem.itemCode = itemCode;
                updatedItem.itemName = name;
                updatedItem.itemPrice = price;
                updatedItem.itemQty = qty;
                foundItem.postValue(updatedItem);
            } else {
                toastMessage.postValue("Item not found. Cannot update.");
            }
        });
    }
}