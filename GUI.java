import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Sets up the stage and gui style for the rest of the program
 * @author Olimjon Nematov
 *
 */
public class GUI extends Application{
	public mainSession ms= new mainSession();
	public Stage w;
	/**
	 * initiates the gui
	 */
	@Override
	public void start(Stage args) throws Exception {
		w= new Stage();
		w.setWidth(1100);
		w.setHeight(600);
		w.setResizable(false);
	//layouts
		HBox mainOptions= new HBox(150);
		VBox firstLayout= new VBox();
		firstLayout.setAlignment(Pos.CENTER);
		mainOptions.setAlignment(Pos.CENTER);
		
	//controls
		Image logo= new Image("Loc8r.png");
		ImageView iv= new ImageView(logo);
		iv.setFitWidth(700);	
		iv.setFitHeight(400);
		Button myStarredB= new Button("Favorites");
		Button searchB= new Button("Search ");
		Button myFlaggedB= new Button("Flagged");
		
		myStarredB.setId("button");
		searchB.setId("button");
		myFlaggedB.setId("button");
		
	//animations
		FadeTransition ft= new FadeTransition(Duration.millis(3000),iv);
		ft.setFromValue(-1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(true);
		ft.play();
	//scenes
		Scene main= new Scene(firstLayout,400,400);				
	//music.
		final URL resource = getClass().getResource("music.mp3");
		final Media m= new Media(resource.toString());
		MediaPlayer mp= new MediaPlayer(m);
		mp.setAutoPlay(true);
		mp.play();
	//setOn Actions
		searchB.setOnAction(e ->{
			SearchClass.Scene(w, main, ms);
		});
		myStarredB.setOnAction(e ->{
			StarredClass.getScene(main, w,ms);
		});
		myFlaggedB.setOnAction(e ->{
			FlaggedClass.getScene(main, w, ms);
		});
	//getChildren
			mainOptions.getChildren().addAll(myStarredB,searchB,myFlaggedB);
			firstLayout.getChildren().addAll(iv,mainOptions);
		
//styling
		main.getStylesheets().add("rome.css");		
	
		w.setScene(main);
		w.show();
	}
	public static void main(String[] args) {
		GUI.launch(args);
}

}
