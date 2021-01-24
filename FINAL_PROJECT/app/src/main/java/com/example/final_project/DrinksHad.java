package com.example.final_project;

import java.util.ArrayList;

public class DrinksHad {
    private static DrinksHad singleInstance = null;

    private ArrayList<Drink> drinksList;

    private DrinksHad(){
        drinksList = new ArrayList<>();
    }

    public static DrinksHad getInstance() {
        if (singleInstance == null) {
            singleInstance = new DrinksHad();
        }
        return singleInstance;
    }

    public void addDrink(Drink drink) {
        drinksList.add(drink);
    }

    public ArrayList<Drink> getDrinksList() {
        return drinksList;
    }

    public void setDrinksList(ArrayList<Drink> drinksList) {
        this.drinksList = drinksList;
    }
}
