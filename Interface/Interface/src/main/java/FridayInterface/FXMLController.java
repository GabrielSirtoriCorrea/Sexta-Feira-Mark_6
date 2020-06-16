package FridayInterface;

import java.io.File;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;

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

    private TableColumn idColumn = new TableColumn<JSONArray, Integer>("ID");

    private TableColumn keyWord1Column = new TableColumn<JSONArray, String>("Palavra chave 1");
    private TableColumn keyWord2Column = new TableColumn<JSONArray, String>("Palavra chave 2");
    private TableColumn keyWord3Column = new TableColumn<JSONArray, String>("Palavra chave 3");
    private TableColumn respose1Column = new TableColumn<JSONArray, String>("Resposta 1");
    private TableColumn respose2Column = new TableColumn<JSONArray, String>("Resposta 2");
    private TableColumn respose3Column = new TableColumn<JSONArray, String>("Resposta 3");
    private TableColumn commandColumn = new TableColumn<JSONArray, String>("Comando");

    private TableColumn homeWorkTypeColumn = new TableColumn<JSONArray, String>("Tipo");
    private TableColumn homeWorkSubjectColumn = new TableColumn<JSONArray, String>("Matéria");
    private TableColumn homeWorkColumn = new TableColumn<JSONArray, String>("tarefa");
    private TableColumn homeWorkDeliveryColumn = new TableColumn<JSONArray, String>("Entrega");
    private TableColumn homeWorkDescColumn = new TableColumn<JSONArray, String>("Descrição");

    private TableColumn projectColumn = new TableColumn<JSONArray, String>("Projeto");
    private TableColumn languagesColumn = new TableColumn<JSONArray, String>("Linguagens");

    private TableColumn DeviceColumn = new TableColumn<JSONArray, String>("Device");
    private TableColumn DeviceDescColumn = new TableColumn<JSONArray, String>("Descrição");
    private TableColumn DeviceJsonColumn = new TableColumn<JSONArray, String>("JSON");

    ServerConnection connection;
    JSONArray arrayResponse;
    JSONObject response;
    String imagePath;
    int action;
    File imageFile;
    Image image;
    ObservableList<JSONArray> tableViewData = FXCollections.observableArrayList();

    Locale locale;
    Calendar calendar;
    GregorianCalendar gregCalendar;
    SimpleDateFormat dateFormat;
    SimpleDateFormat hourFormat;
    String date;
    String hour;
    String weekDay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        EventHandler handler = new EventHandler() {

            @Override
            public void handle(Event event) {
                setClock();
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

        connection = new ServerConnection();
        response = (JSONObject) connection.receive("getDevicesStatus").get("Interface");

        action = Integer.parseInt(response.get("action").toString());

        switch (action) {
            case 0:
                tableView.setVisible(false);
                imageView.setVisible(false);
                break;

            case 1:
                response = (JSONObject) connection.receive("getInteractions");

                if (!tableView.getColumns().contains(commandColumn)) {

                    for (int c = 0; c < response.size(); c++) {
                        arrayResponse = (JSONArray) response.get(Integer.toString(c));
                        tableViewData.add(arrayResponse);
                    }
                    System.out.println(tableViewData.getClass().getName());
                    tableView.setItems(tableViewData);

                    addInteractionsColumns();

                }

                break;

            case 2:
                if (!tableView.getColumns().contains(homeWorkColumn)) {
                    addHomeWorksColumns();
                }
                break;

            case 3:
                if (!tableView.getColumns().contains(projectColumn)) {
                    addProjectsColumns();
                }
                break;

            case 4:

                if (!tableView.getColumns().contains(DeviceColumn)) {
                    response = (JSONObject) connection.receive("getDevicesJsons");

                    for (int c = 0; c < response.size(); c++) {
                        arrayResponse = (JSONArray) response.get(Integer.toString(c));
                        tableViewData.add(arrayResponse);
                        System.out.println(arrayResponse);
                    }
                    tableView.setItems(tableViewData);
                    System.out.println("TableView dados >>> " + tableView.getItems());

                    addDevicesColumns();

                    /*AINDA
                      NÃO
                    FUNCIONANDO
                    */

                }
                break;

            case 5:
                imagePath = response.get("url").toString();
                System.out.println(imagePath);
                imageFile = new File(imagePath);
                image = new Image(imageFile.toURI().toString());
                imageView.setImage(image);

                imageView.setVisible(true);

            default:
                break;
        }
    }

    private void addInteractionsColumns() {
        tableView.getColumns().clear();
        tableView.getColumns().addAll(idColumn, keyWord1Column, keyWord2Column, keyWord3Column, respose1Column,
                respose2Column, respose3Column, commandColumn);

        tableView.setVisible(true);
    }

    private void addHomeWorksColumns() {
        tableView.getColumns().clear();
        tableView.getColumns().addAll(idColumn, homeWorkTypeColumn, homeWorkSubjectColumn, homeWorkColumn,
                homeWorkDeliveryColumn, homeWorkDescColumn);

        tableView.setVisible(true);
    }

    private void addProjectsColumns() {
        tableView.getColumns().clear();
        tableView.getColumns().addAll(idColumn, projectColumn, languagesColumn);

        tableView.setVisible(true);
    }

    private void addDevicesColumns() {
        tableView.getColumns().clear();

        idColumn.setCellValueFactory(new PropertyValueFactory<JSONArray, Integer>("ID"));
        DeviceColumn.setCellValueFactory(new PropertyValueFactory<JSONArray, String>("Device"));
        DeviceDescColumn.setCellValueFactory(new PropertyValueFactory<JSONArray, String>("Descrição"));
        DeviceJsonColumn.setCellValueFactory(new PropertyValueFactory<JSONArray, String>("JSON"));
        tableView.getColumns().addAll(idColumn, DeviceColumn, DeviceDescColumn, DeviceJsonColumn);

        tableView.setVisible(true);
    }

    private void setClock() {

        try {
            locale = new Locale("pt", "BR");
            gregCalendar = new GregorianCalendar();
            dateFormat = new SimpleDateFormat("dd'/'MM'/'yyyy", locale);
            hourFormat = new SimpleDateFormat("HH':'mm", locale);

            calendar = gregCalendar.getInstance();

            date = dateFormat.format(calendar.getTime());
            hour = hourFormat.format(calendar.getTime());

            calendar.setTime(dateFormat.parse(date));

            weekDay = Integer.toString(calendar.get(Calendar.DAY_OF_WEEK)) + "ª Feira";

            if(weekDay.equals("7º Feira")){
                weekDay = "Sábado";
                
            }else{
                if(weekDay.equals("1º Feira")){
                    weekDay = "Domingo";
                }
            }  

            lblWeekDay.setText(weekDay);
            lblDayMonth.setText(date);
            lblTime.setText(hour);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }

}

