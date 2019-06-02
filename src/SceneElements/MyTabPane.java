package SceneElements;

import javafx.scene.control.TabPane;

public class MyTabPane extends TabPane{
	//private ArrayList<Patient> patients;

	public MyTabPane(){
		
		ProceduresTab proc = new ProceduresTab();
		PatientsTab pat = new PatientsTab();
		PaymentsTab pay = new PaymentsTab();
		ReportsTab rpt = new ReportsTab();
        
        this.getTabs().add(pat);   
        this.getTabs().add(proc);  
        this.getTabs().add(pay);  
        this.getTabs().add(rpt);   
        
	}
	
}
		
