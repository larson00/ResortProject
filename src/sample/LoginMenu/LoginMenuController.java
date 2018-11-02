/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.LoginMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Controller;
import sample.Employee;
import sample.Global;
import sample.Global.WindowLocation;
import sample.Guest;
import sample.GuestMenu.GuestMenuController;
import sample.GuestMenu.GuestMenuHomeController;
import sample.ManagerMenu.ManagerMenuController;
import sample.Room;

/**
 * Notes
 * Chck the text file of fxml files, they will sometimes have redtext/eerors which IDEA IDE will not tell you
 * Make sure there no java.awt as it will case Errors
 *
 *
 * LoginMenuController.java
 * Notes by:pPetit
 * This is bascially the "main" of the project.
 * This houses the login screen: Guest Login and Maanager Login
 * To login to guest, you  have to enter a Username and Password and press Create Button
 * That will invoke a method that creates a guest class, with the inputs the user did
 * Then Guest can login, Usernames arent case senesitves but passwords are
 *
 * For Manager: The default Login in U: Nicolo P: Martina
 * Same case sentivity,
 * For extra functionality, you can show/hide passwords.
 *
 */


/**
 *
 * @author ggrab
 */

public class LoginMenuController extends Controller implements Initializable {
   // private ArrayList<String> usernameList = new ArrayList<>(); //ArrayList of username Fields
   private ArrayList<String> usernameList;
  // private ArrayList<String> usernameList =super.getUsernameList();
  public List<Room> rooms;
  private ArrayList<String> passwordList;
  private ArrayList<Guest> guestList;
  private Guest currentGuest;
  private Room roomClickedOn;
  private int daysStaying;
  private boolean initializedRooms=false;
  private ObservableList<Employee> data;



  enum UserType{ //not used
        GUEST,MANAGER

    }

    private void updateGlobal(){
      Global.currentGuestLoggedIn = currentGuest;
    }

@FXML
private CheckBox checkBox; //CheckBo for handling showPassword

    @FXML
  public void storeVariables1(){
      /**
       * To send and store variables across windows.
       * Other Controllers will call this method and send their lists, so the whole programs are sharing
       * the same data.
       */
      //

//    this.usernameList = Global.usernameList;
//    this.passwordList = Global.passwordList;
//    this.guestList = Global.guestList;
//    this.rooms = Global.rooms;
//    this.data = Global.data;
    int i=0;
    for (String word :usernameList){ //Show UserList to check if correct data is stored
      System.out.println("Name = " + word);
      System.out.println(passwordList.get(i));
      i++;
    }
      for (Room r: rooms) {

        System.out.println("Room:" +r.getName()
            + " Price "+ r.getPrice()
            + " Avail: "+r.getAvailable()
        //    + " Guest Name: "+r.getOccupiedGuest().getUserName()
        );
        // System.out.println(r.getName());
        if (r.getOccupiedGuest() ==null)
        {
          System.out.println("NULL GUEST");
        }else{
          System.out.println("Guest name"+r.getOccupiedGuest().getUserName());
        }
      }

    }


    public  void openGuestMenu(Guest g1) throws IOException {
//        try {
//            Stage stage = (Stage) buttonCreate.getScene().getWindow();
//            stage.close();
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GuestMenu.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//             stage = new Stage();
//            stage.setScene(new Scene(root1));
//            stage.show();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
      /**
       * Simple Process
       * First Close the Window
       * Create A object of the new Controller Class
       * store Data,arraylists in that controllerclass's constructor
       * Then Open the fxml File
       * When the new window is opened the data is stored.
       *
       */
      System.out.println();
      currentGuest = g1;
      updateGlobal();
      Stage stage = (Stage) buttonCreate.getScene().getWindow(); //Asks a object in the window to store it's WindowID
            stage.close(); //Close current Window

    //Loads FXML Loader
      FXMLLoader Loader = new FXMLLoader();
      //Using Global's Enum named WindowLocation get the Url for the EnumType
      String url = WindowLocation.GUESTMENUHOME.getLocation();
      //load the url you just acquired.
      Loader.setLocation(getClass().getResource(url));
      try {
        // Loader.setController(guestController);
        Loader.load(); //Loads
      }catch ( IOException ex){
        Logger.getLogger(GuestMenuController.class.getName()).log(Level.SEVERE, null ,ex);

      }

      Parent p = Loader.getRoot();
       stage = new Stage();
      stage.setTitle("Guest Menu: "+g1.getUserName());
      stage.setScene(new Scene(p));
      stage.show(); //Opens new Window




    }


