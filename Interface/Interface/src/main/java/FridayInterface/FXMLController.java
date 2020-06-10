package FridayInterface;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

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
    private TableView tableView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventHandler handler = new EventHandler(){

			@Override
			public void handle(Event event) {
                connectionLoop();
                
                System.out.println("loop");
			}

        };

        KeyFrame frame = new KeyFrame(Duration.millis(1000), handler);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }    

    private void connectionLoop(){
        ServerConnection connection = new ServerConnection();

        JSONArray arrayResponse;
        JSONObject response = (JSONObject) connection.receive("getDevicesStatus").get("Interface");

        int action = Integer.parseInt(response.get("action").toString());
        
        System.out.println(action);

        switch(action){
            case 1:
                response = (JSONObject) connection.receive("getInteractions");
                arrayResponse = (JSONArray) response.get("0");
                System.out.println(arrayResponse.get(0));

                response = connection.receive("getInteractionsHeader");
                arrayResponse = (JSONArray) response.get("Header");
                System.out.println(arrayResponse);

                tableView.setVisible(true);
                break;
            default:
                break;
        }
    }
}
