package sample;

import java.util.Date;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * MyEvent.java
 * Events class store informations of the Events of the Resort
 * Events can be created by the Owner or the guest
 * Guest created Events have to be Approved by the owner.
 * Eventt is an abstract Class, it's subclasses are what extend from it
 * THey include: Spa, Meeting, Wedding, other.
 *
 */

public  class MyEvent {
  String name;
  int numberOfGuests;
  Boolean didManagerCreate; //Check if Manager created sample.MyEvent ornot
  Guest guestCreator; //If Guest created it, then the Guest object is stored here
  String Venue; //Maybe Make Enum
  //Add more Varaibles
  Date date;
  // 5 String fields used in EventCreateController
  String otherDetails;
  String selectVendor;
  String numOfPeople;
  String nameOfOrg;
  String nameOfEvent;
  //Getters and setters for the fields

  public String getOtherDetails() {
    return otherDetails;
  }

  public void setOtherDetails(String otherDetails) {
    this.otherDetails = otherDetails;
  }

  public String getSelectVendor() {
    return selectVendor;
  }

  public void setSelectVendor(String selectVendor) {
    this.selectVendor = selectVendor;
  }

  public String getNumOfPeople() {
    return numOfPeople;
  }

  public void setNumOfPeople(String numOfPeople) {
    this.numOfPeople = numOfPeople;
  }

  public String getNameOfOrg() {
    return nameOfOrg;
  }

  public void setNameOfOrg(String nameOfOrg) {
    this.nameOfOrg = nameOfOrg;
  }

  public String getNameOfEvent() {
    return nameOfEvent;
  }

  public void setNameOfEvent(String nameOfEvent) {
    this.nameOfEvent = nameOfEvent;
  }

  public String getName() {
    return name;
  }

  public static class EventCellFactory implements Callback<ListView<MyEvent>, ListCell<MyEvent>> {

    @Override
    public ListCell<MyEvent> call(ListView<MyEvent> listview) {
      return new EventCell();
    }
  }

  public MyEvent() {

  }


  public MyEvent(String name) {
    this.name = name;
  }
  // Constructor for 5 things used in EventCreateController

  public MyEvent(String numOfPeople, String nameOfOrg, String nameOfEvent,
      String otherDetails, String selectVendor) {
    this.selectVendor = selectVendor;
    this.otherDetails = otherDetails;
    this.numOfPeople = numOfPeople;
    this.nameOfOrg = nameOfOrg;
    this.nameOfEvent = nameOfEvent;
  }
}


class EventCell  extends ListCell<MyEvent>
{
  @Override
  public void updateItem(MyEvent item, boolean empty)
  {
    super.updateItem(item, empty);

    int index = this.getIndex();
    String name = null;

    // Format name
    if (item == null || empty)
    {
    }
    else
    {
      name = (index + 1) + ". " +
          item.getName() + " ";
    }

    this.setText(name);
    setGraphic(null);
  }
}


