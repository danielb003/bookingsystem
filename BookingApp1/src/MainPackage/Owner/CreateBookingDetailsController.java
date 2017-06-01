package MainPackage.Owner;

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

public class CreateBookingDetailsController {

	ObservableList<String> serviceList = FXCollections.observableArrayList();
	ObservableList<String> timeList = FXCollections.observableArrayList();
	ObservableList<String> empList = FXCollections.observableArrayList();
	
	
	private String selectedEmployee = null;
	private String selectedService = null;
	private String selectedTime = null;
	private LocalDate selectedDate = null;
	private String selectedDay = null;
	
	
	
	@FXML
	private DatePicker date;
	@FXML
	public ComboBox<String> service = new ComboBox<String>(serviceList);
	@FXML
	public ComboBox<String> time = new ComboBox<String>(timeList);
	@FXML
	public ComboBox<String> employee = new ComboBox<String>(empList);
	@FXML
	Label invaliddate;
	@FXML
	Label invalidservice;
	@FXML
	Label invalidtime;
	@FXML
	Label invalidemployee;
	@FXML
	Label success;
	
	//This function is to initialize the Services section whenever the Booking page is called in.
	@FXML
	private void initialize() {
		
		date.setDayCellFactory(dayCellFactory);
		Connection con = null;
		Statement statement = null;
		
			try {
				con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
				statement = con.createStatement();
				ResultSet serviceSet = statement.executeQuery("SELECT Services , duration FROM BusinessActivities Where ownerid = '"+BookingSystem.companyLogin+"'");
				while(serviceSet.next()) {
					serviceList.add(serviceSet.getString("Services"));
				}
				service.setItems(serviceList);
				con.close();
			} catch (SQLException e1) {
				BookingSystem.log.error(e1.toString());
				e1.printStackTrace();
			}
		
	}
	
