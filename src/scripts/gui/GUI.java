package scripts.gui;/**
 * Created by macalroy on 8/30/2016.
 */

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.osbot.rs07.script.Script;
import scripts.utils.Data;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {

    @FXML
    private Pane rootPane;

    @FXML
    private Pane lootPane;

    @FXML
    private Text timerText;

    @FXML
    private TextField itemText;

    @FXML
    private TableView<SkillData> skillTable;

    @FXML
    private ListView<String> lootList;

    @FXML
    private TableColumn<SkillData, String> skillName;

    @FXML
    private TableColumn<SkillData, String> skillLevel;

    @FXML
    private TableColumn<SkillData, String> skillExperience;

    @FXML
    private TableColumn<SkillData, String> skillExpHr;

    @FXML
    private TableView<LootItem> lootTable;

    @FXML
    private TableColumn<LootItem, String> lootName;

    @FXML
    private TableColumn<LootItem, String> lootAmount;

    @FXML
    private MenuItem chickenItem;

    @FXML
    private MenuItem cowItem;

    @FXML
    private MenuItem attackItem;

    @FXML
    private MenuItem strengthItem;

    @FXML
    private MenuItem defenceItem;

    @FXML
    private MenuItem randomItem;

    @FXML
    private Menu lootMenu;

    @FXML
    private Button addLootButton;

    private ObservableList<SkillData> skillData = FXCollections.observableArrayList();

    private ObservableList<LootItem> lootData = FXCollections.observableArrayList();

    private ObservableList<String> lootListData = FXCollections.observableArrayList("");

    private Script script;

    public GUI(Script script) throws Exception {
        this.script = script;
        initAndShowGUI();
    }

    private void initAndShowGUI() throws Exception {
        setTitle("Lumbridge Fighter");
        final JFXPanel fxPanel = new JFXPanel();
        add(fxPanel);
        setSize(650, 500);
        setVisible(true);
        setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                script.stop(false);
            }
        });
        Platform.runLater(() -> {
            try {
                initFX(fxPanel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initFX(JFXPanel fxPanel) throws Exception {
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private Scene createScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainUI.fxml"));
        loader.setController(this);
        loader.load();

        Scene scene = new Scene(rootPane);

        skillTable.setItems(skillData);
        lootTable.setItems(lootData);

        chickenItem.setOnAction(event -> {
            Data.isCows = false;
        });

        cowItem.setOnAction(event -> {
            Data.isCows = true;
        });

        attackItem.setOnAction(event -> {
            Data.attackType = 0;
        });

        strengthItem.setOnAction(event -> {
            Data.attackType = 1;
        });

        defenceItem.setOnAction(event -> {
            Data.attackType = 2;
        });

        randomItem.setOnAction(event -> {
            Data.attackType = 3;
        });

        Label lootMenuLabel = new Label("Loot Menu");
        lootMenuLabel.setOnMouseClicked(event -> {
            try {
                script.log("Working | Open");

                Stage lootStage = new Stage();
                lootStage.initModality(Modality.NONE);

                Scene lootScene = lootMenu();

                lootStage.setScene(lootScene);
                lootStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        lootMenu.setGraphic(lootMenuLabel);
        return (scene);
    }

    private Scene lootMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lootMenu.fxml"));
        loader.setController(this);
        loader.load();

        Scene scene = new Scene(lootPane);

        lootList.setItems(lootListData);


        for (int i = 0; i <= getLootData().size() - 1; i++) {
            updateLootListData(getLootData().get(i).getItemName(), false);
        }

        addLootButton.setOnAction(event -> {
            getLootListData().add(itemText.getText());
            itemText.clear();
        });

        return (scene);
    }

    public void updateLootListData(String name, boolean remove) {
        if (remove) {
            if (getLootListData().size() == 1) {
                getLootListData().set(0, name);
            } else {
                for (int i = 0; i <= getLootListData().size() - 1; i++) {
                    if (getLootListData().get(i).equalsIgnoreCase(name)) {
                        getLootListData().remove(i);
                    }
                }
            }
        } else {
            getLootListData().add(name);
        }
    }

    public void updateSkillData(int index, int level, int actualLevel, int xp, int xpHr) {
        getSkillData().get(index).setSkillLevel(level, actualLevel);
        getSkillData().get(index).setSkillExperience(xp);
        getSkillData().get(index).setSkillExperienceHour(xpHr);
    }

    public void updateLootData(String name, int amount) {
        if (getLootData().size() == 0) {
            getLootData().add(new LootItem(name, amount));
        } else {
            for (int i = 0; i <= getLootData().size() - 1; i++) {
                if (getLootData().get(i).getItemName().equalsIgnoreCase(name)) {
                    getLootData().get(i).setItemAmount(amount);
                }
            }
        }
    }

    public void setTimerText(String text) {
        this.timerText.setText(text);
    }

    public ObservableList<SkillData> getSkillData() {
        return skillData;
    }

    public TableView<SkillData> getSkillTable() {
        return skillTable;
    }

    public ObservableList<LootItem> getLootData() {
        return lootData;
    }

    public TableView<LootItem> getLootTable() {
        return lootTable;
    }

    public ObservableList<String> getLootListData() {
        return lootListData;
    }
}
