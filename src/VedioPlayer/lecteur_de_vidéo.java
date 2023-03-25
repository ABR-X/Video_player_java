package VedioPlayer;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class lecteur_de_vidéo extends BorderPane {
	
	Media enregistrement;
	MediaPlayer lecteur;
	MediaView interface_utilisateur;
	Pane espace;
	VideoControls barre_de_lecture;
	
	public lecteur_de_vidéo(String file) {
		enregistrement = new Media(file);
		lecteur = new MediaPlayer(enregistrement);
		interface_utilisateur = new MediaView(lecteur);
		espace = new Pane();
		barre_de_lecture = new VideoControls(lecteur);
		
		espace.getChildren().add(interface_utilisateur);
		
		setCenter(espace);
		setBottom(barre_de_lecture);
		
		lecteur.play();
	}

}
