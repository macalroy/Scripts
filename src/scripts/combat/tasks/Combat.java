package scripts.combat.tasks;

import org.osbot.rs07.script.MethodProvider;
import scripts.combat.tasks.combat.Attack;
import scripts.combat.tasks.combat.BuryBones;
import scripts.combat.tasks.combat.DropRawFood;
import scripts.combat.tasks.combat.Loot;
import scripts.utils.Data;
import scripts.utils.Methods;
import scripts.utils.Task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by macalroy on 8/25/2016.
 */
public class Combat extends Task {

    private ArrayList<Task> tasks = new ArrayList<>();

    public Combat(MethodProvider api, Methods m) {
        super(api, m);

    }

    @Override
    public boolean verify() {

        if ((Data.isCows ? LOCATIONS[1] : LOCATIONS[0]).contains(api.myPlayer())) {
            tasks.addAll(Arrays.asList(new Loot(api, m),
                    new DropRawFood(api, m),
                    new BuryBones(api, m),
                    new Attack(api, m)));
            return true;
        }
        return false;
    }


    @Override
    public void execute() throws InterruptedException {
        tasks.stream().filter(task -> task.verify()).forEach((task1) -> {
            try {
                task1.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
