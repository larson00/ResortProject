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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

  @FXML
  public void storeVariables(ArrayList<String> unLIST, ArrayList<String> pwList,
      ArrayList<Guest> gList, Guest g1){
    this.usernameList = unLIST;
    this.passwordList = pwList;
    this.guestList = gList;
    this.currentGuest = g1;
    int i=0;
    for (String word :usernameList){
      System.out.println("Name = " + word);
      System.out.println(passwordList.get(i));
      i++;
    }



  }


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
      System.out.println("Hiiiii");


  }

  @FXML
  public void handleRoomClick(MouseEvent event){
    System.out.println("Click Room");

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
      storeFields.storeVariables1(usernameList,passwordList,guestList);


      Parent p = Loader.getRoot();
      Stage stage = new Stage();
      stage.setScene(new Scene(p));
      stage.show();



    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      System.out.println("HERE IN INTAILIZE");
      rooms.add(new Room("1A"));
      rooms.add(new Room("Room 2A"));
      rooms.add(new Room("Room 3A"));
      roomListView.itemsProperty().bind(listProperty);

      listProperty.set(FXCollections.observableArrayList(rooms));
      roomListView.setCellFactory(new RoomCellFactory());
      roomListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
          String roomSelected = roomListView.getSelectionModel().getSelectedItem().toString();
          System.out.println("Here IS : "+roomSelected);
          System.out.println("clicked on " + roomListView.getSelectionModel().getSelectedItem());
        }

      });

    }    
    
}
