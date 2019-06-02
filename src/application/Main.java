package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Controller.Controller;
import Dialogs.AlertBox;
import SceneElements.MyTabPane;
import Scenes.MyHomeScene;

public class Main extends Application {
		
	//variables
	Stage window;
	TextField nameInput;
	PasswordField passInput;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override

	public void start(Stage primaryStage) throws Exception {
		window = primaryStage; //is your main window
		Controller.getInstance().setStage(window);//allows the controller to get things working
		window.setTitle("The Dental Surgery");
		//login window
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		// Name Label - constrains use (child, column, row)
		Label nameLabel = new Label("Username:");
		GridPane.setConstraints(nameLabel, 0, 0);

		// Name Input
		nameInput = new TextField();
		GridPane.setConstraints(nameInput, 1, 0);

		// Password Label
		Label passLabel = new Label("Password:");
		GridPane.setConstraints(passLabel, 0, 1);

		// Password Input
		passInput = new PasswordField();
		passInput.setPromptText("password");
		GridPane.setConstraints(passInput, 1, 1);

		// Login
		Button loginButton = new Button("Log In");
		loginButton.setOnAction(e -> handleLogin());
		GridPane.setConstraints(loginButton, 1, 2);
		// Add everything to grid
		grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);
		Scene scene = new Scene(grid, 300, 200);
		window.setScene(scene);
		window.show();
	}
	
	public void handleLogin(){		
		//importing AlertBox code, so if put in wrong details a warning window will pop up
		String username = nameInput.getText().toString();
		String password = passInput.getText().toString();
		AlertBox alert = new AlertBox();
		boolean success = true;
		String title = "Incorrect Login", 
			   message = "Username/password combo incorrect!!";
		
		
		if(!Controller.getInstance().checkLogin(username,password)){
			AlertBox.display(title, message);
		}
		else{
			//opens up the main windows
			MyTabPane pane = new MyTabPane();
			MyHomeScene my = new MyHomeScene(pane);
			Controller.getInstance().setScene(my);
		}
	}


}