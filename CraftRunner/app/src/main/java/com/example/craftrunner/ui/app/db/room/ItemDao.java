package com.example.craftrunner.ui.app.db.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item")
    List<Item> getAll();

    // add new
    @Insert
    void insertAll(Item... users);

    // delete
    @Query("DELETE FROM item WHERE itemCode = :code")
    void deleteByItemCode(int code);

    // search by id
    @Query("SELECT * FROM item WHERE itemCode = :code LIMIT 1")
    Item getItemByCode(int code);

    // search id, name or both
    @Query("SELECT * FROM item WHERE " +
            "(:itemCode IS NULL OR itemCode = :itemCode) AND " +
            "(:itemName IS NULL OR itemName LIKE '%' || :itemName || '%')")
    List<Item> searchItems(Integer itemCode, String itemName);

    // update
    @Query("UPDATE item SET itemName = :name, itemPrice = :price, itemQty = :qty WHERE itemCode = :code")
    void updateItemByCode(int code, String name, int price, int qty);

}
