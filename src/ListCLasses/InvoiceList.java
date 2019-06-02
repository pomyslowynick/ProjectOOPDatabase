package ListCLasses;

import java.util.ArrayList;

import classes.Invoice;

public class InvoiceList {
    ArrayList<Invoice> invoices;
	
	public InvoiceList(){
		invoices = new ArrayList<Invoice>();
	}
	
	public ArrayList<Invoice> getInvoices() {
		return this.invoices;
	}

	public void addInvoice(Invoice i) {
		this.invoices.add(i);
	}

	public Invoice getinvoice(int index) {
		return this.invoices.get(index);
	}
}
