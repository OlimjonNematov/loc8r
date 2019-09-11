import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * This class sets up the scene for all the starred locations
 * @author Olimjon Nematov
 *
 */
public class StarredClass {
	/**
	 * sets the stage to a new scene
	 * @param main Stage, scene that it is changing from, main session
	 * 
	 */
public static void getScene(Scene main, Stage w, mainSession ms) {
	//Layouts
		BorderPane starredL= new BorderPane();
		HBox hb= new HBox(100);
		VBox starredName= new VBox(25);
		VBox starredAddress= new VBox(25);
		VBox starredOption= new VBox(11);
	//lists etc
		ArrayList<Label> namesList= new ArrayList<>();
		ArrayList<Label> addressList= new ArrayList<>();
		ArrayList<Button> buttonList= new ArrayList<>();
		
		
//*
		for(location l: ms.starred) {
			namesList.add(new Label(l.getName()));
			addressList.add(new Label(l.getAddress()));
			buttonList.add(new Button("UnStar"));
		}
		//*/
		
		for(Button b: buttonList) {

			int x=buttonList.indexOf(b);
			location l= ms.starred.get(x);
			b.setText("unstar");
			b.setId("flag");
			b.setMaxSize(75, 8);
			b.setOnAction(e ->{
				if(ms.starred.contains(l)) {
					ms.starred.remove(x);
					namesList.remove(x);
					addressList.remove(x);
					buttonList.remove(x);
					
					starredName.getChildren().remove(x);
					starredAddress.getChildren().remove(x);
					starredOption.getChildren().remove(x);
					
				}
			});

		}
		
	//controls
		Label starredTitle= new Label("My Favorites");
		Button backB= new Button("<");
		backB.setId("button");
	//alignment
		starredL.setAlignment(starredTitle, Pos.CENTER);
		starredL.setAlignment(backB, Pos.CENTER);
		
		hb.setAlignment(Pos.CENTER);
	//setOn Actions
		backB.setOnAction(e ->{
			w.setScene(main);
		});
	
	//getChildren		
		starredAddress.getChildren().addAll(addressList);
		starredOption.getChildren().addAll(buttonList);
		starredName.getChildren().addAll(namesList);
		starredL.setTop(starredTitle);
		starredL.setLeft(backB);
		hb.getChildren().addAll(starredName, starredAddress,starredOption);
		starredL.setCenter(hb);
	//set up
		Scene S= new Scene(starredL);
	//css
		starredTitle.setId("title");
		S.getStylesheets().add("rome.css");
		w.setScene(S);
		
}
/**
 * stars all the locations
 * @param list of all locations
 */
	public ArrayList<location> starIt(ArrayList<location>all) {
		ArrayList<location> starred= new ArrayList();
		
		for(location l: all) {
			if(l.isStar()==true) {
				starred.add(l);
			}
		}
	
		return starred;
	}

}
