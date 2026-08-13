package com.super_x;
import com.super_x.view.HomePage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main{
    public static Stage myStage;
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("Ecoload Ai");
        Application.launch(HomePage.class,args);
    }
}