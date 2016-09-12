package scripts.combat.tasks.combat;

import javafx.application.Platform;
import org.osbot.rs07.api.filter.AreaFilter;
import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.filter.NameFilter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;
import scripts.combat.LumbridgeFighter;
import scripts.gui.LootItem;
import scripts.utils.Methods;
import scripts.utils.Task;

import java.util.List;

/**
 * Created by macalroy on 8/25/2016.
 */
public class Loot extends Task {

    public Loot(MethodProvider api, Methods m) {
        super(api, m);
    }

    @Override
    public boolean verify() {
        return !api.getInventory().isFull()
                && api.myPlayer().getInteracting() == null
                && !api.getCombat().isFighting()
                && !api.myPlayer().isUnderAttack();
    }

    @Override
    public void execute() throws InterruptedException {
        List<GroundItem> items = api.getGroundItems().filter(new AreaFilter<GroundItem>(
                        new Area(new Position(api.myPlayer().getX() + 2, api.myPlayer().getY() + 2, api.myPlayer().getZ()),
                                new Position(api.myPlayer().getX() - 2, api.myPlayer().getY() - 2, api.myPlayer().getZ()))),
                new NameFilter<GroundItem>((isCows ? COW_LOOT : CHICKEN_LOOT)));
        int r = api.random(1, 600);
        while (api.getInventory().contains(new ContainsNameFilter<Item>("Raw"))) {
            api.getInventory().dropForNameThatContains("Raw");
        }
        for (GroundItem item : items) {
            if (item != null && item.exists()) {
                if (api.myPlayer().getRecentMessages().contains("You're an Iron Man, so you can't take items that other players have dropped.")) {
                    return;
                }
                if (r >= 500 || !item.isVisible()) {
                    api.getCamera().toEntity(item);
                }
                if (item.interact("Take")) {
                    new ConditionalSleep(5000) {
                        @Override
                        public boolean condition() {
                            return !api.myPlayer().isMoving() && !item.exists();
                        }
                    }.sleep();
                    if (lootedItems.size() == 0) {
                        lootedItems.add(new LootItem(item.getName(), item.getAmount()));
                    } else {
                        for (int i = 0; i <= lootedItems.size() - 1; i++) {
                            if (lootedItems.get(i).getItemName().equalsIgnoreCase(item.getName())) {
                                lootedItems.get(i).setItemAmount(item.getAmount() + lootedItems.get(i).getItemAmount());
                            } else if (lootedItems.contains(item.getName())) {
                                lootedItems.add(new LootItem(item.getName(), item.getAmount()));
                            }
                        }
                    }
                }
            }
        }
    }
}
