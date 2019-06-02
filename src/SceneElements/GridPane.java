package SceneElements;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class GridPane {

//what I'm trying to do here is doing the layout of one of the tabs here and
						//then import it in the main tabPane class

	public GridPane addGridPane() {
	    GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 10, 0, 10));

	    // Category in column 2, row 1
	    Text category = new Text("Please enter in patient details");
	    grid.add(category, 1, 0); //no idea if the numbering is right
	    
	    Label fName = new Label("Firstname:");
		GridPane.setConstraints(fName, 0, 0);
		TextField firstName = new TextField();
		GridPane.setConstraints(firstName, 0, 1);

		Label lName = new Label("Lastname:");
		GridPane.setConstraints(lName, 1, 0);
		TextField lastName = new PasswordField();
		GridPane.setConstraints(lastName, 1, 1);
		
		Label phone = new Label("Phone Number:");
		GridPane.setConstraints(phone, 0, 0);
		TextField phoneNum = new TextField();
		GridPane.setConstraints(phoneNum, 0, 1);

		
		Label address = new Label("Address:");
		GridPane.setConstraints(address, 0, 0);
		TextField postAdd = new TextField();
		GridPane.setConstraints(postAdd, 0, 1);
		
		//need validation for all of the above
		return grid;
	}

	  //buttons at the bottom
	  public AnchorPane addAnchorPane(Node grid) {
		    AnchorPane anchorpane = new AnchorPane();
		    Button buttonSave = new Button("Save");
		    Button buttonCancel = new Button("Cancel");

		    HBox hb = new HBox();
		    hb.setPadding(new Insets(0, 10, 10, 10));
		    hb.setSpacing(10);
		    hb.getChildren().addAll(buttonSave, buttonCancel);

		    anchorpane.getChildren().addAll(grid,hb);   
		    AnchorPane.setBottomAnchor(hb, 8.0);
		    AnchorPane.setRightAnchor(hb, 5.0);
		    AnchorPane.setTopAnchor(grid, 10.0);

		    return anchorpane;
		}
	  
	private static void setConstraints(Label nameLabel, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	private static void setConstraints(TextField nameInput, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	private void add(Text category, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	private void setPadding(Insets insets) {
		// TODO Auto-generated method stub
		
	}

	private void setVgap(int i) {
		// TODO Auto-generated method stub
		
	}

	private void setHgap(int i) {
		// TODO Auto-generated method stub
		
	}
}
