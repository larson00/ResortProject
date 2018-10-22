/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ggraber7402
 */


public class ManagerMenuController implements Initializable {
    private ArrayList<String> usernameList;
    private ArrayList<String> passwordList;
    private ArrayList<Guest> guestList;
    private Guest currentGuest;
    private Room roomClickedOn;
    private Employee employeeClickedOn;
    private int daysStaying;
    private boolean initializedRooms=false;
    @FXML
    private TableView<Employee> employeeTable = new TableView<Employee>();
    private ObservableList<Employee> data;
    final HBox hb = new HBox();

    @FXML
    public void storeVariables2(ArrayList<String> unLIST, ArrayList<String> pwList,
        ArrayList<Guest> gList, List<Room> rooms){
        System.out.println("HERE FIRST?");
        this.usernameList = unLIST;
        this.passwordList = pwList;
        this.guestList = gList;
       // this.currentGuest = g1;
        this.rooms = rooms;
        int i=0;
        for (String word :usernameList){
            System.out.println("Name = " + word);
            System.out.println(passwordList.get(i));
            i++;
        }



    }

    @FXML
    private TabPane tabPane;

    @FXML
    private Label labelAvailable,labelGuestName;
    @FXML
    private Label labelPricePerDay;
    @FXML
    private Label labelDaysStaying;
//  @FXML
//  private ImageView imageViewRoom;


    @FXML
    private Button signoutButton,buttonRemoveGuest;
   // @FXML
    //private Button bookRoombutton;

    @FXML
    private ListView roomListView;

    ///@FXML
    //TableColumn columnEmployeeID,columnNameEmployee,columnPayroll;
    @FXML
    Tab tabEmployeeList;
    //@FXML
  //  TableView tableViewEmployees;




    @FXML
    protected List<Room> rooms = new ArrayList<>();
    @FXML
    protected ListProperty<Room> listProperty = new SimpleListProperty<>();

 //   @FXML
    //protected List<Employee> employees = new ArrayList<>();
    @FXML
    protected ListProperty<Employee> listPropertyEmployee = new SimpleListProperty<>();


    @FXML private TableView<Employee> tableViewEmployees;
    @FXML private TableColumn<Employee, Integer> columnEmployeeID;
    @FXML private TableColumn<Employee, String> columnNameEmployee;
    @FXML private TableColumn<Employee, Double> columnPayroll;
    @FXML private Button buttonAdd, buttonDelete;

    @FXML private Button buttonSubmit;
    @FXML private TextField textFieldEmpName,textFieldEmpID,textFieldEmpPayRoll;
    @FXML private Label labelSuccessSubmit;
    @FXML private Tab tabAddEmployee;

    @FXML void handleAddButton(){
        tabAddEmployee.setDisable(false);
        tabPane.getSelectionModel().select(tabAddEmployee);

    }
    @FXML void handleSubmit(){
        Boolean successful11=true;
        String empName= textFieldEmpName.getText();
        int empID=0;
        Double empPayRoll =0.0;
        System.out.println(textFieldEmpPayRoll.getText());
        labelSuccessSubmit.setText("WHATTHE");

        try{
            empID= Integer.parseInt(textFieldEmpID.getText());
        }catch(NumberFormatException exception){
            successful11=false;
            labelSuccessSubmit.setText("Employee ID Input Error.");
        }

       Boolean successful12=true;
        try{

            empPayRoll =Double.parseDouble(textFieldEmpPayRoll.getText());
        }catch (NullPointerException exception){
            successful12=false;
            labelSuccessSubmit.setText("Payroll Input Error.");
        }
        if (successful11== true && successful12 == true){
            //correct inputs, allow sumbit
            labelSuccessSubmit.setText("Employee added succesfully!");
            data.add(new Employee(empName,empPayRoll,empID ));

        }



    }

    @FXML void handleDeleteEmployee(){
        data.remove(employeeClickedOn);
        System.out.println(employeeClickedOn.getName()+"IS GONE");
        employeeClickedOn= null;



    }

    @FXML
    void handleAdd(){
        System.out.println("add");
        data.add(new Employee(
            "Mark Jackson",
            12.3,
            6));
       // Employee createemp = new Employee("Joe Jackson", "12.0","6");
     //   employees.add(createemp);



    }












