package scripts.combat;

import javafx.application.Platform;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.util.ExperienceTracker;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import scripts.combat.tasks.WalkTo;
import scripts.combat.tasks.Bank;
import scripts.combat.tasks.Combat;
import scripts.gui.LootItem;
import scripts.gui.SkillData;
import scripts.utils.Data;
import scripts.utils.Methods;
import scripts.gui.GUI;
import scripts.utils.Task;
import scripts.utils.Timer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by macalroy on 8/25/2016.
 */
@ScriptManifest(author = "Orient", version = 0, logo = "", info = "Fights any NPC in Lumbridge", name="Lumbridge Fighter")
public class LumbridgeFighter extends Script {

    public GUI gui;

    private ArrayList<Task> tasks = new ArrayList<Task>();
    private long startTime;

    private String task;

    ExperienceTracker xpTracker;

    @Override
    public void onStart() throws InterruptedException {
        try {
            gui = new GUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        startTime = System.currentTimeMillis();
        tasks.addAll(Arrays.asList(new Bank(this, new Methods(this)),
                new WalkTo(this, new Methods(this)),
                new Combat(this, new Methods(this))));
        xpTracker = getExperienceTracker();
        xpTracker.start(Skill.STRENGTH);
        xpTracker.start(Skill.ATTACK);
        xpTracker.start(Skill.HITPOINTS);
        xpTracker.start(Skill.DEFENCE);
        xpTracker.start(Skill.PRAYER);
        Platform.runLater(() -> getGui().getSkillData().addAll(new SkillData("Attack", 0, 0, 0),
                new SkillData("Strength", 0, 0, 0),
                new SkillData("Defence", 0, 0, 0),
                new SkillData("Hitpoints", 0, 0, 0),
                new SkillData("Prayer", 0, 0, 0)));
    }

    @Override
    public int onLoop() throws InterruptedException {
        tasks.stream().filter(task -> task.verify()).forEach((task1) -> {
            try {
                task1.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return 1000;
    }

    @Override
    public void onPaint(Graphics2D g) {
        Data.currentTime = System.currentTimeMillis() - startTime;
        Point mP = getMouse().getPosition();
        Image image = null;
        try {
            image = new ImageIcon(new URL("http://i.imgur.com/zwGmcvE.gif")).getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }


        g.drawImage(image, mP.x + image.getWidth(null)/4, mP.y + image.getHeight(null)/4, null);

        getGui().setTimerText(Timer.format(Data.currentTime));

        getGui().updateSkillData(0,
                xpTracker.getGainedLevels(Skill.ATTACK),
                getSkills().getDynamic(Skill.ATTACK),
                xpTracker.getGainedXP(Skill.ATTACK),
                perHour(xpTracker.getGainedXP(Skill.ATTACK)));
        getGui().updateSkillData(1,
                xpTracker.getGainedLevels(Skill.STRENGTH),
                getSkills().getDynamic(Skill.STRENGTH),
                xpTracker.getGainedXP(Skill.STRENGTH),
                perHour(xpTracker.getGainedXP(Skill.STRENGTH)));
        getGui().updateSkillData(2,
                xpTracker.getGainedLevels(Skill.DEFENCE),
                getSkills().getDynamic(Skill.DEFENCE),
                xpTracker.getGainedXP(Skill.DEFENCE),
                perHour(xpTracker.getGainedXP(Skill.DEFENCE)));
        getGui().updateSkillData(3,
                xpTracker.getGainedLevels(Skill.HITPOINTS),
                getSkills().getDynamic(Skill.HITPOINTS),
                xpTracker.getGainedXP(Skill.HITPOINTS),
                perHour(xpTracker.getGainedXP(Skill.HITPOINTS)));
        getGui().updateSkillData(4,
                xpTracker.getGainedLevels(Skill.PRAYER),
                getSkills().getDynamic(Skill.PRAYER),
                xpTracker.getGainedXP(Skill.PRAYER),
                perHour(xpTracker.getGainedXP(Skill.PRAYER)));
        if (Data.lootedItems.size() > 0) {
            for (int i = 0; i <= Data.lootedItems.size() - 1; i++) {
                getGui().updateLootData(Data.lootedItems.get(0).getItemName(),
                        Data.lootedItems.get(0).getItemAmount());
            }
        }
    }

    public int perHour(int value) {
        return (int) ((value) * 3600000D / (System.currentTimeMillis() - startTime));
    }

    public int getTextWidth(String string, Graphics2D g) {
        return g.getFontMetrics().stringWidth(string);
    }

    public int getTextHeight(Graphics2D g) {
        return g.getFontMetrics().getHeight();
    }

    public GUI getGui() {
        return gui;
    }
}