    void checkLogin(String usernameSent, String passwordSent)
    {
      /**
       * Login Button calls this method
       * This will check if the user inputted username and password match one  of the arraylist Record
       * Will give back different errors in the console
       * If successful than a guest is chosen and that Guest will be altered in the GuestMenu Window
       *
       */
        // usernameSent= usernameSent.toLowerCase();
        //passwordSent= passwordSent.toLowerCase();
        boolean isNameCorrect=false;
        boolean isPasswordCorrect=false;
        for (Guest g1: getGuestList()) {

            if(g1.getUserName().equalsIgnoreCase(usernameSent)){
                //they match!
                isNameCorrect= true;
                //now check password
                if (g1.getPassword().equals(passwordSent)){
                    try {

                        openGuestMenu(g1); //Open Guest Menu, send g1
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Successful!
                    System.out.println("Login Succesful");
                    isPasswordCorrect= true;
                }


            }


        }
        if (isNameCorrect == false && isPasswordCorrect == false){
            System.out.println("Error, login credentials not found");
        }else if (isNameCorrect==true && isPasswordCorrect== false){
            System.out.println("Error wrong password.");
        }else {
            System.out.println("Success");
        }


    }


    public ArrayList<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(ArrayList<Guest> guestList) {
        this.guestList = guestList;
    }

    public ArrayList<String> getUsernameList() {
        return usernameList;
    }

    public ArrayList<String> getPasswordList() {
        return passwordList;
    }

    public void setUsernameList(ArrayList<String> usernameList) {
        this.usernameList = usernameList;
    }

    public void setPasswordList(ArrayList<String> passwordList) {
        this.passwordList = passwordList;
    }

    public void createGuest(String n, String pw){
        Guest createGuest = new Guest(n,pw); //Calls method to create new Guest option nothing special

        getUsernameList().add(n);// No need for method this already adds it in
        getPasswordList().add(pw);
        getGuestList().add(createGuest);
        //System.out.println(getUsernameList().get(0));
        // setUsernameList(getUsernameList());

//    Guest createGuest = new Guest(n,pw);
//    usernameList.add(n);
//    passwordList.add(pw);
//
//    System.out.println(usernameList.get(0A)+"hAHA");


    }



    @FXML
    private Button buttonCreate;
    @FXML
    private Tab tabManager;
    @FXML
    private Tab tabGuest;

    @FXML
    private Button buttonLogin;

    @FXML
    private Label labelLogin;

    @FXML
    private TextField txtfieldUsername;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Button buttonExit;

    @FXML
    private TextField getTxtfieldUsernameManager;

    @FXML
    private PasswordField getTxtfieldPasswordManager;

    @FXML
    private Button buttonManagerLogin;

    @FXML
    private Button buttonExit1;

    @FXML
    private Label labelManagerLogin;




    @FXML
    void extraFunction(ActionEvent event){
        //System.out.println("BLAH"); Cant remember what this is for

    }

    @FXML
    void handleButtonExit(ActionEvent event) {
      /**
       * Exit Program, closes window
       */
        System.out.println("Goodbye");
      Stage stageExit = (Stage) buttonExit.getScene().getWindow();
      stageExit.close();
    }

    @FXML
    void handleButtonLogin(ActionEvent event) {

        String field1, field2;//Declare Strings
        field1 = txtfieldUsername.getText();//Store what in txtField Username into field1
        field2 = textFieldPassword.getText();//Store whats in txtFieldPassword into field2
        //UserType tyoe= UserType.GUEST; Not used
        checkLogin(field1,field2);//Calls method to check if inputs are valid




    }

    @FXML
    void handleButtonUsername(ActionEvent event) {

    }

    @FXML
    void handleButtonLoginManager(ActionEvent event){
      //see HandleButtonLogin for guests, basically same code
        System.out.println("MAngaer");
        boolean isNameCorrect=false;
        boolean isPasswordCorrect=false;
        String field1, field2;
        field1 = getTxtfieldUsernameManager.getText();
        field2 = getTxtfieldPasswordManager.getText();

        String managerUsername = "Nicolo";
        String managerPassword = "Martina";
        //UserType tyoe= UserType.MANAGER;
        if (field1.equalsIgnoreCase(managerUsername)){
            isNameCorrect= true;
            if (field2.equals(managerPassword)){
                isPasswordCorrect= true;
                System.out.println("Login Manager");



            }

        }
        if (isNameCorrect == false && isPasswordCorrect == false){
            System.out.println("Error, Sorry login credentials not found");
        }else if (isNameCorrect==true && isPasswordCorrect== false){
            System.out.println("Password Incorrect");
        }else {
            System.out.println("Successful Login");
        }

      openManagerMenu();
    }

  private void openManagerMenu() {
  //  ManagerMenuController guestController = new ManagerMenuController(
    //    usernameList,passwordList,guestList,rooms
    //);
    //guestController.storeVariables(usernameList,passwordList,guestList,g1,rooms);
    //FXMLLoader loader = new FXMLLoader();


//  FXMLLoader Loader = new FXMLLoader();
 // Loader.setLocation(getClass().getResource("ManagerMenuController.fxml")); //Call new window
   // try {
    //  Loader.setController(guestController);
     // Loader.load(); //Loads
    //}catch ( IOException ex){
     // Logger.getLogger(GuestMenuController.class.getName()).log(Level.SEVERE, null ,ex);

    //}
//      DisplayTextController display = Loader.getController(); //Calling DisplayTextcontroller file
//     display.setText(name_Text,email_Text); //using displaytextcontroller's method
//      GuestMenuController storeFields =Loader.getController(); //Calling the new window's  controller
//     storeFields.storeVariables(usernameList,passwordList,guestList,g1,rooms); //Calling the controller's method
//    Store Variables will store the lists of users, passwords and guest
//    GuestMenu's store Variables also takes the guest selected by user.
/**
 * See Guest Controller open for Notes, since it's basically the same
 *
 */

    updateGlobal();
    ManagerMenuController managerController = new ManagerMenuController();
    Stage stageExit = (Stage) buttonExit1.getScene().getWindow();//
    stageExit.close();//Closes curerent Stage

    FXMLLoader Loader = new FXMLLoader(); //sample.LoginMenu.LoginMenuController
    Loader.setLocation(getClass().getResource("/sample/ManagerMenu/ManagerMenu.fxml")); //Call new window
    try {
      Loader.setController(managerController);
      Loader.load(); //Loads
    }catch ( IOException ex){
      Logger.getLogger(ManagerMenuController.class.getName()).log(Level.SEVERE, null ,ex);

    }


//    FXMLLoader Loader = new FXMLLoader();
//    Loader.setLocation(getClass().getResource("ManagerMenu.fxml"));
//    try {
//      Loader.load();
//    }catch ( IOException ex){
//      Logger.getLogger(GuestMenuController.class.getName()).log(Level.SEVERE, null ,ex);
//
//    }
//    ManagerMenuController storeFields =Loader.getController(); //Calling the new window's  controller
// storeFields.storeVariables2(usernameList,passwordList,guestList,rooms); //Calling the controller's method
    //Dont' do these two lines again. What happens first is Controller's intilize with makez default 3 rooms.
    //And then it gets set with the empty room the second send it cassing error. We have a better way doing this.

    Parent p = Loader.getRoot();
    Stage stage = new Stage();
    stage.setTitle("Manager Menu");
    stage.setScene(new Scene(p));
    stage.show(); //Opens
  }
  @FXML
  CheckBox checkBox2;
  @FXML
  TextField textFieldForPassword;
    @FXML TextField textFieldForPasswordManager;
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    /**
     * This method is called first when FXMLDOCUEMENTCONTROLLER IS opened.
     * It will set the properties for passwordFields to able to show/hide
     *
     * And also it will take the data of Global class so data stays consistent.
     */
    this.usernameList = Global.usernameList;
    this.passwordList = Global.passwordList;
    this.guestList = Global.guestList;
    this.rooms = Global.rooms;
    this.data = Global.data;

  //  guestList.add(new Guest("Hi","ko"));
   // System.out.println(Global.guestList.get(0));

    // text field to show password as unmasked
    //final TextField textFieldForPassword = new TextField(); //REgular textfield show characters
    //System.out.println(textFieldPassword.getLayoutY());
    //System.out.println(textFieldPassword.getLayoutX());
   // textFieldForPassword.setLayoutY(textFieldPassword.getLayoutY());
   // textFieldForPassword.setLayoutX(textFieldPassword.getLayoutX());
    // Set initial state
    textFieldForPassword.setManaged(false);
    textFieldForPassword.setVisible(false);//Hide regular textfield

    textFieldForPasswordManager.setManaged(false);
    textFieldForPasswordManager.setVisible(false);//Hide regular textfield
    textFieldForPasswordManager.managedProperty().bind(checkBox2.selectedProperty());// TextField's setManageProperty will be changed by CheckBox
    textFieldForPasswordManager.visibleProperty().bind(checkBox2.selectedProperty());// TextField's setVisibleProperty will be changed by CheckBox

    getTxtfieldPasswordManager.managedProperty().bind(checkBox2.selectedProperty().not());//Same as above but oppsite?
    getTxtfieldPasswordManager.visibleProperty().bind(checkBox2.selectedProperty().not());

    // Bind the textField and passwordField text values bidirectionally.
    textFieldForPasswordManager.textProperty().bindBidirectional(getTxtfieldPasswordManager.textProperty()); //MAkes two textfie

    // Actual password field
    //final PasswordField passwordField = new PasswordField();//Password Field shows ***

    // CheckBox checkBox = new CheckBox("Show/Hide password");//Create checkbox that will toggle

    // Bind properties. Toggle textField and passwordField
    // visibility and managability properties mutually when checkbox's state is changed.
    // Because we want to display only one component (textField or passwordField)
    // on the scene at a time.
    textFieldForPassword.managedProperty().bind(checkBox.selectedProperty());// TextField's setManageProperty will be changed by CheckBox
    textFieldForPassword.visibleProperty().bind(checkBox.selectedProperty());// TextField's setVisibleProperty will be changed by CheckBox

    textFieldPassword.managedProperty().bind(checkBox.selectedProperty().not());//Same as above but oppsite?
    textFieldPassword.visibleProperty().bind(checkBox.selectedProperty().not());

    // Bind the textField and passwordField text values bidirectionally.
    textFieldForPassword.textProperty().bindBidirectional(textFieldPassword.textProperty()); //MAkes two textfields share share same input
    //If this code isn't there the two textfields are seperate

  }


