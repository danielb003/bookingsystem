package MainPackage.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;

import MainPackage.BookingSystem;
import Object.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Callback;

public class CustCreateBookingDetailsController {

	ObservableList<String> serviceList = FXCollections.observableArrayList();
	ObservableList<String> timeList = FXCollections.observableArrayList();
	ObservableList<String> empList = FXCollections.observableArrayList();
	
	
	private String selectedEmployee = null;
	private String selectedService = null;
	private String selectedTime = null;
	private LocalDate selectedDate = null;
	private String selectedDay = null;
	
	
	
	@FXML
	private DatePicker date2;
	@FXML
	public ComboBox<String> service2 = new ComboBox<String>(serviceList);
	@FXML
	public ComboBox<String> time2 = new ComboBox<String>(timeList);
	@FXML
	public ComboBox<String> employee2 = new ComboBox<String>(empList);
	@FXML
	Label invaliddate2;
	@FXML
	Label invalidservice2;
	@FXML
	Label invalidtime2;
	@FXML
	Label invalidemployee2;
	@FXML
	Label success2;
	
	//This function is to initialize the Services section whenever the Booking page is called in.
	@FXML
	private void initialize() {
		
		date2.setDayCellFactory(dayCellFactory);
		Connection con = null;
		Statement statement = null;
		
			try {
				con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
				statement = con.createStatement();
				ResultSet serviceSet = statement.executeQuery("SELECT Services , duration FROM BusinessActivities Where ownerid = '"+BookingSystem.companyLogin+"'");
				while(serviceSet.next()) {
					serviceList.add(serviceSet.getString("Services"));
				}
				service2.setItems(serviceList);
				con.close();
			} catch (SQLException e1) {
				BookingSystem.log.error(e1.toString());
				e1.printStackTrace();
			}
			
		
	}
	
	//This function main idea is that whenever the customer changes the date, it will launch the Time section
	//according to the chosen date.
	@FXML
	public void launchTime() {
		timeList.clear();
		BookingSystem.log.info("loading avaliable times");
		
		//empList.clear();
		//employee.setItems(empList);
		selectedDate = date2.getValue();
		if(selectedDate != null){
			selectedDay = selectedDate.getDayOfWeek().toString();
		}
		
		Connection con = null;
		Statement statement = null;
		
			try {
				con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
				statement = con.createStatement();
				ResultSet timeSet = statement.executeQuery("SELECT Time FROM workingTimeDate WHERE Day LIKE '"+selectedDay+"' AND ownerid = '"+BookingSystem.companyLogin+"'");
				while(timeSet.next()) {
					timeList.add(timeSet.getString("Time"));
				}
				time2.setItems(timeList);
				con.close();
			} catch (SQLException e1) {
				BookingSystem.log.error(e1.toString());
				e1.printStackTrace();
			}
	}
		  
	
	
	//This function is to launch the Employee section according to the time chosen at the Time section.
	@FXML
	public void launchEmployee() {
		BookingSystem.log.info("loading avaliable employees");
		empList.clear();
		
		
		selectedDate = date2.getValue();
		if(selectedDate != null){
			selectedDay = selectedDate.getDayOfWeek().toString();
		}
		
		selectedTime = time2.getSelectionModel().getSelectedItem();
		
		Connection con = null;
		Statement statement = null;
		
			try {
				con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
				statement = con.createStatement();
				ResultSet empSet = statement.executeQuery("SELECT Time, Day, employee.name FROM workingTimeDate Inner Join employee on workingTimeDate.EmployeeID = employee.empid Where workingTimeDate.ownerID = '"+BookingSystem.companyLogin+"'");
				while(empSet.next()) {
					
					if(selectedDate != null && selectedTime != null){
						if((selectedDay.equalsIgnoreCase(empSet.getString("Day")) && selectedTime.equals(empSet.getString("Time")))){
							empList.add(empSet.getString("name"));	
						}
					}
				}

				ObservableList<String> newempList = removeEmployee(empList);
				employee2.setItems(newempList);
				con.close();
			} catch (SQLException e1) {
				BookingSystem.log.error(e1.toString());
				e1.printStackTrace();
			}
	}
	
	//This function will remove the employees that are booked for a specific time slot.a
	private ObservableList<String> removeEmployee(ObservableList<String> empList) {
		
		Connection con = null;
		String selectedDate = "";
		String time = "";
		if(date2.getValue() != null && time2.getValue() != null){
			selectedDate = date2.getValue().toString();
			time = time2.getValue();
		}
		System.out.println(time+"  "+selectedDate);
		try {
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			ResultSet rs = con.createStatement().executeQuery("SELECT newbooking.empID, employee.name FROM newbooking Inner Join employee on newbooking.empID = employee.empid WHERE Date ='" + selectedDate + "' AND startTime = '"+time+"'");
				
			while(rs.next()) {
				empList.remove(rs.getString("name"));
			}
			con.close();
		}catch (SQLException e1) {
			BookingSystem.log.error(e1.toString());
			e1.printStackTrace();
		} 
		return empList;
	}
	
