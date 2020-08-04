/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.friday;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Gazebo
 */
public class SextaFeiraInterface extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Runtime.getRuntime().exec("cmd.exe /c start \\Sexta-Feira-Mark_6\\Sexta-Feira(A.I.)\\Sexta-Feira(A.I.).py");
        
        Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Sexta-Feira");
        stage.setScene(scene);
        stage.show();
        
        stage.setFullScreen(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
