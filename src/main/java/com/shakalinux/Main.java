package com.shakalinux;

import com.shakalinux.util.InicializarSistema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        InicializarSistema init = new InicializarSistema();
        init.init();
    }

}