  @FXML
    void handleButtonCreate(ActionEvent event){
        txtfieldUsername.requestFocus(); //Can be used if user entered something wrong
        txtfieldUsername.selectAll();//Highlights the text so user can easily fix it wiothut mouse click
        String field1, field2;
        field1 = txtfieldUsername.getText();
        field2 = textFieldPassword.getText();
        System.out.println(field1+" "+field2);
        Guest createGuest;
        if (guestList.isEmpty()){
          //Since first time running this code, make default guestlist
          createGuest(field1,field2);
          createGuest("john doe","password");
          createGuest("jane doe","qwerty");
          createGuest("Juan Doe","asdf");
        }else{
          createGuest(field1,field2);
        }
        int i=0;
      System.out.println("\n");
        for (String word :getUsernameList()){
            System.out.println("Name = " + word);
            System.out.println(getPasswordList().get(i));
            i++;
        }
        currentGuest= getGuestList().get(1);


    }

}


//
//    public  void openGuestMenu(Guest g1) throws IOException {
//        try {
//            Stage stage = (Stage) buttonCreate.getScene().getWindow();
//            stage.close();
//            Parent root2 = FXMLLoader.load(getClass().getResource("GuestMenu.fxml" ));
//
//            stage = new Stage();
//
//            stage.setScene(new Scene(root2));
//            stage.show();
//
//
//
//        }finally {
//
//        }
//    }
