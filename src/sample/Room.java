package sample;
//Great WEbsite https://examples.javacodegeeks.com/desktop-java/javafx-table-example/

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class Room {
  String name;
  String pictureUrl;
  int squareFt;
  boolean isAvailble;
  int price;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public int getSquareFt() {
    return squareFt;
  }

  public void setSquareFt(int squareFt) {
    this.squareFt = squareFt;
  }

  public boolean isAvailble() {
    return isAvailble;
  }

  public void setAvailble(boolean availble) {
    isAvailble = availble;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Room(String name) {
    this.name = name;
  }
}

class RoomCell  extends ListCell<Room>
{
  @Override
  public void updateItem(Room item, boolean empty)
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


class RoomCellFactory implements Callback<ListView<Room>, ListCell<Room>> {

  @Override
  public ListCell<Room> call(ListView<Room> listview) {
    return new RoomCell();
  }
}
