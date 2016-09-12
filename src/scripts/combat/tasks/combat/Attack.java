package scripts.combat.tasks.combat;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;
import scripts.utils.Data;
import scripts.utils.Methods;
import scripts.utils.Task;

/**
 * Created by macalroy on 8/27/16.
 */
public class Attack extends Task {

    public Attack(MethodProvider api, Methods m) {
        super(api, m);
    }

    @Override
    public boolean verify() {
        return !api.myPlayer().isUnderAttack() && api.myPlayer().getInteracting() == null;
    }

    @Override
    public void execute() throws InterruptedException {
        int i = 20;
        if (api.myPlayer().getInteracting() != null) {
            return;
        }
        switch (Data.attackType) {
            case 0:
                changeFightingStyle(0);
                break;
            case 1:
                changeFightingStyle(1);
                break;
            case 2:
                changeFightingStyle(2);
                break;
            case 3:
                if ((Data.currentTime / 60) % 60 == i) {
                    i += 20;
                    changeFightingStyle(api.random(0, 3));
                }
                break;
        }
        NPC n = api.getNpcs().closest(new Filter<NPC>() {
            @Override
            public boolean match(NPC npc) {
                return (isCows ? (npc.getName().equalsIgnoreCase("Cow") || npc.getName().equalsIgnoreCase("Calf"))
                        : npc.getName().equalsIgnoreCase("Chicken"))
                        && !npc.isUnderAttack() && npc.getInteracting() == null;
            }
        });
        if (n != null) {
            int r = api.random(1, 600);
            if (api.myPlayer().getPosition().distance(n) > 5) {
                api.getWalking().walk(n);
            }
            if (r >= 500 || !n.isVisible()) {
                api.getCamera().toEntity(n);
            }
            if (n.getInteracting() != null && !n.isUnderAttack()) {
                return;
            }
            if (!api.getMap().canReach(n)) {
                return;
            }
            if (api.myPlayer().isUnderAttack()) {
                if (n.getInteracting() == api.myPlayer()) {
                    new ConditionalSleep(5000) {
                        @Override
                        public boolean condition() {
                            return api.myPlayer().isAnimating()
                                    && api.myPlayer().isInteracting(n)
                                    && n.exists()
                                    && n.isAnimating()
                                    && n.isHitBarVisible();
                        }
                    }.sleep();
                    return;
                }
            } else {
                if (n.interact("Attack")) {
                    new ConditionalSleep(5000) {
                        @Override
                        public boolean condition() {
                            return api.myPlayer().isAnimating()
                                    && api.myPlayer().isInteracting(n)
                                    && n.exists()
                                    && n.isAnimating()
                                    && n.isHitBarVisible();
                        }
                    }.sleep();
                    return;
                }
            }
        }
    }

    public int getFightingStyleId() {
        return api.getConfigs().get(43);
    }

    public void changeFightingStyle(int id) {
        if (id == getFightingStyleId())
            return;
        Tab currentTab = api.getTabs().getOpen();
        if (currentTab != Tab.ATTACK)
            api.getTabs().open(Tab.ATTACK);
        switch (id) {
            case 0:
                api.getWidgets().get(593, 3).interact();
                break;
            case 1:
                api.getWidgets().get(593, 7).interact();
                break;
            case 2:
                api.getWidgets().get(593, 11).interact();
                break;
            case 3:
                api.getWidgets().get(593, 15).interact();
                break;
        }
        if (currentTab != Tab.INVENTORY)
            api.getTabs().open(Tab.INVENTORY);
    }

}
