package com.example.craftrunner.ui.app.db.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {
    @PrimaryKey
    public int itemCode;

    public String itemName;

    public int itemPrice;

    public int itemQty;
}
