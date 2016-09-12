package scripts.combat.tasks.bank;

import org.osbot.rs07.api.Bank;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.MethodProvider;
import scripts.utils.Methods;
import scripts.utils.Task;

/**
 * Created by macalroy on 9/4/2016.
 */
public class WalkToBank extends Task {

    public WalkToBank(MethodProvider api, Methods m) {
        super(api, m);
    }

    @Override
    public boolean verify() {
        return !Banks.LUMBRIDGE_UPPER.contains(api.myPlayer());
    }


    @Override
    public void execute() throws InterruptedException {
        api.getWalking().webWalk(Banks.LUMBRIDGE_UPPER);
    }
}
