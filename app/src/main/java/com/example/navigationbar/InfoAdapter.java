package com.example.navigationbar;

public class InfoAdapter {
    private String Name;
    private String Disp;
    private String price;
    private int ID;

    InfoAdapter(){
        this.Name = "";
        this.Disp = "";
        this.price = "";
        this.ID = -1;
    }

    public String getName() {
        return Name;
    }

    public String getDisp() {
        return Disp;
    }

    public String getPrice() {
        return price;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setDisp(String disp) {
        this.Disp = disp;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
