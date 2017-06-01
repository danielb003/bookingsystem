package MainPackage;

import java.io.IOException;

import org.apache.log4j.Logger;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class BookingSystem extends Application {
	public static String companyLogin = "";
	private static Stage primaryStage;
	private static Stage registerstage;
	private static Stage loginStage;
	private static BorderPane mainlayout;
	private static AnchorPane startinglayout;
	
	public final static Logger log = Logger.getLogger(BookingSystem.class);
	
	public static String companyname = "";
	public static Image companylogo = null;
	@Override
	public void start(Stage primaryStage) throws IOException {
		BookingSystem.loginStage = primaryStage;
		BookingSystem.loginStage.setTitle("Booking App");
		showLogin();
	}
	public static void showLogin() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(BookingSystem.class.getResource("Login/Login.fxml"));
		startinglayout = loader.load();
		Scene scene = new Scene(startinglayout);
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Varela+Round");
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Gruppo");
		loginStage.setScene(scene);
		loginStage.show();
	}
	public static void showLogin2() throws IOException {
		registerstage.close();
		FXMLLoader loader = new FXMLLoader(BookingSystem.class.getResource("Login/Login.fxml"));
		startinglayout = loader.load();
		Scene scene = new Scene(startinglayout);
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Varela+Round");
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Gruppo");
		loginStage.setScene(scene);
		loginStage.show();
	}
	public static void showLogin3() throws IOException {
		primaryStage.close();
		FXMLLoader loader = new FXMLLoader(BookingSystem.class.getResource("Login/Login.fxml"));
		startinglayout = loader.load();
		Scene scene = new Scene(startinglayout);
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Varela+Round");
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Gruppo");
		loginStage.setScene(scene);
		loginStage.show();
	}
	
	public static void showRegisterMenu() throws IOException {
		loginStage.close();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Register/Register.fxml"));
		startinglayout = loader.load();
		
		registerstage = new Stage();
		registerstage.setTitle("Booking App");
		registerstage.initOwner(primaryStage);
		Scene scene = new Scene(startinglayout);
		registerstage.setScene(scene);
		registerstage.show();
	}
	public static void showOwnerMenu() throws IOException {
		loginStage.close();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Owner/OwnerMenu.fxml"));
		mainlayout = loader.load();
		
		primaryStage = new Stage();
		primaryStage.setTitle("Owner Menu");
		primaryStage.initOwner(loginStage);
		Scene scene = new Scene(mainlayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void showAddEmployee() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Owner/AddEmployee.fxml"));
		BorderPane AddEmployee = loader.load();
		mainlayout.setCenter(AddEmployee);
	}
	public static void showAddTimeDate() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Owner/AddTimeDate.fxml"));
		BorderPane AddTimeDate = loader.load();
		mainlayout.setCenter(AddTimeDate);
	}
	public static void showWorkingTimes() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Owner/WorkingTimes.fxml"));
		BorderPane WorkingTimes = loader.load();
		mainlayout.setCenter(WorkingTimes);
	}
	public static void showBusinessActivities() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Owner/BusinessActivities.fxml"));
		BorderPane BusinessActivities = loader.load();
		mainlayout.setCenter(BusinessActivities);
	}
	public static void showBookingSummaryOwner() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Owner/FullBookingSummaryOwner.fxml"));
		BorderPane WeeklySummary = loader.load();
		mainlayout.setCenter(WeeklySummary);
	}
	public static void showBookingHistoryCustomer() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Customer/FullBookingSummaryCustomer.fxml"));
		BorderPane WeeklySummary = loader.load();
		mainlayout.setCenter(WeeklySummary);
	}
	public static void showCreateBookingCustInfo() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Owner/CreateBookingCustInfo.fxml"));
		BorderPane CreateBookingCustInfo = loader.load();
		mainlayout.setCenter(CreateBookingCustInfo);
	}
	public static void showCreateBookingDetails() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Owner/CreateBookingDetails.fxml"));
		BorderPane CreateBookingDetails = loader.load();
		mainlayout.setCenter(CreateBookingDetails);
	}
	public static void showSettings() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Owner/Settings.fxml"));
		BorderPane Settings = loader.load();
		mainlayout.setCenter(Settings);
	}
	public static void showCustomerMenu() throws IOException {
		loginStage.close();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Customer/CustomerMenu.fxml"));
		mainlayout = loader.load();
		
		primaryStage = new Stage();
		primaryStage.setTitle("Customer Menu");
		primaryStage.initOwner(loginStage);
		Scene scene = new Scene(mainlayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void showCustCreateBookingDetails() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("Customer/CustCreateBookingDetails.fxml"));
		BorderPane CustCreateBookingDetails = loader.load();
		mainlayout.setCenter(CustCreateBookingDetails);
	}
	//Function to display Super User Menu
	public static void showSuperUserMenu() throws IOException {
		loginStage.close();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("SuperUser/SuperUserMenu.fxml"));
		mainlayout = loader.load();
		
		primaryStage = new Stage();
		primaryStage.setTitle("SuperUser Menu");
		primaryStage.initOwner(loginStage);
		Scene scene = new Scene(mainlayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void showAddOwner() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(BookingSystem.class.getResource("SuperUser/AddOwner.fxml"));
		BorderPane Settings = loader.load();
		mainlayout.setCenter(Settings);
	}
	public static void main(String[] args) {
		log.info("starting aplication");
		launch(args);
	}
}
