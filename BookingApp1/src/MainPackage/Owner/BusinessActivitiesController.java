package MainPackage.Owner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MainPackage.BookingSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BusinessActivitiesController {
	
	//Combo box list for duration
	//Pre-set duration for owner to choose
	ObservableList<String> durationList = FXCollections.observableArrayList("30mins", "60mins", "90mins", "120mins");
	
	@FXML
	private ComboBox<String> duration = new ComboBox<String>(durationList);
	@FXML
	TextField ADDservices;
	@FXML 
	Button confirmed;
	@FXML
	Label success;
	@FXML
	Label errorservices;
	@FXML
	Label errorduration;
	@FXML
	Label errordatabase;
	
	//When the page loads from Owner Menu, it will automatically initialize the Duration
	//drop down list
	@FXML
	private void initialize() {
		duration.setItems(durationList);
	}
	//The main purpose of this function is to add the owner's choice of new business activities into
	//the database
	@FXML
	private void addNewData() {
		
		errordatabase.setVisible(false);
		success.setVisible(false);
		String duration2 = duration.getSelectionModel().getSelectedItem();
		
		//Checks if the text field or drop down list is null value or not
		if(ADDservices.getText().equals("")) {
			errorservices.setVisible(true);
		}
		else{
			errorservices.setVisible(false);
		}
		if(duration2 == null) { 
			errorduration.setVisible(true);
		}
		else{
			errorduration.setVisible(false);
		}
		//If all field is not null, it will error check with the database and enter the new data into it
		if(!ADDservices.getText().equals("") && ADDservices.getText().matches("[a-zA-Z ]{2,30}") && duration2 != null){
			if(databasecheck(ADDservices.getText()) == 0) {
				errordatabase.setVisible(false);
				Connection con = null;
				Statement statement = null;
				{
					try {
						con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
						statement = con.createStatement();
		
						statement.executeUpdate("INSERT INTO BusinessActivities (`Services`, `Duration`, `ownerID`) " + "VALUES ('" + ADDservices.getText() + "','" + duration2 + "','" + BookingSystem.companyLogin + "')");
						success.setVisible(true);
						ADDservices.setText("");
						duration.getSelectionModel().clearSelection();
					} catch (Exception e) {
						System.err.println(e);
					} finally {
						if(statement != null) {
							try {
								statement.close();
							} catch (SQLException e) { }
						}
					}
					if(con != null) {
						try {
							con.close();
						} catch (SQLException e) { }
					}
				}
			}
			else if(databasecheck(ADDservices.getText()) == 1){
				errordatabase.setVisible(true);
			}
		}
		else if(!ADDservices.getText().matches("[a-zA-Z ]{2,30}")) {
			errordatabase.setVisible(false);
			errorservices.setVisible(true);
		}
	}
	//The main purpose of this function is to prevent duplicating of data in the database
	private int databasecheck(String service) {
		
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet1 = null;
		int i = 0;
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();	
			
			resultSet1 = statement.executeQuery("SELECT Services FROM BusinessActivities");
			while(resultSet1.next()) {
				if(service.equals(resultSet1.getString("Services"))){
					i = 1;
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
