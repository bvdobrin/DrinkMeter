package com.example.final_project;

public class Drink {
    private String type;
    private int quantity;
    private float alcohol;

    public Drink(String type, int quantity, float alcohol) {
        this.type = type;
        this.quantity = quantity;
        this.alcohol = alcohol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(float alcohol) {
        this.alcohol = alcohol;
    }
}
