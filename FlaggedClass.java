import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Shows all the flagged locations
 * @author Olimjon Nematov
 *
 */
public class FlaggedClass {
	/**
	 * initiates the scene and fills up with flagged locations
	 */
	public static void getScene(Scene main, Stage w, mainSession ms) {
		//Layouts
			BorderPane flaggedL= new BorderPane();
			HBox hb= new HBox(100);
			VBox flaggedName= new VBox(25);
			VBox flaggedAddress= new VBox(25);
			VBox flaggedOption= new VBox(11);
		//lists etc
			ArrayList<Label> namesList= new ArrayList<>();
			ArrayList<Label> addressList= new ArrayList<>();
			ArrayList<Button> buttonList= new ArrayList<>();
			
			
	//*
			for(location l: ms.flagged) {
				namesList.add(new Label(l.getName()));
				addressList.add(new Label(l.getAddress()));
				buttonList.add(new Button("Unflag"));
			}
			//*/
			
			for(Button b: buttonList) {

				int x=buttonList.indexOf(b);
				location l= ms.flagged.get(x);
				b.setText("unflag");
				b.setId("flag");
				b.setMaxSize(75, 8);
				b.setOnAction(e ->{
					if(ms.flagged.contains(l)) {
						ms.flagged.remove(x);
						namesList.remove(x);
						addressList.remove(x);
						buttonList.remove(x);
						
						flaggedName.getChildren().remove(x);
						flaggedAddress.getChildren().remove(x);
						flaggedOption.getChildren().remove(x);
						
					}
				});

			}
			
		//controls
			Label flaggedTitle= new Label("My Flagged");
			Button backB= new Button("<");
			backB.setId("button");
		//alignment
			flaggedL.setAlignment(flaggedTitle, Pos.CENTER);
			flaggedL.setAlignment(backB, Pos.CENTER);
			
			hb.setAlignment(Pos.CENTER);
		//setOn Actions
			backB.setOnAction(e ->{
				w.setScene(main);
			});
		
		//getChildren		
			flaggedAddress.getChildren().addAll(addressList);
			flaggedOption.getChildren().addAll(buttonList);
			flaggedName.getChildren().addAll(namesList);
			flaggedL.setTop(flaggedTitle);
			flaggedL.setLeft(backB);
			hb.getChildren().addAll(flaggedName, flaggedAddress,flaggedOption);
			flaggedL.setCenter(hb);
		//set up
			Scene S= new Scene(flaggedL);
		//css
			flaggedTitle.setId("title");
			S.getStylesheets().add("rome.css");
			w.setScene(S);
			
	}
}
 