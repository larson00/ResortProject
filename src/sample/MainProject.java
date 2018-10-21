package sample;


import java.util.ArrayList;

public class MainProject {
  private ArrayList<String> usernameList = new ArrayList<>();
  private ArrayList<String> passwordList = new ArrayList<>();
  private Guest currentGuest;
  private Manager admin;

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
    getUsernameList().add(n);
    getPasswordList().add(pw);


  }

  public void main(String[] args){
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

    for (String word :getUsernameList()){
      System.out.println("Name = " + word);


    }





  }



}
