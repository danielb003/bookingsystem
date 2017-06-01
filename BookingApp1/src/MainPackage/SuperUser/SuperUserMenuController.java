package MainPackage.SuperUser;

import java.io.IOException;

import MainPackage.BookingSystem;
import javafx.fxml.FXML;

public class SuperUserMenuController {

	@FXML
	private void goLogout() throws IOException {
		BookingSystem.showLogin3();
	}
	@FXML
	private void goAddOwner() throws IOException {
		BookingSystem.showAddOwner();
	}
}
