/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.Action;
import javax.swing.text.html.ImageView;

/**
 * FXML Controller class
 *
 * @author ggraber7402
 */
public class GuestMenuController implements Initializable {
  private ArrayList<String> usernameList;
  private ArrayList<String> passwordList;
  private ArrayList<Guest> guestList;
  private Guest currentGuest;
  private Room roomClickedOn;
  private int daysStaying;
  private boolean initializedRooms=false;

  @FXML
  public void storeVariables(ArrayList<String> unLIST, ArrayList<String> pwList,
      ArrayList<Guest> gList, Guest g1,List<Room> rooms){
    System.out.println("HERE FIRST?");
    this.usernameList = unLIST;
    this.passwordList = pwList;
    this.guestList = gList;
    this.currentGuest = g1;
    this.rooms = rooms;
    int i=0;
    for (String word :usernameList){
      System.out.println("Name = " + word);
      System.out.println(passwordList.get(i));
      i++;
    }



  }
  @FXML
  private Label labelTotalPriceShow,paymentSucces,labelPaymentSuccess;

  @FXML
  private Button buttonConfirmPayment;

  @FXML
  private TabPane tabPane;
@FXML
private Tab tabPayment;
    @FXML
    private Label labelAvailable;
  @FXML
  private Label labelPricePerDay;
  @FXML
  private TextField textFieldDaysStaying;
//  @FXML
//  private ImageView imageViewRoom;


    @FXML
    private Button signoutButton;
    @FXML
    private Button bookRoombutton;
//    @FXML
//    private ListView roomListView;
//    @FXML
//    protected List<String> rooms = new ArrayList<>();
//    @FXML
//    protected ListProperty<String> listProperty = new SimpleListProperty<>();
@FXML
private ListView roomListView;



  @FXML
  protected List<Room> rooms = new ArrayList<>();
  @FXML
  protected ListProperty<Room> listProperty = new SimpleListProperty<>();



    @FXML
    public void handleBookRoom(ActionEvent event){
      Boolean success= true;
      tabPane.getSelectionModel().select(tabPayment);
      try{
        daysStaying =Integer.parseInt(textFieldDaysStaying.getText());
      }catch (NumberFormatException exception){
        labelTotalPriceShow.setText("Error, Invalid Input");
        buttonConfirmPayment.setDisable(true);
        success= false;
      }
      if (success==true){
        buttonConfirmPayment.setDisable(false);
        System.out.println("here");
        labelTotalPriceShow.setText(Double.toString(roomClickedOn.getPrice() * daysStaying));
      }





  }


    @FXML
    public void handleSignout(ActionEvent event){
//        try {
//            Stage stage = (Stage) signoutButton.getScene().getWindow();
//            stage.close();
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            stage = new Stage();
//            stage.setScene(new Scene(root1));
//            stage.show();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
      Stage stageExit = (Stage) signoutButton.getScene().getWindow();
            stageExit.close();
      FXMLLoader Loader = new FXMLLoader();
      Loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
      try {
        Loader.load();
      }catch ( IOException ex){
        Logger.getLogger(GuestMenuController.class.getName()).log(Level.SEVERE, null ,ex);

      }
      //  DisplayTextController display = Loader.getController(); //Calling DisplayTextcontroller file
      // display.setText(name_Text,email_Text); //using displaytextcontroller's method
      FXMLDocumentController storeFields =Loader.getController();
      storeFields.storeVariables1(usernameList,passwordList,guestList,rooms);


      Parent p = Loader.getRoot();
      Stage stage = new Stage();
      stage.setScene(new Scene(p));
      stage.show();



    }

    GuestMenuController(ArrayList<String> unLIST, ArrayList<String> pwList,
        ArrayList<Guest> gList, Guest g1,List<Room> rooms){
      /**
       * Happens after initialize class
       */
      System.out.println("In Constructor");
      this.usernameList = unLIST;
      this.passwordList = pwList;
      this.guestList = gList;
      this.currentGuest = g1;
      this.rooms = rooms;


    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      System.out.println("HERE IN INTAILIZE");

      if (rooms.isEmpty())
      {
        System.out.println("ROOM OS EMPTY \n\n\n");
        rooms.add(new Room("1A",false,200));
        rooms.get(0).setAvailable(false);
        rooms.get(0).setOccupiedGuest(new Guest("BruceWayne","batman"));//John Doe occupies room 1A
        rooms.add(new Room("Room 2A",true,300));
        rooms.get(1).setAvailable(true);
        rooms.add(new Room("Room 3A",false,500));
        rooms.get(2).setAvailable(false);
        rooms.get(2).setOccupiedGuest(new Guest("ClarkKent","superman"));//John Doe occupies room 1A
      }


      roomListView.itemsProperty().bind(listProperty);

      listProperty.set(FXCollections.observableArrayList(rooms));
      roomListView.setCellFactory(new RoomCellFactory());
      roomListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
          String roomSelected = roomListView.getSelectionModel().getSelectedItem().toString();
          //System.out.println("To string:"+roomSelected.toString());

          for (Room r: rooms
          ) {
            if (r.toString().equalsIgnoreCase(roomSelected)){
              roomClickedOn= r;
              System.out.println("Room: "+roomClickedOn.getName());
              //Same toString same, set currentRoom to r

            }
           // System.out.println(r.getName());
          }
          System.out.println("Here IS : "+roomSelected);
         // System.out.println("clicked on " + roomListView.getSelectionModel().getSelectedItem());

          //Check Room Avability.
          displayRoomFeatures(roomClickedOn);

        }

      });

    }

  private void displayRoomFeatures(Room roomClickedOn) {
      if (roomClickedOn.getAvailable()){
        //true
        labelAvailable.setText("Available");
        bookRoombutton.setDisable(false);

      }else
      {
        labelAvailable.setText("Not Available");
        bookRoombutton.setDisable(true);

      }
      labelPricePerDay.setText(Double.toString(roomClickedOn.getPrice()));



  }

  public void handleConfirmPayment(ActionEvent event) {
    labelPaymentSuccess.setText("Payment Success");
    roomClickedOn.setAvailable(false);
    roomClickedOn.setOccupiedGuest(currentGuest);
    System.out.println("Room:"+roomClickedOn.getName() +" Availe:"+roomClickedOn.getAvailable() + " guest: "+
        roomClickedOn.getOccupiedGuest().getUserName());

  }
}
