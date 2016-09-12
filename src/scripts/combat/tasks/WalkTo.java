package scripts.combat.tasks;

import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.Condition;
import scripts.utils.Methods;
import scripts.utils.Task;

/**
 * Created by macalroy on 8/25/2016.
 */
public class WalkTo extends Task {

    public WalkTo(MethodProvider api, Methods m) {
        super(api, m);
    }

    @Override
    public boolean verify() {
        return !api.getInventory().isFull() && !(isCows ? LOCATIONS[1] : LOCATIONS[0]).contains(api.myPlayer());
    }

    @Override
    public void execute() throws InterruptedException {
        final WebWalkEvent cowWalk = new WebWalkEvent(LOCATIONS[1]);
        final WebWalkEvent chickenWalk = new WebWalkEvent(LOCATIONS[0]);
        if (isCows) {
            cowWalk.setBreakCondition(new Condition() {
                @Override
                public boolean evaluate() {
                    if (!getIsCows()) {
                        return true;
                    }
                    return false;
                }
            });
            api.execute(cowWalk);
        } else if (!isCows) {
            chickenWalk.setBreakCondition(new Condition() {
                @Override
                public boolean evaluate() {
                    if (getIsCows()) {
                        return true;
                    }
                    return false;
                }
            });
            api.execute(chickenWalk);
        }
    }
}
