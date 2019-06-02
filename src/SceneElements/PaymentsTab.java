package SceneElements;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Controller.Controller;
import classes.Payment;
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

public class PaymentsTab extends Tab {
	private ListView<String> list; // this is like my home page
	Button button;
	TextArea txt;

	public PaymentsTab() {
		this.setText("Payments");
		this.setClosable(false);
		SplitPane sp = new SplitPane();
		StackPane sp1 = new StackPane();
		BorderPane sp2 = new BorderPane();
		BorderPane sp3 = new BorderPane();
		Button b = new Button("List Payments");
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
				loadPaymentDetails(id);
			}
		});

		txt = new TextArea("Click payment To View Details ....");
		sp3.setCenter(txt);
		sp2.setTop(b);
		sp2.setCenter(list);

		this.setContent(sp);
	}

	private void loadPaymentDetails(int id) {
		List<List<String>> results = Controller.getInstance().getPaymentDetails(id);
		String output = "";
		for (int i = 0; i < results.size(); i++) {
			output += "Patient Details\n ";
			output += "\tPatient Name" + results.get(i).get(0)+ " "+ results.get(i).get(1)+"\n";
			output += "\tPatient Address " + results.get(i).get(2)+"\n";
			output += "\tPatient Number "+ results.get(i).get(3)+"\n";
			output += "Invoice Details\n";
			output += "\tInvoice Number "+ results.get(i).get(5)+"\n";
			output += "\tInvoice Total Amount "+ results.get(i).get(4)+"\n";
			output += "\tInvoice Date "+ results.get(i).get(6)+"\n";		
			output += "Procedure Details\n";	
		}
		output += Controller.getInstance().getProceduresFromPayment(id);
		txt.setText(output);
	}

	private void loadProcedures() {// this is getting my list of patients from
		try {// the patient list class
			List<String> patientNames = new ArrayList<String>();
			Controller.getInstance().readPayments();
			for (Payment p : Controller.getInstance().getPaymentList().getPayment()) {
				patientNames.add(p.getID() + "," + p.getPaymentAmt() + " - " + p.getPaymentDate());
			}

			ObservableList<String> myList = FXCollections.observableArrayList(patientNames);
			list.setItems(myList);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