	//This function will initiate when the user clicks on the Submit button.
	@FXML
	private void onSubmit() {
		
		//All labels will be set to invisible
		success2.setVisible(false);
		invaliddate2.setVisible(false);
		invalidservice2.setVisible(false);
		invalidtime2.setVisible(false);
		invalidemployee2.setVisible(false);
		//Get value from all menu choice
		selectedDate = date2.getValue();
		selectedService = service2.getSelectionModel().getSelectedItem();
		selectedEmployee = employee2.getSelectionModel().getSelectedItem();
		selectedTime = time2.getSelectionModel().getSelectedItem();
		try{
			Connection con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			
			ResultSet result1 = con.createStatement().executeQuery("select empid from employee where employee.name = '"+selectedEmployee+"'");
			selectedEmployee = result1.getString("empid");
			
			result1.close();
			con.close();
		}catch(Exception e){
			BookingSystem.log.error(e.toString());
		}
		try{
			Connection con2 = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			
			ResultSet result2 = con2.createStatement().executeQuery("select servicesID from BusinessActivities where BusinessActivities.Services = '"+selectedService+"'");
			BookingSystem.log.info(result2.getInt("servicesID")+"");
			selectedService = result2.getInt("servicesID")+"";
			
			result2.close();
			con2.close();
			
			
		}catch(Exception e){
			BookingSystem.log.error(e.toString());
		}
		
		
		//Error validate to check whether does all menu choice has a value in them
		if(selectedDate == null) {
			invaliddate2.setVisible(true);
		}
		else {
			invaliddate2.setVisible(false);
		}
		if(selectedService == null) {
			invalidservice2.setVisible(true);
		}
		else {
			invalidservice2.setVisible(false);
		}
		if(selectedTime == null) {
			invalidtime2.setVisible(true);
		}
		else {
			invalidtime2.setVisible(false);
		}
		if(selectedEmployee == null) {	
			invalidemployee2.setVisible(true);
		}
		else {
			invalidemployee2.setVisible(false);
		}
		//When all menu choice has a value
		if(selectedDate != null && selectedService != null && selectedTime != null && selectedEmployee != null) {
			//A new string is created to get the value from another function under Person class from Object folder
			String ID = Person.retrieveID();
			Connection con = null;
			Statement statement = null;
			{
				//Create connection with the business database
				try {
					con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
					statement = con.createStatement();
					//SQL statement to get the mobile number that matches the logged in customer ID at the 
					//beginning of the booking
					ResultSet custSet = statement.executeQuery("SELECT custid, number FROM customer");
					while(custSet.next()) {
						if(ID.equals(custSet.getString("custid"))) {
							String custnumber = custSet.getString("number");
							//SQL statement to insert all data into the booking table
							statement.executeUpdate("INSERT INTO newbooking(`date`, `startTime`, `customerNumber`, `empID`, `servicesID`,`ownerID`,`status`) VALUES ('" + selectedDate + "','" + selectedTime + "','" + custnumber +  "','" + selectedEmployee + "','" + selectedService + "','"+BookingSystem.companyLogin+"','"+"w"+"')");
							//If insert success, it will print out a success label and clear all menu choices
							success2.setVisible(true);
							date2.setValue(null);
							service2.getSelectionModel().clearSelection();
							time2.getSelectionModel().clearSelection();
							employee2.getSelectionModel().clearSelection();
						}
					}
				} catch (Exception e) {
					BookingSystem.log.error(e.toString());
						System.err.println(e);
				} finally {
					if(statement != null) {
						try {
							statement.close();
						} catch (SQLException e) {BookingSystem.log.error(e.toString()); }
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {BookingSystem.log.error(e.toString()); }
				}
			}
		}
	}
	//This section is to initialize the Date Picker to disable all past dates from today onwards.
	Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
	    public DateCell call(final DatePicker datePicker) {
	
	       return new DateCell() {
	            @Override
	            public void updateItem(LocalDate item, boolean empty) {
	
	                // Must call super
	                super.updateItem(item, empty);
	              
	                // Show Weekends in blue color
	                LocalDate date = LocalDate.from(item);
	                if(date.isBefore(LocalDate.now())) {
	                	 setDisable(true);
	                     setStyle("-fx-background-color: #EEEEEE;");
	                }
	                DayOfWeek day = DayOfWeek.from(item);
	                if(day == DayOfWeek.SUNDAY) {
	                	 setDisable(true);
	                     setStyle("-fx-background-color: #EEEEEE;");
	                }
	            }
	        };
	    }
	};
}
