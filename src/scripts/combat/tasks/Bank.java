package scripts.combat.tasks;

import org.osbot.rs07.script.MethodProvider;
import scripts.combat.tasks.bank.BankItems;
import scripts.combat.tasks.bank.WalkToBank;
import scripts.utils.Methods;
import scripts.utils.Task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by macalroy on 9/4/2016.
 */
public class Bank extends Task {

    private ArrayList<Task> tasks = new ArrayList<>();

    public Bank(MethodProvider api, Methods m) {
        super(api, m);
        tasks.addAll(Arrays.asList(new WalkToBank(api, m),
                new BankItems(api, m)));
    }

    @Override
    public boolean verify() {
        return api.getInventory().isFull();
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
