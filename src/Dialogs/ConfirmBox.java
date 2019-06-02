package Dialogs;

import javafx.stage.*;

import javafx.scene.*;

import javafx.scene.layout.*;

import javafx.scene.control.*;

import javafx.geometry.*;

public class ConfirmBox {//can be used to make sure you want to add procedure to that patient
	//variable
	static boolean answer;

	public static boolean display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label();
		label.setText(message);
		// Create two buttons
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		// Clicking will set answer and close window
		yesButton.setOnAction(e -> {//setOnAction is what happens when button is pressed
			answer = true;
			window.close();
		});
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		VBox layout = new VBox(10);
		// Add buttons
		layout.getChildren().addAll(label, yesButton, noButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		// Make sure to return answer
		return answer;
	}
}
