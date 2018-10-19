package sample;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class FXMLDocumentController {
  private ArrayList<String> usernameList = new ArrayList<>();
  private ArrayList<String> passwordList = new ArrayList<>();
  private ArrayList<Guest> guestList = new ArrayList<>();
  private Guest currentGuest;
  private Manager admin;
  enum UserType{
    GUEST,MANAGER

  }


  void checkLogin(String usernameSent, String passwordSent)
  {
    // usernameSent= usernameSent.toLowerCase();
    //passwordSent= passwordSent.toLowerCase();
    boolean isNameCorrect=false;
    boolean isPasswordCorrect=false;
    for (String word1: getUsernameList()) {
      if (usernameSent.equalsIgnoreCase(word1)){
        //they match!
        isNameCorrect= true;
        //now check password
        for(String word2: getPasswordList()) {
          if (passwordSent.equals(word2)) {
            System.out.println("Perect");
            isPasswordCorrect= true;
          }


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
    Guest createGuest = new Guest(n,pw);

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
  private Tab tabGuest;

  @FXML
  private Button buttonLogin;

  @FXML
  private Label labelLogin;

  @FXML
  private TextField txtfieldUsername;

  @FXML
  private TextField txtfieldPassword;

  @FXML
  private Button buttonExit;

  @FXML
  private TextField txtfieldUsername1;

  @FXML
  private TextField txtfieldUsername2;

  @FXML
  private Button buttonManagerLogin;

  @FXML
  private Button buttonExit1;

  @FXML
  private Label labelManagerLogin;

  @FXML
  void handleButtonExit(ActionEvent event) {
    System.out.println("Goodbye");
    System.out.close();
  }

  @FXML
  void handleButtonLogin(ActionEvent event) {
    String field1, field2;
    field1 = txtfieldUsername.getText();
    field2 = txtfieldPassword.getText();
    UserType tyoe= UserType.GUEST;
    checkLogin(field1,field2);




  }

  @FXML
  void handleButtonUsername(ActionEvent event) {

  }

  @FXML
  void handleButtonLoginManager(ActionEvent event){
    boolean isNameCorrect=false;
    boolean isPasswordCorrect=false;
    String field1, field2;
    field1 = txtfieldUsername.getText();
    field2 = txtfieldPassword.getText();
    String managerUsername = "Nicolo";
    String managerPassword = "Martin";
    UserType tyoe= UserType.MANAGER;
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


  }


  @FXML
  void handleButtonCreate(ActionEvent event){
    String field1, field2;
    field1 = txtfieldUsername.getText();
    field2 = txtfieldPassword.getText();
    System.out.println(field1+" "+field2);
    Guest createGuest;
    createGuest(field1,field2);

    createGuest("john doe","password");
    createGuest("jane doe","qwerty");
    createGuest("Juan Doe","asdf");
    int i=0;
    for (String word :getUsernameList()){
      System.out.println("Name = " + word);
      System.out.println(getPasswordList().get(i));
      i++;
    }
    currentGuest= getGuestList().get(1);

  }

}