	//This function main idea is that whenever the owner changes the date, it will launch the Time section
	//according to the chosen date.
	@FXML
	public void launchTime() {
		timeList.clear();
		BookingSystem.log.info("loading avaliable times");
		
		//empList.clear();
		//employee.setItems(empList);
		selectedDate = date.getValue();
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
				time.setItems(timeList);
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
		
		
		selectedDate = date.getValue();
		if(selectedDate != null){
			selectedDay = selectedDate.getDayOfWeek().toString();
		}
		
		selectedTime = time.getSelectionModel().getSelectedItem();
		
		Connection con = null;
		Statement statement = null;
		
			try {
				con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
				statement = con.createStatement();
				ResultSet empSet = statement.executeQuery("SELECT Time, Day, employee.name FROM workingTimeDate Inner Join employee on workingTimeDate.EmployeeID = employee.empid Where ownerid = '"+BookingSystem.companyLogin+"'");
				while(empSet.next()) {
					
					if(selectedDate != null && selectedTime != null){
						if((selectedDay.equalsIgnoreCase(empSet.getString("Day")) && selectedTime.equals(empSet.getString("Time")))){
							empList.add(empSet.getString("name"));
							
						}
					}
				}
				ObservableList<String> newempList = removeEmployee(empList);
				employee.setItems(newempList);
				con.close();
			} catch (SQLException e1) {
				BookingSystem.log.error(e1.toString());
				e1.printStackTrace();
			}
	}
	
private ObservableList<String> removeEmployee(ObservableList<String> empList) {
		
		Connection con = null;
		String selectedDate = "";
		String timef = "";
		if(date.getValue() != null && time.getValue() != null){
			selectedDate = date.getValue().toString();
			timef = time.getValue();
		}
		System.out.println(timef+"  "+selectedDate);
		try {
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			ResultSet rs = con.createStatement().executeQuery("SELECT newbooking.empID, employee.name FROM newbooking Inner Join employee on newbooking.empID = employee.empid WHERE Date ='" + selectedDate + "' AND startTime = '"+timef+"'");
				
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
		success.setVisible(false);
		invaliddate.setVisible(false);
		invalidservice.setVisible(false);
		invalidtime.setVisible(false);
		invalidemployee.setVisible(false);
		//Get value from all menu choice
		selectedDate = date.getValue();
		selectedService = service.getSelectionModel().getSelectedItem();
		selectedEmployee = employee.getSelectionModel().getSelectedItem();
		selectedTime = time.getSelectionModel().getSelectedItem();
		try{
			Connection con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			
			ResultSet result1 = con.createStatement().executeQuery("select empid from employee where employee.name = '"+selectedEmployee+"'");
			selectedEmployee = result1.getString("empid");
			
			result1.close();
			con.close();
		}catch(Exception e){
			BookingSystem.log.error(e);
		}
		try{
			Connection con2 = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			
			ResultSet result2 = con2.createStatement().executeQuery("select servicesID from BusinessActivities where BusinessActivities.Services = '"+selectedService+"'");
			BookingSystem.log.info(result2.getInt("servicesID")+"");
			selectedService = result2.getInt("servicesID")+"";
			
			result2.close();
			con2.close();
			
			
		}catch(Exception e){
			BookingSystem.log.error(e);
		}
		
		
		//Error validate to check whether does all menu choice has a value in them
		if(selectedDate == null) {
			invaliddate.setVisible(true);
		}
		else {
			invaliddate.setVisible(false);
		}
		if(selectedService == null) {
			invalidservice.setVisible(true);
		}
		else {
			invalidservice.setVisible(false);
		}
		if(selectedTime == null) {
			invalidtime.setVisible(true);
		}
		else {
			invalidtime.setVisible(false);
		}
		if(selectedEmployee == null) {	
			invalidemployee.setVisible(true);
		}
		else {
			invalidemployee.setVisible(false);
		}
		//When all menu choice has a value
		if(selectedDate != null && selectedService != null && selectedTime != null && selectedEmployee != null) {
			//The system will check which function in person does not have an empty data
			if(Person.retrieveIDName() == null) {
				if(Person.retrieveNameNumAddress() != null) {
					//New string array to convert the incoming string array from other function under Person class
					//from Object folder
					String[] customer = Person.retrieveNameNumAddress();
					//Extract all data from the array
					String name = customer[0];
					String number = customer[1];
					String address = customer[2];
					//Validation check the contact number with the database
					//If there is no similar contact number
					if(databasecheckMobNo(number) == 0) {
						Connection con = null;
						Statement statement = null;
						{
							//Create connection with the business database
							try {
								con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
								statement = con.createStatement();
								//SQL query to insert all data into their respective table
								statement.executeUpdate("INSERT INTO customer(`name`, `address`, `number`, `ownerID`) VALUES ('" + name + "','" + address + "','" + number + "','" + BookingSystem.companyLogin + "')");
								statement.executeUpdate("INSERT INTO newbooking(`date`, `startTime`, `customerNumber`, `empID`, `servicesID`, `ownerID`) VALUES ('" + selectedDate + "','" + selectedTime + "','" + number +  "','" + selectedEmployee + "','" + selectedService + "','" + BookingSystem.companyLogin + "')");
								//If insert success, it will print out a success label and clear all menu choices
								success.setVisible(true);
								date.setValue(null);
								service.getSelectionModel().clearSelection();
								time.getSelectionModel().clearSelection();
								employee.getSelectionModel().clearSelection();
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
					//If there is a similar contact number exists in the customer table
					else if(databasecheckMobNo(number) == 1){
						Connection con = null;
						Statement statement = null;
						{
							//Create connection with the business database
							try {
								con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
								statement = con.createStatement();
								//SQL query to insert the data only into the booking table
								statement.executeUpdate("INSERT INTO newbooking(`date`, `startTime`, `customerNumber`, `empID`, `servicesID`, `ownerID`) VALUES ('" + selectedDate + "','" + selectedTime + "','" + number +  "','" + selectedEmployee + "','" + selectedService + "','" + BookingSystem.companyLogin + "')");
								//If insert success, it will print out a success label and clear all menu choices
								success.setVisible(true);
								date.setValue(null);
								service.getSelectionModel().clearSelection();
								time.getSelectionModel().clearSelection();
								employee.getSelectionModel().clearSelection();
						
							} catch (Exception e) {
								BookingSystem.log.error(e.toString());
									System.err.println(e);
							} finally {
								if(statement != null) {
									try {
										statement.close();
									} catch (SQLException e) { BookingSystem.log.error(e.toString());}
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
			}
			//This section is when owner uses the customer ID and Name field to do the booking
			else if(Person.retrieveIDName() != null) {
				if(Person.retrieveNameNumAddress() == null) {
					//A new string is created to get the value from another function under Person class from Object folder
					String ID = Person.retrieveIDName();
					Connection con = null;
					Statement statement = null;
					{
						//Create connection with the business database
						try {
							con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
							statement = con.createStatement();
							//SQL statement to get the mobile number that matches the customer ID input by owner at the 
							//beginning of the booking
							ResultSet custSet = statement.executeQuery("SELECT custid, number FROM customer");
							while(custSet.next()) {
								if(ID.equals(custSet.getString("custid"))) {
									String custnumber = custSet.getString("number");
									//SQL statement to insert all data into the booking table
									statement.executeUpdate("INSERT INTO newbooking(`date`, `startTime`, `customerNumber`, `empID`, `servicesID`, `ownerID`) VALUES ('" + selectedDate + "','" + selectedTime + "','" + custnumber +  "','" + selectedEmployee + "','" + selectedService + "','" + BookingSystem.companyLogin + "')");
									//If insert success, it will print out a success label and clear all menu choices
									success.setVisible(true);
									date.setValue(null);
									service.getSelectionModel().clearSelection();
									time.getSelectionModel().clearSelection();
									employee.getSelectionModel().clearSelection();
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
		}
	}
	
	//This function is created to Duplicate check the input Mobile Number with the business
	//database customer table number field, if the number exists already, it will return 1, else 0.
	private int databasecheckMobNo(String number) {
		
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet1 = null;
		int i = 0; 
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();	
			
			resultSet1 = statement.executeQuery("SELECT number FROM customer");
			while(resultSet1.next()) {
				if(number.equals(resultSet1.getString("number"))){
						i = 1;
				}
			}
		} catch (SQLException e){
			BookingSystem.log.error(e.toString());
			e.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e1) {BookingSystem.log.error(e1.toString()); }
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e1) {BookingSystem.log.error(e1.toString()); }
		}
		return i;
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