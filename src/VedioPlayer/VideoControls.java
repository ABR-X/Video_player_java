package VedioPlayer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class VideoControls extends HBox {
    Slider Temps_Slider;
Slider Volume_Slider;

Button Button_Pause;

Label Label_Volume;

MediaPlayer Player_Video;

public VideoControls(MediaPlayer mediaPlayer) {
	this.Player_Video = mediaPlayer;

	Volume_Slider = new Slider();
	Temps_Slider = new Slider();
	
	setAlignment(Pos.CENTER);
	
	setPadding(new Insets(10,10,10,10));
	setStyle("-fx-background-color:white");

	Label_Volume = new Label("Volume :");
	Button_Pause = new Button("||");

	getChildren().add(Button_Pause);
	getChildren().add(Temps_Slider);

	getChildren().add(Label_Volume);
	getChildren().add(Volume_Slider);

	Volume_Slider.prefWidth(70);
	Volume_Slider.setMinWidth(30);

	Volume_Slider.setValue(100);

	Button_Pause.setOnAction((e) -> {
		Status status = mediaPlayer.getStatus();

		if (status == Status.PLAYING) {
			mediaPlayer.pause();
			Button_Pause.setText(">");
		} else if (status == Status.PAUSED) {
			mediaPlayer.play();
			Button_Pause.setText("||");
		}
	});

	mediaPlayer.currentTimeProperty().addListener((o) -> {
		Temps_Slider.setValue(mediaPlayer.getCurrentTime().toMillis() / mediaPlayer.getTotalDuration().toMillis() * 100);
	});

	Temps_Slider.valueProperty().addListener((o) -> {
		if (Temps_Slider.isPressed())
			mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(Temps_Slider.getValue() / 100));
	});

	Volume_Slider.valueProperty().addListener((o) -> {
		mediaPlayer.setVolume(Volume_Slider.getValue() / 100);
	});
}
}