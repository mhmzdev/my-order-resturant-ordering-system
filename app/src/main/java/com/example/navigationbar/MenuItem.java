package com.example.navigationbar;

public class MenuItem {
    String Name, Description, Price;
    int Image;

    public MenuItem() {

    }

    public MenuItem(String itemname, String itemDesc, String itemPrice, int itemImage) {
        Name = itemname;
        Description = itemDesc;
        Price = itemPrice;
        this.Image = itemImage;
    }

    public void setName(String itemName) {
        Name = itemName;
    }

    public void setDescription(String itemDesc) {
        Description = itemDesc;
    }

    public void setPrice(String itemPrice) {
        Price = itemPrice;
    }

    public void setImage(int itemImage) {
        this.Image = itemImage;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getPrice() {
        return Price;
    }

    public int getImage() {
        return this.Image;
    }
}
