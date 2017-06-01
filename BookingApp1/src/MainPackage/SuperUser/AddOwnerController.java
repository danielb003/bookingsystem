package MainPackage.SuperUser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MainPackage.BookingSystem;
import Object.Owner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddOwnerController {

	@FXML
	TextField businessname;
	@FXML
	TextField ownerid;
	@FXML
	TextField ownername;
	@FXML
	TextField ownerpassword;
	@FXML
	TextField ownernumber;
	@FXML
	TextArea owneraddress;
	@FXML
	Label invalidownerid;
	@FXML
	Label invalidownername;
	@FXML
	Label invalidownerpassword;
	@FXML
	Label invalidownernumber;
	@FXML
	Label invalidowneraddress;
	@FXML
	Label invalidbusinessname;
	@FXML
	Label iddatabase;
	@FXML
	Label bnamedatabase;
	@FXML
	Label addsuccess;
	@FXML
	Button submit;
	
	//This function will be initialized when user clicks on Submit button
	public void onSubmitOwner(ActionEvent event) throws IOException{
		
		//Getting all input from available text field
		addsuccess.setVisible(false);
		String bname = businessname.getText();
		String id = ownerid.getText();
		String name = ownername.getText();
		String password = ownerpassword.getText();
		String number = ownernumber.getText();
		String address = owneraddress.getText();
		
		//Create a new Owner type
		Owner nO = new Owner();
		
		//Input validate business name
		nO.setBusinessName(bname);
		if(nO.getBusinessName() == null) {				
			invalidbusinessname.setVisible(true);
		} else {
			invalidbusinessname.setVisible(false);
		}
		//Input validate owner ID
		nO.setID(id);
		if(nO.getID() == null) {				
			invalidownerid.setVisible(true);
		} else {
			invalidownerid.setVisible(false);
		}
		//Input validate owner Name
		nO.setName(name);
		if(nO.getName() == null) {			
			invalidownername.setVisible(true);
		} else {
			invalidownername.setVisible(false);
		}
		//Input validate owner Password
		nO.setPassword(password);
		if(nO.getPassword() == null){
			invalidownerpassword.setVisible(true);
		} else {
			invalidownerpassword.setVisible(false);
		}
		//Input validate owner Address
		nO.setAddress(address);
		if(nO.getAddress() == null){
			invalidowneraddress.setVisible(true);
		} else {
			invalidowneraddress.setVisible(false);
		}
		//Input validate owner Mobile Number
		nO.setNumber(number);
		if(nO.getNumber() == null){
			invalidownernumber.setVisible(true);
		} else {
			invalidownernumber.setVisible(false);
		}
		//If all input is valid, it stores the new customer data into the database
		if(!(nO.getID() == null) && !(nO.getName() == null) && !(nO.getPassword() == null) && !(nO.getNumber() == null)
				&& !(nO.getBusinessName() == null) && !(nO.getAddress() == null)) {
			id = "o" + nO.getID();
			//If there are no similar data exist in the database, it will then insert the new data into it.
			if(databasecheck(id, bname) == 0) {
				iddatabase.setVisible(false);
				bnamedatabase.setVisible(false);
				nO.addOwner(nO);
				addsuccess.setVisible(true);
				businessname.setText("");
				ownerid.setText("");
				ownername.setText("");
				ownerpassword.setText("");
				ownernumber.setText("");
				owneraddress.setText("");
			}
			//If the input owner ID already exists, it will pops an error message.
			else if(databasecheck(id, bname) == 1) {
				iddatabase.setVisible(true);
				bnamedatabase.setVisible(false);
			}
			//If the business name already exists, it will pops an error message too.
			else if(databasecheck(id, bname) == 2) {
				iddatabase.setVisible(false);
				bnamedatabase.setVisible(true);
			}
		}
	}
	//This function is just getting the input ID and business name data and comparing it with the existing database
	//data, in order to prevent duplicate data.
	public int databasecheck(String ID, String bname) {
		
		Connection con = null;
		Statement statement = null;
		int i = 0;
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();	
			ResultSet ownerSet = statement.executeQuery("SELECT ownid FROM owner");
			while(ownerSet.next()) {
				if(ID.equals(ownerSet.getString("ownid"))) {
					i = 1;
				}
			}
			ResultSet ownerSet1 = statement.executeQuery("SELECT businessname FROM owner");	
			while(ownerSet1.next()) {
				if(bname.equals(ownerSet.getString("businessname"))) {
					i = 2;
				}
			}
		}catch (SQLException e1) {
			BookingSystem.log.error(e1);
			e1.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e1) {BookingSystem.log.error(e1); }
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e1) {BookingSystem.log.error(e1); }
		}		
		return i;
	}
}
