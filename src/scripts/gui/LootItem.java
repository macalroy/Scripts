package scripts.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by macalroy on 9/6/2016.
 */
public class LootItem {

    private final SimpleStringProperty itemName;
    private final SimpleStringProperty itemAmount;

    public LootItem(String itemName, int itemAmount) {
        this.itemName = new SimpleStringProperty(itemName);
        this.itemAmount = new SimpleStringProperty("" + itemAmount);
    }

    public String getItemName() {
        return itemName.get();
    }

    public int getItemAmount() {
        return Integer.parseInt(itemAmount.get());
    }

    public void setItemName(String name) {
        itemName.set(name);
    }

    public void setItemAmount(int amount) {
        itemAmount.set("" + amount);
    }

    public StringProperty itemNameProperty() {
        return itemName;
    }

    public StringProperty itemAmountProperty() {
        return itemAmount;
    }

}
