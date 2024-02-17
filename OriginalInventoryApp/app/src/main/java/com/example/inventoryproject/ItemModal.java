package com.example.inventoryproject;

public class ItemModal {

    private String id, itemName, quantity;

    // Creating getter and setter methods
    public String getItemName() { return itemName; }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getQuantity()
    {
        return quantity;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }


    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    // Constructor
    public ItemModal( String id,
                      String itemName,
                      String quantity)
    {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
    }

}
