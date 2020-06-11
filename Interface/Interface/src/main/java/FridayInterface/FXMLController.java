package FridayInterface;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.omg.CORBA.portable.IDLEntity;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;

public class FXMLController implements Initializable {

    @FXML
    private Label lblTime;
    @FXML
    private Label lblDayMonth;
    @FXML
    private Label lblWeekDay;
    @FXML
    private ImageView imageView;
    @FXML
    private TableView<JSONArray> tableView;

    private TableColumn idColumn = new TableColumn<>("ID");

    private TableColumn keyWord1Column = new TableColumn<>("Palavra chave 1");
    private TableColumn keyWord2Column = new TableColumn<>("Palavra chave 2");
    private TableColumn keyWord3Column = new TableColumn<>("Palavra chave 3");
    private TableColumn respose1Column = new TableColumn<>("Resposta 1");
    private TableColumn respose2Column = new TableColumn<>("Resposta 2");
    private TableColumn respose3Column = new TableColumn<>("Resposta 3");
    private TableColumn commandColumn = new TableColumn<>("Comando");

    private TableColumn homeWorkTypeColumn = new TableColumn<>("Tipo");
    private TableColumn homeWorkSubjectColumn = new TableColumn<>("Matéria");
    private TableColumn homeWorkColumn = new TableColumn<>("tarefa");
    private TableColumn homeWorkDeliveryColumn = new TableColumn<>("Entrega");
    private TableColumn homeWorkDescColumn = new TableColumn<>("Descrição");

    private TableColumn projectColumn = new TableColumn<>("Projeto");
    private TableColumn languagesColumn = new TableColumn<>("Linguagens");

    private TableColumn DeviceColumn = new TableColumn<>("Device");
    private TableColumn DeviceDescColumn = new TableColumn<>("Descrição");
    private TableColumn DeviceJsonColumn = new TableColumn<>("JSON");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventHandler handler = new EventHandler() {

            @Override
            public void handle(Event event) {
                connectionLoop();
            }

        };

        KeyFrame frame = new KeyFrame(Duration.millis(1000), handler);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    private void connectionLoop() {

        ServerConnection connection = new ServerConnection();

        JSONArray arrayResponse;
        JSONObject response = (JSONObject) connection.receive("getDevicesStatus").get("Interface");

        int action = Integer.parseInt(response.get("action").toString());

        System.out.println(action);

        switch (action) {
            case 0:
                tableView.setVisible(false);
                imageView.setVisible(false);
                break;

            case 1:
                response = (JSONObject) connection.receive("getInteractions");


                if(!tableView.getColumns().contains(commandColumn)){

                    for (int c = 0; c < response.size(); c++) {
                        arrayResponse = (JSONArray) response.get(Integer.toString(c));

                        tableView.getItems().add(arrayResponse);
                    }

                    addInteractionsColumns();
                    
                }

                break;

            case 2:
                if(!tableView.getColumns().contains(homeWorkColumn)){
                    addHomeWorksColumns();
                }
                break;

            case 3:
                if(!tableView.getColumns().contains(projectColumn)){
                    addProjectsColumns();
                }
                break;

            case 4:
                if(!tableView.getColumns().contains(DeviceColumn)){
                    addDevicesColumns();
                }
                break;

            default:
                break;
        }
    }


    private void addInteractionsColumns(){
        tableView.getColumns().clear();
        tableView.getColumns().addAll(idColumn, keyWord1Column, keyWord2Column, keyWord3Column, respose1Column, respose2Column, respose3Column, commandColumn);

        tableView.setVisible(true);
    }

    private void addHomeWorksColumns(){
        tableView.getColumns().clear();
        tableView.getColumns().addAll(idColumn, homeWorkTypeColumn, homeWorkSubjectColumn, homeWorkColumn, homeWorkDeliveryColumn, homeWorkDescColumn);

        tableView.setVisible(true);
    }

    private void addProjectsColumns(){
        tableView.getColumns().clear();
        tableView.getColumns().addAll(idColumn, projectColumn, languagesColumn);

        tableView.setVisible(true);
    }

    private void addDevicesColumns(){
        tableView.getColumns().clear();

        tableView.getColumns().addAll(idColumn, DeviceColumn, DeviceDescColumn, DeviceJsonColumn);
        tableView.setVisible(true);

    }
}
