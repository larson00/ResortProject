/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Notes
 * Chck the text file of fxml files, they will sometimes have redtext/eerors which intellj will not tell you
 * MAke sure there no java.awt wiwll cause errors
 *
 */


/**
 *
 * @author ggrab
 */

public class FXMLDocumentController {
    private ArrayList<String> usernameList = new ArrayList<>();
    private ArrayList<String> passwordList = new ArrayList<>();
    private ArrayList<Guest> guestList = new ArrayList<>();
    private Guest currentGuest;
    private Manager admin;
    enum UserType{
        GUEST,MANAGER

    }
    @FXML
  public void storeVariables1(ArrayList<String> unLIST, ArrayList<String> pwList,
      ArrayList<Guest> gList){
      //To send and store varaibles across windows.
    this.usernameList = unLIST;
    this.passwordList = pwList;
    this.guestList = gList;
    int i=0;
    for (String word :usernameList){
      System.out.println("Name = " + word);
      System.out.println(passwordList.get(i));
      i++;
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

      FXMLLoader Loader = new FXMLLoader();
      Loader.setLocation(getClass().getResource("GuestMenu.fxml")); //Call new window
      try {
        Loader.load(); //Loads
      }catch ( IOException ex){
        Logger.getLogger(GuestMenuController.class.getName()).log(Level.SEVERE, null ,ex);

      }
      //  DisplayTextController display = Loader.getController(); //Calling DisplayTextcontroller file
      // display.setText(name_Text,email_Text); //using displaytextcontroller's method
      GuestMenuController storeFields =Loader.getController(); //Calling the new window's  controller
      storeFields.storeVariables(usernameList,passwordList,guestList,g1); //Calling the controller's method
      //Store Variables will store the lists of users, passwords and guest
      //GuestMenu's store Variables also takes the guest selected by user.
      //

      Parent p = Loader.getRoot();
      Stage stage = new Stage();
      stage.setScene(new Scene(p));
      stage.show();




    }


    void checkLogin(String usernameSent, String passwordSent)
    {
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
                        openGuestMenu(g1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
    private TextField txtfieldPassword;

    @FXML
    private Button buttonExit;

    @FXML
    private TextField getTxtfieldUsernameManager;

    @FXML
    private TextField getTxtfieldPasswordManager;

    @FXML
    private Button buttonManagerLogin;

    @FXML
    private Button buttonExit1;

    @FXML
    private Label labelManagerLogin;

    @FXML
    void extraFunction(ActionEvent event){
        System.out.println("BLAH");

    }

    @FXML
    void handleButtonExit(ActionEvent event) {
        System.out.println("Goodbye");
      Stage stageExit = (Stage) buttonExit.getScene().getWindow();
      stageExit.close();
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
        System.out.println("MAngaer");
        boolean isNameCorrect=false;
        boolean isPasswordCorrect=false;
        String field1, field2;
        field1 = getTxtfieldUsernameManager.getText();
        field2 = getTxtfieldPasswordManager.getText();
        String managerUsername = "Nicolo";
        String managerPassword = "Martin";
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