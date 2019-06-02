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

public class ProceduresTab extends Tab {
	private ListView<String> list; // this is like my home page
	Button button;
	TextArea txt;

	public ProceduresTab() {
		this.setText("Procedures");
		this.setClosable(false);
		SplitPane sp = new SplitPane();
		StackPane sp1 = new StackPane();
		BorderPane sp2 = new BorderPane();
		BorderPane sp3 = new BorderPane();
		Button b = new Button("List Procedures");
		list = new ListView<>();

		b.setOnAction(e -> loadProcedures());// lambda

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
				loadProcedureDetails(id);
				loadProcedureCounts(id);
			}
		});

		txt = new TextArea("Click Procedure To View Details ....");
		sp3.setCenter(txt);
		sp2.setTop(b);
		sp2.setCenter(list);

		this.setContent(sp);
	}

	private void loadProcedureDetails(int id) {
		List<List<String>> results = Controller.getInstance().getProcedureDetails(id);
		for (int i = 0; i < results.size(); i++) {
			int ID = Integer.parseInt(results.get(i).get(0));
			String name = results.get(i).get(1);
			Double price = Double.parseDouble(results.get(i).get(2));
			Procedure p = new Procedure(ID, name, price);
			txt.setText(p.toString());
		}
	}

	private void loadProcedureCounts(int id) {
		List<List<String>> results = Controller.getInstance()
				.executeQuery("select count(*) from invoice_details where procedure_id =  " + id);
		for (int i = 0; i < results.size(); i++) {
			int count = Integer.parseInt(results.get(i).get(0));
			txt.appendText("\n This procedure has been performed  " + count + " times!");
		}
	}

	private void loadProcedures() {// this is getting my list of patients from
									// the patient list class
		List<String> patientNames = new ArrayList<String>();
		Controller.getInstance().readProcedures();
		for (Procedure p : Controller.getInstance().getProcedureList().getProcedures()) {
			patientNames.add(p.getProcNo() + "," + p.getProcName() + " " + p.getPrice());
		}

		ObservableList<String> myList = FXCollections.observableArrayList(patientNames);
		list.setItems(myList);

	}
}
