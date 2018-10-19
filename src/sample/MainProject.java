package sample;


import java.util.ArrayList;
import java.util.Scanner;

public class MainProject {
  private ArrayList<String> usernameList = new ArrayList<>();
  private ArrayList<String> passwordList = new ArrayList<>();
  private ArrayList<Guest> guestList = new ArrayList<>();
  private Guest currentGuest;
  private Manager admin;


  void checkLogin(String usernameSent, String passwordSent)
  {
    usernameSent= usernameSent.toLowerCase();
    passwordSent= passwordSent.toLowerCase();
    boolean isNameCorrect=false;
    boolean isPasswordCorrect=false;
    for (String word1: getUsernameList()) {
      if (usernameSent.equalsIgnoreCase(word1)){
        //they match!
        isNameCorrect= true;
        //now check password
        for(String word2: getPasswordList()) {
          if (passwordSent.equalsIgnoreCase(word2)) {
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

 MainProject(){
//    System.out.println(" Enter Username");
//    Scanner scanner = new Scanner(System.in);
//    String inputUserName = scanner.nextLine();
//    System.out.println("Enter Password");
//    String inputPassword = scanner.nextLine();

    //Add to usernameList

    int g = 5;
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
   System.out.println(currentGuest.getUserName()+currentGuest.getPassword());
     checkLogin(currentGuest.getUserName(),currentGuest.getPassword());
     checkLogin("JANE Doe","QWerty");
   System.out.println("Jack Doe and Joey");
   checkLogin("Jack Doe","QWerty");
   System.out.println("Joey Doe and bad password");
   checkLogin("Joey Doe","KOK");
   System.out.println("jane doe bad password");
   checkLogin("JANE Doe","erty");

    }








}






