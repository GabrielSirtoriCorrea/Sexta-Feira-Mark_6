package com.friday;

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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.util.Callback;

public class SceneController implements Initializable {

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
    private TableColumn response1Column = new TableColumn<JSONArray, String>("Resposta 1");
    private TableColumn response2Column = new TableColumn<JSONArray, String>("Resposta 2");
    private TableColumn response3Column = new TableColumn<JSONArray, String>("Resposta 3");
    private TableColumn commandColumn = new TableColumn<JSONArray, String>("Comando");

    private TableColumn homeWorkTypeColumn = new TableColumn<JSONArray, String>("Tipo");
    private TableColumn homeWorkSubjectColumn = new TableColumn<JSONArray, String>("Matéria");
    private TableColumn homeWorkColumn = new TableColumn<JSONArray, String>("Tarefa");
    private TableColumn homeWorkDeliveryColumn = new TableColumn<JSONArray, String>("Entrega");
    private TableColumn homeWorkDescColumn = new TableColumn<JSONArray, String>("Descrição");

    private TableColumn projectColumn = new TableColumn<JSONArray, String>("Projeto");
    private TableColumn projectTypeColumn = new TableColumn<JSONArray, String>("Tipo");

    private TableColumn DeviceColumn = new TableColumn<JSONArray, String>("Device");
    private TableColumn DeviceDescColumn = new TableColumn<JSONArray, String>("Descrição");
    private TableColumn DeviceActionsColumn = new TableColumn<JSONArray, String>("Número de ações");
    
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
        response = FridayComunication.readJsonFile();
        action = Integer.parseInt(response.get("action").toString());

        switch (action) {
            case 0:
                tableView.setVisible(false);
                imageView.setVisible(false);
                break;

            case 1:
                
                if (!tableView.getColumns().contains(commandColumn)) {
                    imageView.setVisible(false);
                    setTableData();

                    addInteractionsColumns();

                }

                break;

            case 2:
                if (!tableView.getColumns().contains(homeWorkColumn)) {
                    imageView.setVisible(false);
                    setTableData();
                    addHomeWorksColumns();
                }
                break;

            case 3:
                if (!tableView.getColumns().contains(projectColumn)) {
                    imageView.setVisible(false);
                    setTableData();
                    addProjectsColumns();
                }
                break;

            case 4:

                if (!tableView.getColumns().contains(DeviceColumn)) {
                    imageView.setVisible(false);
                    setTableData();

                    addDevicesColumns();


                }
                break;

            case 5:
                tableView.setVisible(false);

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

        idColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(CellDataFeatures<JSONArray, Integer> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(0));
            }
         });

         keyWord1Column.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(1));
            }
         });

         keyWord2Column.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(2));
            }
         });

        keyWord3Column.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(3));
            }
         });

        response1Column.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(4));
            }
         });

        response2Column.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(5));
            }
         });

        response3Column.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(6));
            }
         });

        commandColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(7));
            }
         });

        tableView.getColumns().addAll(idColumn, keyWord1Column, keyWord2Column, keyWord3Column, response1Column,
                response2Column, response3Column, commandColumn);

        tableView.setVisible(true);
    }

    private void addHomeWorksColumns() {
        tableView.getColumns().clear();

        idColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(CellDataFeatures<JSONArray, Integer> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(0));
            }
         });

        homeWorkTypeColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(1));
            }
         });

        homeWorkSubjectColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(2));
            }
         });

        homeWorkColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(3));
            }
         });

        homeWorkDeliveryColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(4));
            }
         });

        homeWorkDescColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(5));
            }
         });

        tableView.getColumns().addAll(idColumn, homeWorkTypeColumn, homeWorkSubjectColumn, homeWorkColumn,
                homeWorkDeliveryColumn, homeWorkDescColumn);

        tableView.setVisible(true);
    }

    private void addProjectsColumns() {
        tableView.getColumns().clear();

        projectTypeColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(1));
            }
         });

        projectColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(2));
            }
         });

        idColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(CellDataFeatures<JSONArray, Integer> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(0));
            }
         });

        tableView.getColumns().addAll(idColumn, projectTypeColumn, projectColumn);

        tableView.setVisible(true);
    }

    private void addDevicesColumns() {
        tableView.getColumns().clear();

        idColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(CellDataFeatures<JSONArray, Integer> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(0));
            }
         });

        
        DeviceColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(1));
            }
         });



        DeviceDescColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(2));
            }
         });

        DeviceActionsColumn.setCellValueFactory(new Callback<CellDataFeatures<JSONArray, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<JSONArray, String> param) {
                // TODO Auto-generated method stub
                return new ReadOnlyObjectWrapper(param.getValue().get(3));
            }
         });

        tableView.getColumns().addAll(idColumn, DeviceColumn, DeviceDescColumn, DeviceActionsColumn);

        tableView.setVisible(true);
    }

    private void setTableData(){
        tableView.getItems().clear();

        response = (JSONObject) FridayComunication.readJsonFile().get("content");
        System.out.println(response);

        for (int c = 0; c < response.size(); c++) {
            arrayResponse = (JSONArray) response.get(Integer.toString(c));
            tableViewData.add(arrayResponse);
            System.out.println(arrayResponse);
            }
            tableView.setItems(tableViewData);
            System.out.println("TableView dados >>> " + tableView.getItems());
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

            if(weekDay.equals("7ª Feira")){
                weekDay = "Sábado";
                
            }else{
                if(weekDay.equals("1ª Feira")){
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


