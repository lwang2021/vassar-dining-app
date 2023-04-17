package vassar.cmpu203.vassardiningapp.model;

import java.util.ArrayList;
import java.util.List;

/** A mock database containing populated menus for testing */
public class Data {

    private static final ArrayList<Menu> MENUS = new ArrayList<>();

    /** Populate the set of Menus */
    public static void populateMenus() {
        ArrayList<MenuItem> breakfastItems = new ArrayList<>();
        ArrayList<MenuItem> lunchItems = new ArrayList<>();
        MenuItem eggs = new MenuItem("eggs", "scrambled", new ArrayList<>(List.of(DietaryRestriction.VEGETARIAN)));

        breakfastItems.add(eggs);
        breakfastItems.add(new MenuItem("bacon", "crispy", new ArrayList<>()));
        lunchItems.add(eggs);
        lunchItems.add(new MenuItem("pasta", "", new ArrayList<>(List.of(DietaryRestriction.VEGETARIAN))));
        lunchItems.add(new MenuItem("salad", "", new ArrayList<>(List.of(DietaryRestriction.VEGAN, DietaryRestriction.VEGETARIAN))));

        Menu breakfast = new Menu("deece", "today", "breakfast", breakfastItems);
        Menu lunch = new Menu("deece", "today", "lunch", lunchItems);
        Menu tmrBreakfast = new Menu("deece", "tomorrow", "breakfast", lunchItems);

        MENUS.add(breakfast);
        MENUS.add(lunch);
        MENUS.add(tmrBreakfast);
    }

    /**
     * Search through all the menus to find one that matches the given cafe and mealtime.
     * If none is found, throw an error.
     *
     * @param cafe the name of the desired cafe
     * @param date the name of the desired mealtime
     * @return a menu that matches the cafe and mealtime, otherwise throw an error
     */
    public static List<Menu> findMenu(String cafe, String date) {
        List<Menu> menus = new ArrayList<>();
        for (Menu menu : MENUS) {
            if (menu.getCafe().equalsIgnoreCase(cafe) && menu.getDate().equalsIgnoreCase(date)) {
                menus.add(menu);
            }
        }
        return menus;
    }
}
