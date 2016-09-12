package scripts.combat.tasks.bank;

import org.osbot.rs07.api.filter.NameFilter;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;
import scripts.utils.Data;
import scripts.utils.Methods;
import scripts.utils.Task;

import java.util.List;

/**
 * Created by macalroy on 9/4/2016.
 */
public class BankItems extends Task {

    public BankItems(MethodProvider api, Methods m) {
        super(api, m);
    }

    @Override
    public boolean verify() {
        return Banks.LUMBRIDGE_UPPER.contains(api.myPlayer());
    }

    @Override
    public void execute() throws InterruptedException {
        RS2Object bankObject = api.getObjects().closest(new NameFilter<RS2Object>("Bank booth"));
        if (bankObject != null) {
            if (api.getBank().isOpen()) {
                while (api.getInventory().contains(BANKABLE_ITEMS)) {
                    if (api.getBank().depositAll(BANKABLE_ITEMS)) {
                        new ConditionalSleep(1500, 250) {
                            @Override
                            public boolean condition() {
                                return api.getInventory().getAmount(BANKABLE_ITEMS) == 0;
                            }
                        }.sleep();
                        if (api.getInventory().getAmount(BANKABLE_ITEMS) == 0) {
                            api.getWidgets().closeOpenInterface();
                        }
                    }
                }
            } else if (bankObject.isVisible()) {
                if (bankObject.interact("Bank")) {
                    new ConditionalSleep(1500, 250) {
                        @Override
                        public boolean condition() {
                            return api.getBank().isOpen();
                        }
                    }.sleep();
                }
            }
        }
    }
}
