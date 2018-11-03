package sample;

/**
 * Guest.java
 * Maybe it should be called GuestAccount.
 * Guest is a subclass of the Abstract class User.
 * Guest store the information a guest user inputs when they create a account.
 * Guests will be able to view Rooms, Book a Room, View Events, Book Event and if time allows be able to change Settings
 *
 */

public class Guest extends User {
  private String firstName;
  private String lastName;
  private String dateOfBirth; //Format of MM/DD/YY, Actually might stick to Year for simplciity.

  String getFullName(){
    return firstName+lastName;
  }

  public Guest(String userName, String password) {
    /**
     * Uses User class's constructor to store a username and password.
     */
    super(userName, password);
  }
}
