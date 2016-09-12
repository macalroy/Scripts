package scripts.combat.tasks.combat;

import org.osbot.rs07.script.MethodProvider;
import scripts.utils.Methods;
import scripts.utils.Task;

/**
 * Created by macalroy on 8/27/16.
 */
public class Dialogue extends Task {


    public Dialogue(MethodProvider api, Methods m) {
        super(api, m);
    }

    @Override
    public boolean verify() {
        return api.getDialogues().isPendingContinuation();
    }

    @Override
    public void execute() throws InterruptedException {
        api.sleep(api.random(6000, 7000));
    }

}
