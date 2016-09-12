package scripts.gui.test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scripts.gui.SkillData;
import scripts.utils.Data;

import java.io.*;
import java.net.URL;

/**
 * Created by macalroy on 9/1/2016.
 */
public class TestGUI extends Application {

    private ObservableList<SkillData> data = FXCollections.observableArrayList();

    @FXML
    private Pane sceneOneRoot;

    @FXML
    private Pane sceneTwoRoot;

    @FXML
    private final TableView<SkillData> skillTable = new TableView<>();

    @FXML
    private TableColumn<SkillData, String> skillName;

    @FXML
    private TableColumn<SkillData, String> skillLevel;

    @FXML
    private TableColumn<SkillData, String> skillExperience;

    @FXML
    private Button createSceneTwo;

    @FXML
    private Button createSceneOne;

    @FXML
    private Menu lootMenu;

    @FXML
    private Button addLootButton;

    @FXML
    private ListView<String> lootList;

    @FXML
    private TextField itemText;

    @FXML
    private Pane lootPane;

    private String label;

    private ObservableList<String> lootListData = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(lootMenu());
        primaryStage.show();
        //setSceneOne(primaryStage);
    }

    private void setSceneOne(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene1.fxml"));
        //loader.setRoot(sceneOneRoot);
        loader.setController(this);
        loader.load();

        Scene scene = new Scene(sceneOneRoot, 600, 400);

        if (label != null) {
            createSceneTwo.setText(label);
        }

        createSceneTwo.setOnAction(event -> {
            /*try {
                setSceneTwo(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            downloadImage("https://www.jeremyrclark.com/images/mousepepe.png");
        });
        stage.setScene(scene);
        stage.show();
    }

    private void setSceneTwo(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        //loader.setRoot(sceneTwoRoot);
        loader.setController(this);
        loader.load();

        Scene scene = new Scene(sceneTwoRoot, 550, 375);

        skillTable.setItems(data);

        data.add(new SkillData("Defence", 10, 0, 15));

        createSceneOne.setOnAction(event -> {
            try {
                label = "Number: " + Math.floor(Math.random() * 500);
                setSceneOne(stage);
                loader.setRoot(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    private Scene lootMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lootMenu.fxml"));
        loader.setController(this);
        loader.load();

        Scene scene = new Scene(lootPane);

        lootList.setItems(lootListData);

        addLootButton.setOnAction(event -> {
            if (itemText.getText() != null) {
                System.out.println(itemText.getText());
            } else {
                System.out.println("TextField is empty");
            }
        });

        return (scene);
    }

    public void downloadImage(String location) {
        try {
            URL url = new URL(location);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream("mousepepe.png");

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> getLootListData() {
        return lootListData;
    }

    public ObservableList<SkillData> getData() {
        return data;
    }

}
