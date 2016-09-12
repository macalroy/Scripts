package scripts.combat.tasks.combat;

import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.filter.NameFilter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;
import scripts.utils.Methods;
import scripts.utils.Task;

/**
 * Created by macalroy on 9/4/2016.
 */
public class BuryBones extends Task {

    public BuryBones(MethodProvider api, Methods m) {
        super(api, m);
    }

    @Override
    public boolean verify() {
        return !api.myPlayer().isUnderAttack()
                && !api.getCombat().isFighting()
                && api.myPlayer().getInteracting() == null
                && api.getInventory().contains(new ContainsNameFilter<Item>("Bones"));
    }

    @Override
    public void execute() throws InterruptedException {
        api.getInventory().filter(new ContainsNameFilter<Item>("Bones")).stream().filter(i -> i.interact("Bury")).forEach(item -> new ConditionalSleep(1000) {
            @Override
            public boolean condition() {
                return item == null;
            }
        });
    }
}
