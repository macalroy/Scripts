package scripts.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by macalroy on 9/1/2016.
 */
public class SkillData {

    private final SimpleStringProperty skillName;
    private final SimpleStringProperty skillLevel;
    private final SimpleStringProperty skillExperience;
    private final SimpleStringProperty skillExpHr;

    public SkillData(String skillName, int skillLevel, int skillExp, int skillExpHr) {
        this.skillName = new SimpleStringProperty(skillName);
        this.skillLevel = new SimpleStringProperty("" + skillLevel);
        this.skillExperience = new SimpleStringProperty("" + skillExp);
        this.skillExpHr = new SimpleStringProperty("" + skillExpHr);
    }

    public String getSkillName() {
        return skillName.get();
    }

    public String getSkillLevel() {
        return skillLevel.get();
    }

    public String getSkillExperience() {
        return skillExperience.get();
    }

    public String getSkillExperiencePerHour() {
        return skillExpHr.get();
    }

    public void setSkillName(String name) {
        skillName.set(name);
    }

    public void setSkillLevel(int level, int actualLevel) {
        skillLevel.set("" + level + " (" + actualLevel + ")");
    }

    public void setSkillExperience(int exp) {
        skillExperience.set("" + exp);
    }

    public void setSkillExperienceHour(int expHr) {
        skillExpHr.set("" + expHr);
    }

    public StringProperty skillNameProperty() {
        return skillName;
    }

    public StringProperty skillLevelProperty() {
        return skillLevel;
    }

    public StringProperty skillExperienceProperty() {
        return skillExperience;
    }

    public StringProperty skillExperiencePerHourProperty() {
        return skillExpHr;
    }
}
