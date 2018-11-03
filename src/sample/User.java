package sample;

/**
 * User.java
 * Could also be called UserAccount
 * Manager and Guest Class extend from this.
 *
 */

public abstract class  User {
  private final String userName;
  private final String password;

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }
}
