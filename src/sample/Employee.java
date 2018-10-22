package sample;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class Employee {
  String name,title;
  double payHourly;
  int id;

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

  public double getPayHourly() {
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

  public Employee(String name, double payHourly, int id) {
    this.name = name;
    //this.title = title;
    this.payHourly = payHourly;
    this.id = id;
  }


}

class EmployeeCell  extends ListCell<Employee>
{
  @Override
  public void updateItem(Employee item, boolean empty)
  {
    super.updateItem(item, empty);

    int index = this.getIndex();
    String name = null;

    // Format name
    if (item == null || empty)
    {
    }
    else
    {
      name = (index + 1) + ". " +
          item.getName() + ", ";
    }

    this.setText(name);
    setGraphic(null);
  }
}


class EmployeeCellFactory implements Callback<ListView<Employee>, ListCell<Employee>> {

  @Override
  public ListCell<Employee> call(ListView<Employee> listview) {
    return new EmployeeCell();
  }

}