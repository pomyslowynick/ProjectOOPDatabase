package SceneElements;

import java.util.ArrayList;
import java.util.List;

import Controller.Controller;
import classes.Procedure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class ReportsTab extends Tab {
	private ListView<String> list; //this is like my home page
	Button button;
	TextArea txt;

	public ReportsTab() {
		this.setText("Reports");
		this.setClosable(false);
		SplitPane sp = new SplitPane();
		StackPane sp1 = new StackPane();
		BorderPane sp2 = new BorderPane();
		BorderPane sp3 = new BorderPane();
		Button b = new Button("List Reports");
		list = new ListView<>();

		//b.setOnAction(e -> loadReports());// lambda

		sp.getItems().addAll(sp3, sp1, sp2);

		list.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {
				String splitted[] = list.getSelectionModel().getSelectedItem().split(",", 2); // will
																								// be
																								// matched
																								// 1
																								// times.
				int id = Integer.parseInt(splitted[0]);
				//loadReportsDetails(id);
			}
		});

		txt = new TextArea("Click Procedure To View Details ....");
		sp3.setCenter(txt);
		sp2.setTop(b);
		sp2.setCenter(list);

		this.setContent(sp);
	}


}