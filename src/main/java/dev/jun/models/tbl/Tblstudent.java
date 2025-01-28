package dev.jun.models.tbl;

import dev.jun.models.degree.Degree;
import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXIntegerProperty;
import dev.sol.core.properties.beans.FXStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class Tblstudent extends FXModel {
     public static class LIST_CELL extends ListCell<Tblstudent> {
        @Override
        protected void updateItem(Tblstudent tblstudent, boolean empty){
            super.updateItem(tblstudent, empty);

            if (tblstudent == null || empty){
                setText(null);
                setGraphic(null);
                return;
            }

          
             setGraphic(new Label(tblstudent.getFullname()));
        }
    }


    private final FXIntegerProperty ID;
    private final FXStringProperty Surname;
    private final FXStringProperty Firstname;
    private final FXStringProperty MI;
    private FXStringProperty fullname;

    public Tblstudent(Integer ID,
                      String Surname,
                      String Firstname,
                      String MI){
        this.ID = new FXIntegerProperty(ID);
        this.Surname = new FXStringProperty(Surname);
        this.Firstname = new FXStringProperty(Firstname);
        this.MI = new FXStringProperty(MI);
        this.fullname = new FXStringProperty(Surname + ", " + Firstname + " " + MI);

        track_properties(this.ID, this.Surname, this.Firstname, this.MI);

        
    
    }

    // public Tblstudent(int ID,
    //    String Surname,
    //    String Firstname,
    //    String MI){

    //     this.ID = new FXIntegerProperty(ID);
    //     this.Surname = new FXStringProperty(Surname);
    //     this.Firstname = new FXStringProperty(Firstname);
    //     this.MI = new FXStringProperty(MI);
    // }

    public FXIntegerProperty IDProperty(){
        return ID;
    }

    public Integer getID(){
        return IDProperty().get();
    }

    public void setID(Integer ID){
        IDProperty().set(ID);
    }
    // surname
    public FXStringProperty SurnameProperty(){
        return Surname;
    }

    public String getSurname(){
       return SurnameProperty().get();
    }

    public void setSurname(String Surname){
        SurnameProperty().set(Surname);
    }
     // Firstname
    public FXStringProperty FirstnameProperty(){
        return Firstname;
    }

    public String getFirstname(){
        return FirstnameProperty().get();
    }

    public void setFname(String Fname){
        FirstnameProperty().set(Fname);
    }
 // MI
    public FXStringProperty MIProperty(){
        return MI;
    }

    public String getMI(){
      return  MIProperty().get();
    }

    public void setMI(String MI){
        MIProperty().set(MI);
    }
    public FXStringProperty fullnameProperty() {
        return this.fullname;
    }
    public String getFullname() {
        return fullnameProperty().get();
    }


    public String fullName(){
        return SurnameProperty().get() + ", " + FirstnameProperty().get() +", "+ MIProperty().get();
    }


    @Override
    public FXModel clone() {
       Tblstudent tblstudents = new Tblstudent(getID(),
          getSurname(),
          getFirstname(),
          getMI());
     return tblstudents;
    }

    @Override
    public void copy(FXModel arg0) {
        Tblstudent c = (Tblstudent) arg0;

        setID(c.getID());
        setSurname(c.getSurname());
        setFname(c.getFirstname());
        setMI(c.getMI());
    
    }
}
    

