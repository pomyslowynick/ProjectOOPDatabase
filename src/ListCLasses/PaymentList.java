package ListCLasses;

import java.util.ArrayList;

import classes.Payment;

public class PaymentList{
	ArrayList<Payment> payments;

	public PaymentList() {//creating the list
		payments = new ArrayList<Payment>();
	}

	public ArrayList<Payment> getPayment() {
		return this.payments;
	}//getting the data from patient class

	public void addPayment(Payment p) {
		this.payments.add(p);//adding that patient to the list
	}

	public Payment getPayment(int index) {
		return this.payments.get(index);
	}
}

