package sample;

public class Guest extends User {
  private String firstName;
  private String lastName;
  private String dateOfBirth; //Format of MM/DD/YY, Actually might stick to Year for simplciity.



  public Guest(String userName, String password) {
    super(userName, password);
  }
}
