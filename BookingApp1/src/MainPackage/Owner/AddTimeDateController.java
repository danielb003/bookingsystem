package MainPackage.Owner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import MainPackage.BookingSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class AddTimeDateController {
	
	ArrayList<String[]> employees = new ArrayList<String[]>();

	ObservableList<String> empList = FXCollections.observableArrayList();
	Connection con = null;
	Statement statement = null;
	
	@FXML
	public ComboBox<String> employee = new ComboBox<String>(empList);
	@FXML
	RadioButton monday;
	@FXML
	RadioButton tuesday;
	@FXML
	RadioButton wednesday;
	@FXML
	RadioButton thursday;
	@FXML
	RadioButton friday;
	@FXML
	RadioButton saturday;
	@FXML
	RadioButton first;
	@FXML
	RadioButton second;
	@FXML
	RadioButton third;
	@FXML
	RadioButton fourth;
	@FXML
	Button confirmation;
	@FXML
	Label erroremp;
	@FXML
	Label success;
	@FXML
	Label errorday;
	@FXML
	Label errortime;
	@FXML
	Label errordatabase;
	@FXML
	ToggleGroup Day;
	@FXML
	ToggleGroup time;
	
	@FXML
	private void initialize() {
		{
			try {
				con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			
				ResultSet empSet =  con.createStatement().executeQuery("SELECT name , empid FROM employee WHERE ownerID = '"+ BookingSystem.companyLogin + "'");
				while(empSet.next()) {
					empList.add(empSet.getString("name"));
					employee.setItems(empList);
					String[] empData = {empSet.getString("name"),empSet.getString("empid")};
					employees.add(empData);
				}
				con.close();
			} catch (SQLException e1) {
				BookingSystem.log.error(e1.toString());
				e1.printStackTrace();
			} 
		}
	}
	public String getEmployeeID(String b){
		for(String[] x: employees){
			if(x[0].equals(b)){
				return x[1];
			}
		}
		return null;
	}
	@FXML
	public void onConfirmation() {
		
		success.setVisible(false);
		String empID = getEmployeeID(employee.getSelectionModel().getSelectedItem());
		String empday = HandleDayOptions(monday, tuesday, wednesday, thursday, friday, saturday);
		String emptime = HandleTimeOptions(first, second, third, fourth);
		
		if(employee.getSelectionModel().getSelectedItem() == null) {
			erroremp.setVisible(true);
		}
		else {
			erroremp.setVisible(false);
		}
		if(empday == null) {
			errorday.setVisible(true);
		} 
		else {
			errorday.setVisible(false);
		}
		if(emptime == null) {
			errortime.setVisible(true);
		}
		else {
			errortime.setVisible(false);
		}
		if(empID != null && empday != null && emptime != null) {
			if(databasecheck(empID, empday, emptime) != null) {
				errordatabase.setVisible(false);
				Connection con = null;
				
				{
					try {
						con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
						
						
						con.createStatement().executeUpdate("INSERT INTO workingTimeDate(`EmployeeID`, `Day`, `Time`, `ownerID`) VALUES ('" + empID + "','" + empday + "','" + emptime + "','" + BookingSystem.companyLogin + "')");
						success.setVisible(true);
						employee.getSelectionModel().clearSelection();
						Day.selectToggle(null);
						time.selectToggle(null);
						con.close();
					} catch (Exception e) {
						BookingSystem.log.error(e.toString());
							System.err.println(e);
					}
				}
			}else{
				BookingSystem.log.info("adding working time failed");
				errordatabase.setVisible(true);
			}
		}
	}
	private String HandleDayOptions(RadioButton monday, RadioButton tuesday, RadioButton wednesday, RadioButton thursday, RadioButton friday, RadioButton saturday) {
		
		if(monday.isSelected()) {
			return ("Monday");
		}
		if(tuesday.isSelected()) {
			return ("Tuesday");
		}
		if(wednesday.isSelected()) {
			return ("Wednesday");
		}
		if(thursday.isSelected()) {
			return ("Thursday");
		}
		if(friday.isSelected()) {
			return ("Friday");
		}
		if(saturday.isSelected()) {
			return ("Saturday");
		}
		else {
			BookingSystem.log.error("selected day failed");
			return null;
		}
	}
	private String HandleTimeOptions(RadioButton first, RadioButton second, RadioButton third, RadioButton fourth) {
		
		if(first.isSelected()) {
			return ("8am-10am");
		}
		if(second.isSelected()) {
			return ("10am-12pm");
		}
		if(third.isSelected()) {
			return ("1pm-3pm");
		}
		if(fourth.isSelected()) {
			return ("3pm-5pm");
		}
		else {
			BookingSystem.log.error("selected time failed");
			return null;
		}
	}
	private String databasecheck(String empID, String empday, String emptime) {
	
		Connection con = null;
		
		
		
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			
			
			ResultSet resultSet1 = con.createStatement().executeQuery("SELECT * FROM workingTimeDate Where EmployeeID = '"+empID+"'");
			
			
			while(resultSet1.next()) {
					if(empday.equals(resultSet1.getString("Day"))) {
						if(emptime.equals(resultSet1.getString("Time"))) {
							BookingSystem.log.info("already working at that time");
							return null;
						}
					}					
				
			}
			con.close();
		} catch (SQLException e){
			BookingSystem.log.error(e.toString());
			e.printStackTrace();
		} 
		return empID;
	}
}
