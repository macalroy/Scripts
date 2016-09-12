package scripts.combat.tasks.combat;

import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;
import scripts.utils.Methods;
import scripts.utils.Task;

/**
 * Created by macalroy on 9/3/2016.
 */
public class DropRawFood extends Task {

    public DropRawFood(MethodProvider api, Methods m) {
        super(api, m);
    }

    @Override
    public boolean verify() {
        return !api.myPlayer().isUnderAttack()
                && api.getInventory().contains(new ContainsNameFilter<Item>("Raw"))
                && api.getInventory().isFull();
    }

    @Override
    public void execute() throws InterruptedException {
        api.getInventory().dropForNameThatContains("Raw");
    }
}
