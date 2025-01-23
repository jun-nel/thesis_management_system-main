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

          
             setGraphic(new Label(tblstudent.getfullName()));
        }
    }


    private final FXIntegerProperty ID;
    private final FXStringProperty Surname;
    private final FXStringProperty Fname;
    private final FXStringProperty MI;

    public Tblstudent(Integer ID,
                      String Surname,
                      String Fname,
                      String MI){
        this.ID = new FXIntegerProperty(ID);
        this.Surname = new FXStringProperty(Surname);
        this.Fname = new FXStringProperty(Fname);
        this.MI = new FXStringProperty(MI);
    
    }

    public Tblstudent(int ID,
       String Surname,
       String Fname,
       String MI){

        this.ID = new FXIntegerProperty(ID);
        this.Surname = new FXStringProperty(Surname);
        this.Fname = new FXStringProperty(Fname);
        this.MI = new FXStringProperty(MI);
    }

    public FXIntegerProperty IDProperty(){
        return ID;
    }

    public Integer getID(){
        return IDProperty().get();
    }

    public void setID(Integer ID){
        IDProperty().set(ID);
    }
    
    public FXStringProperty SurnameProperty(){
        return Surname;
    }

    public String getSurname(){
       return SurnameProperty().get();
    }

    public void setSurname(String Surname){
        SurnameProperty().set(Surname);
    }

    public FXStringProperty FnameProperty(){
        return Fname;
    }

    public String getFname(){
        return FnameProperty().get();
    }

    public void setFname(String Fname){
        FnameProperty().set(Fname);
    }

    public FXStringProperty MIProperty(){
        return MI;
    }

    public String getMI(){
      return  MIProperty().get();
    }

    public void setMI(String MI){
        MIProperty().set(MI);
    }


    public String getfullName(){
        return getFname() + ", " + getSurname() +", "+ getMI();
    }


    @Override
    public FXModel clone() {
       Tblstudent tblstudents = new Tblstudent(getID(),
          getSurname(),
          getFname(),
          getMI());
     return tblstudents;
    }

    @Override
    public void copy(FXModel arg0) {
        Tblstudent c = (Tblstudent) arg0;

        setID(c.getID());
        setSurname(c.getSurname());
        setFname(c.getFname());
        setMI(c.getMI());
    
    }
}
    

