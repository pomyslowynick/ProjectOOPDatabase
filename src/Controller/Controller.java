package Controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import ListCLasses.PatientList;
import ListCLasses.PaymentList;
import ListCLasses.ProcedureList;
import classes.Patient;
import classes.Payment;
import classes.Procedure;
import database.DatabaseConnection;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
	private static Controller instance;
	private Stage stage;
	private static PatientList patients;
	private ProcedureList procedures;
	private PaymentList payments;
	Scene scene;

	public Controller() {
		instance = this;
		patients = new PatientList();
		procedures = new ProcedureList();
		payments = new PaymentList();
	}

	public static Controller getInstance() {
		if (instance == null) {
			return new Controller();
		}

		return instance;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}// window

	public void setScene(Scene scene) {
		this.scene = scene;
		this.stage.setScene(this.scene);
		this.stage.show();
	}// within the window where everything happens

	public boolean checkLogin(String username, String password) {
		boolean returnVal = false;
		DatabaseConnection db = new DatabaseConnection();
		try {
			db.makeConnection();
			List<List<String>> results = db.executeQueryForResults(
					"select * from dentist where username = '" + username + "' and pass_word = '" + password + "'");
			if (results.size() > 0) {
				returnVal = true;
			}
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}
		return returnVal;
	}

	public void readPatients() {
		// ReadWriteToFile read = new ReadWriteToFile();
		// patients = (PatientList) read.readFromSerialFile("patients.ser");
		DatabaseConnection db = new DatabaseConnection();
		try {
			db.makeConnection();
			List<List<String>> results = db.executeQueryForResults("select * from patient");
			for (int i = 0; i < results.size(); i++) {
//				int ID = Integer.parseInt(results.get(i).get(0));
				String fName = results.get(i).get(1);
				String lName = results.get(i).get(2);
				String address = results.get(i).get(3);
				int phone = Integer.parseInt(results.get(i).get(4));
				Patient p = new Patient(fName, lName, phone, address);
				patients.addPatient(p);
			}
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}
	}

	public List<List<String>> getPatientDetails(int id) {
		// ReadWriteToFile read = new ReadWriteToFile();
		// patients = (PatientList) read.readFromSerialFile("patients.ser");
		DatabaseConnection db = new DatabaseConnection();
		try {
			db.makeConnection();
			List<List<String>> results = db.executeQueryForResults("select * from patient where ID = " + id);
			db.closeConnection();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}
		return null;
	}

	public List<List<String>> getProcedureDetails(int id) {
		// ReadWriteToFile read = new ReadWriteToFile();
		// patients = (PatientList) read.readFromSerialFile("patients.ser");
		DatabaseConnection db = new DatabaseConnection();
		try {
			db.makeConnection();
			List<List<String>> results = db.executeQueryForResults("select * from procedures where ID = " + id);
			db.closeConnection();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}
		return null;
	}

	public void readProcedures() {
		// ReadWriteToFile read = new ReadWriteToFile();
		// procedures = (ProcedureList)
		// read.readFromSerialFile("procedures.ser");
		// ReadWriteToFile read = new ReadWriteToFile();
		// patients = (PatientList) read.readFromSerialFile("patients.ser");
		DatabaseConnection db = new DatabaseConnection();
		try {
			db.makeConnection();
			List<List<String>> results = db.executeQueryForResults("select * from procedures");
			for (int i = 0; i < results.size(); i++) {
				int ID = Integer.parseInt(results.get(i).get(0));
				String name = results.get(i).get(1);
				Double price = Double.parseDouble(results.get(i).get(2));
				Procedure p = new Procedure(ID, name, price);
				procedures.addProcedure(p);
			}
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}
	}

	public void readPayments() throws ParseException {
		// ReadWriteToFile read = new ReadWriteToFile();
		// procedures = (ProcedureList)
		// read.readFromSerialFile("procedures.ser");
		// ReadWriteToFile read = new ReadWriteToFile();
		// patients = (PatientList) read.readFromSerialFile("patients.ser");
		DatabaseConnection db = new DatabaseConnection();
		try {
			db.makeConnection();
			List<List<String>> results = db.executeQueryForResults("select * from payments");
			for (int i = 0; i < results.size(); i++) {
				int ID = Integer.parseInt(results.get(i).get(0));
				int invoice = Integer.parseInt(results.get(i).get(1));				
				Double price = Double.parseDouble(results.get(i).get(2));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				Payment p = new Payment(ID, invoice, price,sdf.parse(results.get(i).get(3)));
				payments.addPayment(p);
			}
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}
	}
	
	public List<List<String>> getPaymentDetails(int id) {
		// ReadWriteToFile read = new ReadWriteToFile();
		// patients = (PatientList) read.readFromSerialFile("patients.ser");
		DatabaseConnection db = new DatabaseConnection();
		try {
			db.makeConnection();
			List<List<String>> results = db.executeQueryForResults("select pt.first_name,pt.last_name,pt.address,pt.phone, i.amount, "
																	+ "i.invoiceNo, i.invoiceDate "
																	+ "from payments p "
																	+ "inner join invoices i "
																	+ "on i.ID = p.invoiceID "
																	+ "inner join patient pt on "
																	+ "pt.ID = i.patient "
																	+ "where p.ID = " + id);
			db.closeConnection();
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}
		return null;
	}

	public String getProceduresFromPayment(int id) {
		// ReadWriteToFile read = new ReadWriteToFile();
		// patients = (PatientList) read.readFromSerialFile("patients.ser");
		DatabaseConnection db = new DatabaseConnection();
		String output="";
		try {
			db.makeConnection();
			List<List<String>> results = db.executeQueryForResults("select pt.procedure_name, pt.price "
																	+ "from payments p "
																	+ "inner join invoices i "
																	+ "on i.ID = p.invoiceID "
																	+ "inner join invoice_details id "
																	+ "on i.ID = id.invoice_id "
																	+ "inner join procedures pt on "
																	+ "pt.ID = id.procedure_id "
																	+ "where p.ID = " + id);
			db.closeConnection();
			
			for (int i = 0; i < results.size(); i++) {
				output += "\tProcedure Name: "+results.get(i).get(0)+"\n";
				output += "\tProcedure Price: "+results.get(i).get(1)+"\n";
			}
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}
		return null;
	}

	public static void addPatient(Patient patient) {
		DatabaseConnection db = new DatabaseConnection();
		try {
			db.makeConnection();
			db.executeUpdate("INSERT INTO `patient` (`first_name`, `last_name`, `address`, `phone`) "
																+ "VALUES ('" + patient.getFName() + "','" 
																+ patient.getLName() + "','" + patient.getAddress() + "'," 
																+ patient.getPhone() + ")");
			db.closeConnection();
			patients.addPatient(patient);
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}
	}
	
	public List<List<String>> executeQuery(String query){
		DatabaseConnection db = new DatabaseConnection();
		List<List<String>> results = null;
		try {
			db.makeConnection();
			results = db.executeQueryForResults(query);
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection();
		}
		return results;
	}
	
	public PatientList getPatientList() {
		return Controller.patients;
	}

	public ProcedureList getProcedureList() {
		return this.procedures;
	}

	public PaymentList getPaymentList() {
		return this.payments;
	}
}
