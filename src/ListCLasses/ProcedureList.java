package ListCLasses;

import java.util.ArrayList;

import classes.Procedure;

public class ProcedureList{
	ArrayList<Procedure> procedures;
	
	public ProcedureList(){
		procedures = new ArrayList<Procedure>();
	}
	
	public ArrayList<Procedure> getProcedures() {
		return this.procedures;
	}

	public void addProcedure(Procedure p) {
		this.procedures.add(p);
	}

	public Procedure getProcedure(int index) {
		return this.procedures.get(index);
	}
}
