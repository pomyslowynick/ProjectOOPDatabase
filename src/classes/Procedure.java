package classes;

public class Procedure{

	int procNo;
	double price;
	String procName;

	public Procedure(int procNo, String procName, double price) {
		setProcNo(procNo);
		setProcName(procName);
		setPrice(price);
	}

	public void setPrice(double p) {
		this.price = p;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}
	
	public void setProcNo(int p) {
		this.procNo = p;
	}

	public double getPrice() {
		return this.price;
	}
	
	public String getProcName() {
		return this.procName;
	}

	public int getProcNo() {
		return this.procNo;
	}

	public String toString(){
		return "Procedure Number : "+getProcNo()+"\nProcedure Name : "+getProcName()+"\nProcedure Price : €"+getPrice();
	}
}
