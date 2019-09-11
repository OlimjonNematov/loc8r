import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class searches and sets up all the  found locations
 * @author Olimjon
 *
 */
public class SearchClass {
	/**
	 * sets the scene up
	 * @param main stage, scene that is changing from, mainsession
	 */
	public static void Scene(Stage w,Scene back,mainSession ms) {
	//layouts
		BorderPane mainL= new BorderPane();
		HBox searchL= new HBox(40);
		HBox resultsL= new HBox(60);
		VBox namesL= new VBox(40);
		VBox addressL= new VBox(40);
		VBox starL= new VBox(15);
		VBox flagL= new VBox(15);

		HBox tfs= new HBox();
		
		namesL.setId("foundV");
		addressL.setId("foundV");
		starL.setId("foundV");
		flagL.setId("foundV");
		
	//lists
		ArrayList<Label> names= new ArrayList<>();
		ArrayList<Label> addres= new ArrayList<>();
		ArrayList<Button> flags= new ArrayList<>();
		ArrayList<Button> stars= new ArrayList<>();
		ArrayList<Label> distances= new ArrayList<>();
		
	//controls
		Button backB= new Button("<");
		
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Name",
			        "Catagory"
			    );
			final ComboBox<String> cb = new ComboBox<String>(options);
		cb.getSelectionModel().selectFirst();
		cb.setId("cb");
		cb.setPromptText("Select");
		TextField searchField= new TextField();
		Button searchB= new Button();
		searchB.setMaxSize(5,5);
		searchB.setId("b");
		Image i= new Image("s.png");
		ImageView iv= new ImageView(i);
		iv.setFitHeight(25);
		iv.setFitWidth(25);
		searchB.setGraphic(iv);
		TextArea namesTf= new TextArea();
		TextArea addressTf= new TextArea();
		namesTf.setEditable(false);
		addressTf.setEditable(false);
	//no valid txt
		
	//setOn Actions
		backB.setOnAction(e ->{
			w.setScene(back);
		});
		
		searchB.setOnAction(e ->{
		
			namesTf.setText("");
			addressTf.setText("");
			namesTf.clear();
			addressTf.clear();
			names.clear();
			addres.clear();
			stars.removeAll(stars);
			flags.removeAll(flags);
			starL.getChildren().clear();
			flagL.getChildren().clear();
			
		int selected =cb.getSelectionModel().getSelectedIndex();
		ArrayList<location> foundLocations= new ArrayList<>();
		
			if(selected==0) {
				foundLocations=ms.searchByTitle(searchField.getText(), ms.locations, ms.user); 
	
				for(location l: foundLocations) {
					distances.add(new Label(""+l.getDistance()));
					names.add(new Label(l.getName()));
					addres.add(new Label(l.getAddress()));
					flags.add(new Button("Flag"));
					stars.add(new Button("Star"));
					
					stars.get(foundLocations.indexOf(l)).setOnAction(g ->{
						ms.starred.add(l);

					});
					flags.get(foundLocations.indexOf(l)).setOnAction(g ->{
						ms.flagged.add(l);

					});
					for(Button b:flags) {
						b.setId("flag");
						b.setMaxSize(45, 20);
					}
					for(Button b:stars) {
						b.setId("flag");
						b.setMaxSize(45, 20);
					}
				}
				if(searchField.getText().equals("")||foundLocations.isEmpty()) {
					Stage s= new Stage();
					s.setWidth(350);
					s.setHeight(100);
					
					StackPane p= new StackPane();
					Label l= new Label("The locations you looked for do not exist");
					p.getChildren().addAll(l);
					
					Scene s2= new Scene(p,200,50);
					s2.getStylesheets().addAll("rome.css");
					s.setScene(s2);
					s.show();
				}
			}
			else if(selected==1) {
				foundLocations=ms.searchByCat(searchField.getText(), ms.locations, ms.user); 				
				for(location l: foundLocations) {
					distances.add(new Label(""+l.getDistance()));
					names.add(new Label(l.getName()));
					addres.add(new Label(l.getAddress()));
					flags.add(new Button("Flag"));
					stars.add(new Button("Star"));
					
					stars.get(foundLocations.indexOf(l)).setOnAction(g ->{
						ms.starred.add(l);

					});
					flags.get(foundLocations.indexOf(l)).setOnAction(g ->{
						ms.flagged.add(l);

					});
				}
					for(Button b:flags) {
						b.setId("flag");
						b.setMaxSize(50, 30);
					}
					for(Button b:stars) {
						b.setId("flag");
						b.setMaxSize(50,30);
					}
				
			}
			
			
			String namesA="";
			String adressA= "";
			for(Label l:names) {
				namesA+=l.getText()+"\n"+"_________________________________________"+"\n";
										  
				adressA+=addres.get(names.indexOf(l)).getText()+"\n"+"_________________________________________"+"\n";
				namesTf.setText(namesA);
				addressTf.setText(adressA);
			}
			namesA="";
			adressA="";
			foundLocations.removeAll(foundLocations);
			starL.getChildren().addAll(stars);
			flagL.getChildren().addAll(flags);
		});

		
	//styling
	
		namesTf.setId("textarea");
		addressTf.setId("textarea");
		namesTf.setMaxSize(400, 365);
		addressTf.setMaxSize(400, 365);
		backB.setId("button");
	//alignment
		searchL.setAlignment(Pos.CENTER);
		searchL.setPadding(new Insets(40,40,40,40));
		BorderPane.setAlignment(backB, Pos.CENTER);
		resultsL.setAlignment(Pos.CENTER);
	//getChildren
		tfs.getChildren().addAll(namesTf,addressTf,starL,flagL);
		resultsL.getChildren().addAll(tfs);
		searchL.getChildren().addAll(cb,searchField,searchB);
		mainL.setTop(searchL);
		mainL.setLeft(backB);
		mainL.setCenter(resultsL);
	//scene
		Scene S= new Scene(mainL);
		S.getStylesheets().add("rome.css");
		w.setScene(S);
		w.show();
		
	}

}
