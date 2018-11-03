package sample.GuestMenu;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Global;
import sample.Global.WindowLocation;
import sample.LoginMenu.LoginMenuController;

/**
 *
 * GuestMenuHomeController.java
 * This is the controller for GuestMenuHome.fxml
 * Guest Menu home is the frist screen the Guest sees when they login
 * From here they can: View Rooms,View Events, See Calender, Chhange Settings, Exit
 *
 */

public class GuestMenuHomeController {
@FXML
Button bookRoomButton;

  @FXML
  public void handleBookRoom(ActionEvent event){

    /**
     * Opening Guest controller.
     *First you exit this current Stage
     * Then you load a new controller object
     * Get the url from Global.windowLocation.
     * Set the controller.
     * Open the new window
     *
     */
    Stage stageExit = (Stage) bookRoomButton.getScene().getWindow(); //Select an object in this window
    stageExit.close(); //close current window
    GuestMenuController guestController = new GuestMenuController(); // Create object of new Controller


    //Loads FXML Loader
    FXMLLoader Loader = new FXMLLoader();
    Loader.setLocation(getClass().getResource(WindowLocation.GUESTMENUROOM.getLocation())); //Call new window

    try {
      Loader.setController(guestController); //Set Controller as guestMenuRoom doesn't have one linked to fxml.
      Loader.load(); //Loads
    }catch ( IOException ex){
      Logger.getLogger(GuestMenuController.class.getName()).log(Level.SEVERE, null ,ex);

    }
    //  DisplayTextController display = Loader.getController(); //Calling DisplayTextcontroller file
    // display.setText(name_Text,email_Text); //using displaytextcontroller's method
    //LoginMenuController storeFields =Loader.getController();
//    storeFields.storeVariables1(); //This method will stoer the variables


    Parent p = Loader.getRoot();
    Stage stage = new Stage();
    stage.setTitle("View Rooms -"+ Global.currentGuestLoggedIn.getUserName()); //set title
    stage.setScene(new Scene(p));
    stage.show();//Open new window

  }


}
