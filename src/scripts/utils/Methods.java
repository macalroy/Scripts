package scripts.utils;

import org.osbot.rs07.api.model.Character;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.input.mouse.MouseDestination;
import org.osbot.rs07.script.MethodProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macalroy on 8/27/16.
 */
public class Methods extends Data {

    public final MethodProvider api;

    public Methods(MethodProvider api) {
        this.api = api;
    }

    public String status() {
        return status;
    }

    public Data data() {
        return this;
    }

    public boolean inCombat(Character<?> c) {
        return c != null && c.isUnderAttack() && c.getInteracting() == null
                && !c.isAttackable();
    }

    public List<NPC> getInteractingWith(Character<?> c) {
        List<NPC> npcs  = new ArrayList<NPC>();
        for (NPC n : npcs) {
            if (n != null && c != null && !n.getName().contains("dog")
                    && n.isInteracting(c)) {
                npcs.add(n);
            }
        }
        return npcs;
    }

    public int getSkillLevel(Skill skill) {
        return api.getSkills().getDynamic(skill);
    }

    public int getPlayerHealth() {
        return api.getSkills().getDynamic(Skill.HITPOINTS);
    }

}
