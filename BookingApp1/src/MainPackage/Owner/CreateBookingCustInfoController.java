package MainPackage.Owner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MainPackage.BookingSystem;
import Object.Customer;
import Object.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateBookingCustInfoController {

	@FXML
	public TextField customername;
	@FXML
	public TextField customernumber;
	@FXML
	public TextField customerid;
	@FXML
	public TextArea customeraddress;
	@FXML
	Button next;
	@FXML
	Label invalidcustname;
	@FXML
	Label invalidcustnumber;
	@FXML
	Label invalidcustaddress;
	@FXML
	Label invalidcustid;
	@FXML
	Label errorrequired;
	
	//This function will initialize when owner clicked on "Next" button.
	@FXML
	private void onNext() throws IOException {
		
		//Setting all Label to invisible
		invalidcustname.setVisible(false);
		invalidcustnumber.setVisible(false);
		invalidcustaddress.setVisible(false);
		errorrequired.setVisible(false);
		invalidcustid.setVisible(false);
		
		//Error check all text field, making sure it's not empty.
		if(customername.getText().equals("")) {
			invalidcustname.setVisible(true);
		}
		else {
			invalidcustname.setVisible(false);
		}
		if(customernumber.getText().equals("")) {
			invalidcustnumber.setVisible(true);
		}
		else {
			invalidcustnumber.setVisible(false);
		}
		if(customeraddress.getText().equals("")) {
			invalidcustaddress.setVisible(true);
		}
		else {
			invalidcustaddress.setVisible(false);
		}
		//This case happened when owner uses a customer ID and Name to skip other text field inputs.
		//It's for customer who registered to the system but call-in to the owner for booking assistance. 
		if(!customerid.getText().equals("")) {
			if(!customername.getText().equals("")) {
				//Does a database check, making sure they have registered.
				//Case 0 is when there is no match with the database, therefore error message will appear.
				if(databasecheckID(customerid.getText(), customername.getText()) == 0) {
					invalidcustname.setVisible(true);
					invalidcustid.setVisible(true);
					invalidcustnumber.setVisible(false);
					invalidcustaddress.setVisible(false);
					customernumber.setText("");
					customeraddress.setText("");
				}
				//Case 1 is when there is a complete match of data with the database, the data will then be 
				//stored in an array list for future reference purpose.
				//It will automatically linked to the next official booking page. 
				else if(databasecheckID(customerid.getText(), customername.getText()) == 1) {
					invalidcustname.setVisible(false);
					invalidcustid.setVisible(false);
					Person.storeIDName(customerid.getText(), customername.getText());
					BookingSystem.showCreateBookingDetails();
				}
				//Case 2 is when the input customer ID is valid & exist in the database but the given name is
				//invalid, therefore, error message will appear too.
				else if(databasecheckID(customerid.getText(), customername.getText()) == 2) {
					invalidcustname.setVisible(true);
					invalidcustid.setVisible(false);
					invalidcustnumber.setVisible(false);
					invalidcustaddress.setVisible(false);
					customernumber.setText("");
					customeraddress.setText("");
				}
			}
			//This is when the owner entered a customer ID but without the name, it will prompt an error required
			//message.
			else if(customername.getText().equals("")) {
				invalidcustname.setVisible(false);
				invalidcustnumber.setVisible(false);
				invalidcustaddress.setVisible(false);
				errorrequired.setVisible(true);
			}
		}
		//This case is when a normal/non-registered customer calls in to the owner for booking an 
		//appointment.
		else if(customerid.getText().equals("") && !customername.getText().equals("") && !customernumber.getText().equals("") && !customeraddress.getText().equals("")) {
			invalidcustid.setVisible(false);
			//Error check all input customer data, making sure they are valid.
			Person nC = new Customer();
			
			nC.setName(customername.getText());
			if(nC.getName() == null) {			
				invalidcustname.setVisible(true);
			} else {
				invalidcustname.setVisible(false);
			}
			nC.setNumber(customernumber.getText());
			if(nC.getNumber() == null){
				invalidcustnumber.setVisible(true);
			} else {
				invalidcustnumber.setVisible(false);
			}
			nC.setAddress(customeraddress.getText());
			if(nC.getAddress() == null){
				invalidcustaddress.setVisible(true);
			} else {
				invalidcustaddress.setVisible(false);
			}
			//If there is no error, it will then be stored in an array list for future reference purpose.
			//The page will then linked to the next official booking page.
			if(!(nC.getName() == null) && !(nC.getNumber() == null) && !(nC.getAddress() == null)){
				Person.storeNameNumAddress(customername.getText(), customernumber.getText(), customeraddress.getText());
				customername.setText("");
				customernumber.setText("");
				customeraddress.setText("");
				BookingSystem.showCreateBookingDetails();
			}	
		}
	}
	//This function main purpose is to error check the input customer ID and name with the
	//business database
	private int databasecheckID(String ID, String name) {
		
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet1 = null;
		int i = 0; 
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();	
			
			resultSet1 = statement.executeQuery("SELECT custid, name FROM customer");
			while(resultSet1.next()) {
				if(ID.equals(resultSet1.getString("custid"))){
					if(name.equals(resultSet1.getString("name"))) {
						i = 1;
					}
					else if(!name.equals(resultSet1.getString("name"))) {
						i = 2;
					}
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e1) { }
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e1) { }
		}
		return i;
	}
}
