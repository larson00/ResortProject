package sample;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Global {

  /**
   * Arraylists can be declared by arraylist list = Global.arraylist and Global will change as list changes
   * However variables such as Ints and Strings wont' be changed by simialar code
   * So you will have to a method like UpdateGlobals() which will update this class's variables to be used in a new window
   *
   * Global.java
   * This class stores all our data, that is shared by all the controllers.
   * By using this we won't have to use a method to pass fields through each controller.
   * However it doesn't follow effective OOP's practice.
   *
   * Since all the fields are static, they change with the class, so changes are global.
   * However this is bad for testing if the program is too large and you don't knwo whos chaning global
   * (However we shouldn't have this problem)
   *
   * Global also holds enum for WindowLocation will stores url for the FXML files.
   * It makes it easier to get the url just incase the files change location.
   *
   * Expect to be large file.
   */

  static public ArrayList<String> usernameList = new ArrayList<>(); //ArrayList of username Fields
  static public ArrayList<String> passwordList = new ArrayList<>();//Array:ist of password Field
  static public ArrayList<Guest> guestList = new ArrayList<>();//Arraylist of Guests
  static public List<Room> rooms = new ArrayList<>();//Arraylist of rooms that Manager/Guest Menu's use
  static  public Guest currentGuestLoggedIn; //LoginMenuController keeps track of guest to send to GuestMenu
  static  public ObservableList<Employee> data= FXCollections.observableArrayList(); // Arraylist of Employees for MaanagerMenu
  static  public Manager admin;//Not used

  /**
   * URl Locations for quick reference
   */
 public  enum WindowLocation {

    LOGINMENU(""),
    SIGNUP(""),
    MANAGERMENU(""),
    GUESTMENUHOME("/sample/GuestMenu/GuestMenuHome.fxml"),
    GUESTMENUROOM("/sample/GuestMenu/GuestMenu.fxml"),

    ;
    private String url;



    WindowLocation(String urlL) {
      url = urlL;
    }

    public String getLocation(){

      return url;
    }



  }


}
