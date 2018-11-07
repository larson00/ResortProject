package sample.EventMenu;

/**
 * EventMenuHomeController.java
 * This controllers allows user to see the myEvents made by Manager
 * the lsit view shows Events created by the manager
 * Guests can also create their event, but they arent public to all users
 * This controlls has a lsitView, and a combobox for sorting by type (This can be used in other controllers
 *
 *
 */


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import sample.MyEvent;
import sample.MyEvent.EventCellFactory;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Meeting;
import sample.Spa;
import sample.Wedding;

public class EventMenuHomeController implements Initializable {

  ArrayList<MyEvent> myEvents = new ArrayList<>();
  static ArrayList<MyEvent> sortList = new ArrayList<>();

  @FXML
  Button buttonCreate, buttonSort,buttonResetList,moreInfo;
  @FXML
  ListView<MyEvent> listViewEvent = new ListView<>();
  @FXML
  ListProperty<MyEvent> listProperty = new SimpleListProperty<>();
  ListProperty<MyEvent> listProperty2 = new SimpleListProperty<>();
  @FXML
  ComboBox<MyEvent> comboBoxSort;
  @FXML void handleComboSort (ActionEvent event){
    MyEvent choseMyEvent = comboBoxSort.getValue();
    showOnlyType(myEvents, choseMyEvent.getClass());
    listProperty.set(FXCollections.observableArrayList(sortList));
    listViewEvent.setCellFactory(new EventCellFactory()); //Cell Factory allows formatting
  }
@FXML
  void handleInfo(ActionEvent event){
    System.out.println(Global.myEvents.get(0).getNameOfOrg());

  }

  @FXML
  void handleCreate(ActionEvent event) {
    Global.currentScene = buttonCreate.getScene();//

    new Global().openNewWindow(WindowLocation.EVENTCREATE);



  }
  @FXML
  void handleRestoreList(ActionEvent event){

    listProperty.set(FXCollections.observableArrayList(myEvents));
    listViewEvent.setCellFactory(new EventCellFactory()); //Cell Factory allows formatting


  }

  @FXML
  static ArrayList<MyEvent> showOnlyType(List<?> list, Class<? extends MyEvent> c){
    sortList.clear();
    for (Object o : list){
      if (c.isInstance(o)) { //Check if Object's class is a
        //Class Matches
        System.out.println(o.toString());
       sortList.add((MyEvent) o);


      }


    }
    return sortList;

  }

  @FXML
  void handleSort(ActionEvent event){
    /**
     * May delete this method
     */
    //listViewEvent.itemsProperty().bind(listProperty);
    sortList= showOnlyType(myEvents,Spa.class); //alls showOnlyType so it only shows objects of Spa class

    listProperty.set(FXCollections.observableArrayList(sortList));
    listViewEvent.setCellFactory(new EventCellFactory()); //Cell Factory allows formatting

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (myEvents.isEmpty()) {
      /**
       * This is called first before the window is shown
       * use this to initalize class variables so they are equal to global
       *
       */
      //No myEvents make a default list
      MyEvent createMyEvent = new MyEvent("Royal Ball");
      myEvents.add(createMyEvent);
      createMyEvent = new MyEvent("ZombieFest");
      myEvents.add(createMyEvent);
      createMyEvent = new MyEvent("Magic Kingdom!");
      myEvents.add(createMyEvent);
      myEvents.add(new Spa("Spa event"));
      myEvents.add(new Wedding("Wedding event"));
      myEvents.add(new Meeting("Meeting event"));
      myEvents.add(new Spa("Spa event 2"));

      listViewEvent.itemsProperty().bind(listProperty); //Bind a list property to listview
      listProperty.set(FXCollections.observableArrayList(myEvents)); //Set the elements in the list
      listViewEvent.setCellFactory(new EventCellFactory()); //Cell Factory allows formatting
      ArrayList<MyEvent> listofElements = new ArrayList<>();
      listofElements.add(new Spa("ShowSpa"));
      listofElements.add(new Meeting("ShowMeeting"));
      listofElements.add(new Wedding("showweeding"));
     // comboBoxSort.setItems(listProperty2);
      comboBoxSort.itemsProperty().bind(listProperty2); //Bind a list property to the combobox
      listProperty2.set(FXCollections.observableArrayList(listofElements)); //Add a list to the combobox
      comboBoxSort.setCellFactory(new EventCellFactory());/// allows cells to be formatted

    }
    //listViewEvent.setItems(buttonCreate);
  }
}