    @FXML
    public void handleSignout(ActionEvent event){
//        try {
//            Stage stage = (Stage) signoutButton.getScene().getWindow();
//            stage.close();
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            stage = new Stage();
//            stage.setScene(new Scene(root1));
//            stage.show();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        Stage stageExit = (Stage) signoutButton.getScene().getWindow();
        stageExit.close();
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        try {
            Loader.load();
        }catch ( IOException ex){
            Logger.getLogger(GuestMenuController.class.getName()).log(Level.SEVERE, null ,ex);

        }
        //  DisplayTextController display = Loader.getController(); //Calling DisplayTextcontroller file
        // display.setText(name_Text,email_Text); //using displaytextcontroller's method
        FXMLDocumentController storeFields =Loader.getController();
        int i=0;
        for (String word :usernameList){
            System.out.println("Name = " + word);
            System.out.println(passwordList.get(i));
            i++;
        }
        storeFields.storeVariables1(usernameList,passwordList,guestList,rooms,data);


        for (Employee e: data
        ) {

            System.out.println(e.getName());
        }
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();



    }

    ManagerMenuController(ArrayList<String> unLIST, ArrayList<String> pwList,
        ArrayList<Guest> gList,List<Room> rooms,ObservableList<Employee> data ){
        System.out.println("herefirst?");
        /**
         * Happens after initialize class
         */

        this.usernameList = unLIST;
        this.passwordList = pwList;
        this.guestList = gList;
        //this.currentGuest = g1;
        this.rooms = rooms;
        this.data = data;
        System.out.println("In Constructor");

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabAddEmployee.setDisable(true);
        System.out.println("HERE IN INTAILIZE MANAGER");
       // data =;
        if (data.isEmpty()){
            System.out.println("DATA EMPTY\n\n\n\n");
            data =
                FXCollections.observableArrayList(
                    new Employee("Jacob", 12.3, 1),
                    new Employee("Isabella", 13.8, 2),
                    new Employee("Ethan", 12.8, 3),
                    new Employee("Emma", 12.9, 4),
                    new Employee("Michael", 15.0, 5)
                );

        }
        System.out.println("INTY");
//        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//                data.add(new Employee(
//                    addFirstName.getText(),
//                    addLastName.getText(),
//                    addEmail.getText()
//                ));
//                addFirstName.clear();
//                addLastName.clear();
//                addEmail.clear();
//            }
//        });
//        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//                data.add(new Employee(
//                  "Mark Jackson",
//                   "12.3",
//                    "6"
//                ));
//            }
//        });
        //columnEmployeeID.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        //columnNameEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        //columnPayroll.setCellValueFactory(new PropertyValueFactory<Employee, String>("active"));
        //TableColumn firstNameCol = new TableColumn("First Name");
       // columnEmployeeID.setMinWidth(100);
        columnNameEmployee.setCellValueFactory(
            new PropertyValueFactory<Employee, String>("name"));

        //TableColumn lastNameCol = new TableColumn("Last Name");
      //  columnNameEmployee.setMinWidth(100);
        columnPayroll.setCellValueFactory(
            new PropertyValueFactory<Employee, Double>("payHourly"));

        //TableColumn emailCol = new TableColumn("Email");
        //columnPayroll.setMinWidth(200);
        columnEmployeeID.setCellValueFactory(
            new PropertyValueFactory<Employee, Integer>("employeeID"));

        tableViewEmployees.setItems(data);
       // tableViewEmployees.getColumns().addAll(columnEmployeeID, columnPayroll, columnEmployeeID);
        // TODO
           // tableViewEmployees.setOnMousePressed((MouseEvent event) );
        tableViewEmployees.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String employeeSelected = tableViewEmployees.getSelectionModel().getSelectedItem().toString();
                //System.out.println("To string:"+roomSelected.toString());

                for (Employee e: data
                ) {
                    if (e.toString().equalsIgnoreCase(employeeSelected)){
                        employeeClickedOn= e;
                        System.out.println("Room: "+employeeClickedOn.getName());
                        //Same toString same, set currentRoom to r

                    }
                    // System.out.println(r.getName());
                }
                System.out.println("Here is : "+employeeClickedOn);
                // System.out.println("clicked on " + roomListView.getSelectionModel().getSelectedItem());

                //Check Room Avability.
                //displayRoomFeatures(roomClickedOn);

            }

        });




        if (rooms.isEmpty())
        {
            System.out.println("ROOM OS EMPTY \n\n\n");
            rooms.add(new Room("1A",false,200));
            rooms.get(0).setAvailable(false);
            rooms.get(0).setOccupiedGuest(new Guest("BruceWayne","batman"));//John Doe occupies room 1A
            rooms.add(new Room("Room 2A",true,300));
            rooms.get(1).setAvailable(true);
            rooms.add(new Room("Room 3A",false,500));
            rooms.get(2).setAvailable(false);
            rooms.get(2).setOccupiedGuest(new Guest("ClarkKent","superman"));//John Doe occupies room 1A
        }


        roomListView.itemsProperty().bind(listProperty);

        listProperty.set(FXCollections.observableArrayList(rooms));
        roomListView.setCellFactory(new RoomCellFactory());
        roomListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String roomSelected = roomListView.getSelectionModel().getSelectedItem().toString();
                //System.out.println("To string:"+roomSelected.toString());

                for (Room r: rooms
                ) {
                    if (r.toString().equalsIgnoreCase(roomSelected)){
                        roomClickedOn= r;
                        System.out.println("Room: "+roomClickedOn.getName());
                        //Same toString same, set currentRoom to r

                    }
                    // System.out.println(r.getName());
                }
                System.out.println("Here iszzz : "+roomSelected);
                // System.out.println("clicked on " + roomListView.getSelectionModel().getSelectedItem());

                //Check Room Avability.
                displayRoomFeatures(roomClickedOn);

            }

        });

    }
    @FXML
    private void handleRemoveGuest(){
        System.out.println("REmvoing Guest");
        if (roomClickedOn != null){
            //So theres a room clicked on
            roomClickedOn.removeGuest();
            displayRoomFeatures(roomClickedOn);

        }


    }




    private void displayRoomFeatures(Room roomClickedOn) {
        if (roomClickedOn.getAvailable()){
            //true
            labelAvailable.setText("Not Book");
            //bookRoombutton.setDisable(false);

        }else
        {
            labelAvailable.setText("Booked");
            //bookRoombutton.setDisable(true);

        }
        labelPricePerDay.setText(Double.toString(roomClickedOn.getPrice()));

        labelDaysStaying.setText(Integer.toString(roomClickedOn.getDaysStaying()));

        try {
            labelGuestName.setText(roomClickedOn.getOccupiedGuest().getUserName());

        }catch (NullPointerException exception){
            labelGuestName.setText("None");

        }
        }



