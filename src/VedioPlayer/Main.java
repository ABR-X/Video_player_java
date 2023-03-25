package VedioPlayer;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {

	FileChooser fileChooser;

	lecteur_de_vidéo player;

	MenuBar menuBar;
	Menu fileMenu;
	MenuItem openMenuItem;

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);

			fileChooser = new FileChooser();

			menuBar = new MenuBar();
			fileMenu = new Menu("Fichier");
			openMenuItem = new MenuItem("Ouvrir");

			fileMenu.getItems().add(openMenuItem);
			menuBar.getMenus().add(fileMenu);

			openMenuItem.setOnAction((e) -> {

				try {
					File mediaFile = fileChooser.showOpenDialog(primaryStage);
					if(player != null) {
						player.player.dispose();
					}
					player = new lecteur_de_vidéo(mediaFile.toURI().toURL().toExternalForm());
					player.view.setFitWidth(scene.getWidth());
					root.setCenter(player);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});

			root.setTop(menuBar);
			primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
				if (player != null)
					player.view.setFitWidth(scene.getWidth());
			});

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
