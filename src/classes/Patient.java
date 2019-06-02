package classes;

public class Patient{
	
	private static int ID;
	private int patientID;
	private String fName, lName;	
	private int phone;
	private String address;
		
		public Patient(String fName, String lName, int phone, String address){
			this.patientID = ID + 1;
			this.fName = fName;
			this.lName = lName;
			this.phone = phone;
			this.address = address;
			ID++;
			
		}
		
		public int getID(){return this.patientID;}
		
		public String getFName(){
			return this.fName;
		}
		
		public String getLName(){
			return this.lName;
		}
			
		public int getPhone(){
			return this.phone;
		}
		
		public String getAddress(){
			return this.address;
		}		
		
		public String toString(){
			return "Patient ID : "+getID()+"\nPatient Name : " + getFName()+" " +getLName() + "\nAddress : "+getAddress()+"\nPhone Number : "+getPhone();
		}
}