//        public void EmployeeWindow(){
//            System.out.println("HERE IN INTAILIZE MANAGER");
//
//            if (employees.isEmpty())
//            {
//                System.out.println("Employees is empty \n\n\n");
//                employees.add(new Employee("Brad",9.5,1));
//
//                employees.add(new Employee("Tom",11.5,2));
//                employees.add(new Employee("Pete",8.0,3));
//
//            }


       //     tableViewEmployees.itemsProperty().bind(listProperty);
     //       columnEmployeeID.setCellFactory(new PropertyValueFactory<Employee, String>("EmployeeID"));

            //tableViewEmployees.set(FXCollections.observableArrayList(employees));
           // tableViewEmployees.setCellFactory(new RoomCellFactory());
         //   tableViewEmployees.setOnMouseClicked(new EventHandler<MouseEvent>() {

//                @Override
//                public void handle(MouseEvent event) {
//                    String employeeSelected = tableViewEmployees.getSelectionModel().getSelectedItem().toString();
//                    //System.out.println("To string:"+roomSelected.toString());
//
//                    for (Employee emp: employees
//                    ) {
//                        if (emp.toString().equalsIgnoreCase(employeeSelected)){
//                            employeeClickedon= emp;
//                            System.out.println("Room: "+roomClickedOn.getName());
//                            //Same toString same, set currentRoom to r
//
//                        }
//                        // System.out.println(r.getName());
//                    }
//                    System.out.println("Here iszzz : "+roomSelected);
//                    // System.out.println("clicked on " + roomListView.getSelectionModel().getSelectedItem());
//
//                    //Check Room Avability.
//                    displayRoomFeatures(roomClickedOn);
//
//                }

        }


