package classes;

import java.util.Date;

public class Payment{
	
	private int ID;
	int invoiceID;
	private double paymentAmt;
	private Date paymentDate;
	
	public Payment(int id, int invoice, double paymentAmt, Date paymentDate){
			this.ID = id;
			this.invoiceID = invoice;
			this.paymentAmt = paymentAmt;
			this.paymentDate = paymentDate;
		}
		
		public Date getPaymentDate(){
			return this.paymentDate;
		}

		public int getID(){return this.ID;}
		public int getInvoiceID(){return this.invoiceID;}
		public double getPaymentAmt (){
			return this.paymentAmt;
		}
		
		public void print(){
			System.out.println(toString());
		}
		
		public String toString(){
			return "Date: " + paymentDate + "\nTo the Amount of " + paymentAmt;
			
		}
}

