package MainPackage.Owner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MainPackage.BookingSystem;
import Object.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddEmployeeController {
	
	@SuppressWarnings("unused")
	private BookingSystem main;
	@FXML
	TextField EMPIDinput;
	@FXML
	TextField EMPNAMEinput;
	@FXML
	TextField EMPADDRSinput;
	@FXML
	TextField EMPMOBNOinput;
	@FXML
	Button confirm;
	@FXML
	Label invalidID;
	@FXML
	Label invalidName;
	@FXML
	Label invalidMobNo;
	@FXML
	Label invalidAddress;
	@FXML
	Label Success;
	@FXML
	Button Continue;
	@FXML
	Label iddatabase;
	
	public void onConfirmaton(ActionEvent event) throws IOException {
				
		Success.setVisible(false);
		String id = EMPIDinput.getText();
		String name = EMPNAMEinput.getText();
		String mobno = EMPMOBNOinput.getText();
		String address = EMPADDRSinput.getText();
		
		Employee nE = new Employee();
		
		//Input validate customer ID
		nE.setID(id);
		if(nE.getID() == null) {				
			invalidID.setVisible(true);
		} 
		else if(!id.matches("[0-9]{3}")) {
			invalidID.setVisible(true);	
		}else {
			invalidID.setVisible(false);
		}
		//Input validate customer Name
		nE.setName(name);
		if(nE.getName() == null) {			
			invalidName.setVisible(true);
		} else {
			invalidName.setVisible(false);
		}
		//Input validate customer Mobile Number
		nE.setNumber(mobno);
		if(nE.getNumber() == null){
			invalidMobNo.setVisible(true);
		} else {
			invalidMobNo.setVisible(false);
		}
		//Input validate customer Address
		nE.setAddress(address);
		if(nE.getAddress() == null){
			invalidAddress.setVisible(true);
		} else {
			invalidAddress.setVisible(false);
		}

		//If all input is valid, it stores the new employee data into the database
		if(!(nE.getID() == null) && !(nE.getName() == null) && !(nE.getNumber() == null)
				&& !(nE.getAddress() == null)){
			id = "e" + nE.getID(); 
			if(databasecheck(id) == 0) {
				iddatabase.setVisible(false);
				nE.addEmployee(nE);
				Success.setVisible(true);
				EMPIDinput.setText("");
				EMPNAMEinput.setText("");
				EMPMOBNOinput.setText("");
				EMPADDRSinput.setText("");
			}
			else if(databasecheck(id) == 1) {
				iddatabase.setVisible(true);
			}
		}
	}
	public int databasecheck(String ID) {
		
		Connection con = null;
		Statement statement = null;
		int i = 0;
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();	
			ResultSet employeeSet = statement.executeQuery("SELECT empid FROM employee");		
			while(employeeSet.next()) {
				if(ID.equals(employeeSet.getString("empid"))) {
					i = 1;
				}
			}
			while(employeeSet.next()) {
				if(!ID.equals(employeeSet.getString("empid"))) {
					i = 0;
				}
			}
		}catch (SQLException e1) {
			BookingSystem.log.error(e1.toString());
			e1.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e1) { BookingSystem.log.error(e1.toString());}
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e1) {BookingSystem.log.error(e1.toString()); }
		}		
		return i;
	}
}
