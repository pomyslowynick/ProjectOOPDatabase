package classes;

import java.util.Date;

public class Invoice {

	private int ID;
	private double invoiceAmt;	
	private int invoiceNo;
	private Date invoiceDate;
	//private makes sure nobody can access them
		
		public Invoice(int id, double invoiceAmt, int invoiceNo, Date invoiceDate){
			this.ID = id;
			this.invoiceAmt = invoiceAmt;
			this.invoiceNo = invoiceNo;
			this.invoiceDate = invoiceDate;
			
		}//constructor
		
		public int getID(){return this.ID;}
		
		public double getInAmt(){
			return this.invoiceAmt;
		}
		
		public int getInNo(){
			return this.invoiceNo;
		}
			
		public Date getInDate(){
			return this.invoiceDate;
		}
		
		

}
