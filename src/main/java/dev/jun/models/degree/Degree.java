package dev.jun.models.degree;

import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXIntegerProperty;
import dev.sol.core.properties.beans.FXStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class Degree extends FXModel {
    public static class LIST_CELL extends ListCell<Degree> {
        @Override
        protected void updateItem(Degree degree, boolean empty){
            super.updateItem(degree, empty);

            if (degree == null || empty){
                setText(null);
                setGraphic(null);
                return;
            }

            setGraphic(new Label(degree.getDegree()));
        }
    }

    private final FXIntegerProperty degreeID;
    private final FXStringProperty Degree;

    public Degree(Integer degreeID, String Degree) {
        this.degreeID = new FXIntegerProperty(degreeID);
        this.Degree = new FXStringProperty(Degree);

        track_properties(this.degreeID, this.Degree);
    }

    public FXIntegerProperty degreeIDProperty() {
        return degreeID;
    }

    public Integer getDegreeID() {
        return degreeIDProperty().get();
    }

   public void setdegreeID(Integer degreeID){
    degreeIDProperty().set(degreeID);
   }

   public FXStringProperty DegreeProperty(){
    return Degree;
   }

   public String getDegree(){
    return DegreeProperty().get();
   }

   public void setDegree(String Degree){
    DegreeProperty().set(Degree);
   }
    


    @Override
    public FXModel clone() {
        Degree degree = new Degree(
            getDegreeID(),
            getDegree());
     return degree;
        
    }

    @Override
    public void copy(FXModel arg0) {
        Degree c = (Degree) arg0;

        setdegreeID(c.getDegreeID());
        setDegree(c.getDegree());
       
    }

}
