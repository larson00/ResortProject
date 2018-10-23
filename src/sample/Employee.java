package sample;
/**
 * Employee.java Class
 * Simple____Property allows for ease in making columsn in table views
 * Look up how SimpleStringPropertyWorks
 *
 */

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public  class Employee {

  private final SimpleStringProperty name;
  private final SimpleDoubleProperty payHourly;
  private final SimpleIntegerProperty employeeID;

  Employee(String fName, double payme, Integer id) {
    this.name = new SimpleStringProperty(fName);
    this.payHourly = new SimpleDoubleProperty(payme);
    this.employeeID = new SimpleIntegerProperty(id);
  }

  public String getName() {
    return name.get();
  }

  public void setName(String fName) {
    name.set(fName);
  }

  public Double getPayHourly() {
   // System.out.println("HERE");
    //return "out";
    return payHourly.get();
  }

  public void setPayHourly(Double payme) {
   // System.out.println("paymee");
    payHourly.set(payme);

  }

  public Integer getEmployeeID() {
    return employeeID.get();
  }

  public void setEmployeeID(Integer id) {
    employeeID.set(id);
  }
}