package ListCLasses;

import java.util.ArrayList;

import classes.Patient;

public class PatientList {
	ArrayList<Patient> patients;

	public PatientList() {//creating the list
		patients = new ArrayList<Patient>();
	}

	public ArrayList<Patient> getPatients() {
		return this.patients;
	}//getting the data from patient class

	public void addPatient(Patient p) {
		this.patients.add(p);//adding that patient to the list
	}

	public Patient getPatient(int index) {
		return this.patients.get(index);
	}
}
