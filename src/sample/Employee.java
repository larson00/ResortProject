package sample;

public class Employee {
  String name,title;
  int payHourly,id;

  public String getName() {

    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getPayHourly() {
    return payHourly;
  }

  public void setPayHourly(int payHourly) {
    this.payHourly = payHourly;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Employee(String name, String title, int payHourly, int id) {
    this.name = name;
    this.title = title;
    this.payHourly = payHourly;
    this.id = id;
  }


}
