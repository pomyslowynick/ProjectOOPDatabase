package SceneElements;

import java.util.ArrayList;
import java.util.List;


import Controller.Controller;
import classes.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PatientsTab extends Tab {
	private ListView<String> list; //this is like my home page
	Button button;
	TextArea txt;
	TextField fldName, fldLastName, fldAddress, fldPhoneNumber;
	public PatientsTab(){
		this.setText("Patients");
        this.setClosable(false);
        SplitPane sp = new SplitPane();
        StackPane sp1 = new StackPane();
        BorderPane sp2 = new BorderPane();
        BorderPane sp3 = new BorderPane();
        Button btnListPatients = new Button("List Patients");   
        Button btnAddPatient = new Button("Add Patient"); 
        Button submitPatient = new Button("Submit");   
        Button btnRemovePatient = new Button("Remove Patient"); 
    	VBox vbox = new VBox();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 5));
        hbox.setSpacing(10);
        list  = new ListView<>();
        
		fldName = new TextField("");
		fldLastName = new TextField("");
		fldAddress = new TextField("");
		fldPhoneNumber = new TextField("");
		
        btnListPatients.setOnAction(e -> loadPatients());
        btnAddPatient.setOnAction(e -> addPatient());
        btnRemovePatient.setOnAction(e -> loadPatients());
        submitPatient.setOnAction(e -> addPatient());
        sp.getItems().addAll(sp3, sp1, sp2);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {
            	String splitted[] =list.getSelectionModel().getSelectedItem().split(",",2); // will be matched 1 times.
            	int id = Integer.parseInt(splitted[0]);
            	loadPatientDetails(id);
            }
        });
        
        hbox.getChildren().addAll(btnListPatients, btnAddPatient, btnRemovePatient);
        vbox.getChildren().addAll(fldName, fldLastName, fldAddress, fldPhoneNumber, submitPatient);
        txt = new TextArea("Click Patient To View Details ....");
        sp3.setCenter(txt);
        sp2.setLeft(vbox);
        sp2.setTop(hbox);
        sp2.setCenter(list);
        
        this.setContent(sp);
	}
	
	private void loadPatientDetails(int id){
		List<List<String>> results = Controller.getInstance().getPatientDetails(id); 
		for (int i = 0; i < results.size(); i++) {
			String fName = results.get(i).get(1);
			String lName = results.get(i).get(2);
			String address = results.get(i).get(3);
			int phone = Integer.parseInt(results.get(i).get(4));
			Patient p = new Patient(fName, lName, phone, address);
			txt.setText(p.toString());
		}
	}
	
	private void loadPatients(){
		List<String> patientNames = new ArrayList<String>();
		Controller.getInstance().readPatients();
		for(Patient p:Controller.getInstance().getPatientList().getPatients()){
			patientNames.add(p.getID()+","+p.getFName()+" "+p.getLName());
		}
		
		ObservableList<String> myList = FXCollections.observableArrayList(patientNames);
		list.setItems(myList);		
	}
	
//	private void addPatient(){
//		List<String> patientNames = new ArrayList<String>();
////		Controller.getInstance().addPatient(name, fname);
//		
//		ObservableList<String> myList = FXCollections.observableArrayList(patientNames);
//		list.setItems(myList);		
//	}
	
	private void addPatient() {
		Controller.getInstance();
		String name = fldName.getText();
		String lastName = fldLastName.getText();
		String address = fldAddress.getText();
//		int phone = Integer.parseInt(fldPhoneNumber.getText());
		Patient newPatient = new Patient(name, lastName, 2333, address);
		Controller.addPatient(newPatient);
		clearFields();
	}

	private void clearFields() {
		fldName.clear();
		fldLastName.clear();
		fldAddress.clear();
		fldPhoneNumber.clear();
	}
	
	private void removePatient(){
		List<String> patientNames = new ArrayList<String>();
		Controller.getInstance().readPatients();
		for(Patient p:Controller.getInstance().getPatientList().getPatients()){
			patientNames.add(p.getID()+","+p.getFName()+" "+p.getLName());
		}
		
		ObservableList<String> myList = FXCollections.observableArrayList(patientNames);
		list.setItems(myList);		
	}
}
