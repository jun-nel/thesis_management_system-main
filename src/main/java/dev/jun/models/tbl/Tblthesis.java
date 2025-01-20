package dev.jun.models.tbl;

import dev.jun.models.degree.Degree;
import dev.sol.core.application.FXModel;

import dev.sol.core.properties.beans.FXIntegerProperty;
import dev.sol.core.properties.beans.FXObjectProperty;
import dev.sol.core.properties.beans.FXStringProperty;

public class Tblthesis  extends FXModel{

    private final FXIntegerProperty ID;
    private final FXStringProperty Title;
    private final  FXIntegerProperty Year;
    private final FXIntegerProperty Month;
    //private final FXIntegerProperty DegID;
    private FXObjectProperty<Degree>DegID;

    public Tblthesis(int ID,
        String Title,
        int Year,
        int Month,
        Degree DegID){
     this.ID = new FXIntegerProperty(ID);
     this.Title = new FXStringProperty(Title);
     this.Year = new FXIntegerProperty(Year);
     this.Month = new FXIntegerProperty(Month);
     this.DegID = new FXObjectProperty<>(DegID);
     track_properties(this.DegID);
    }
    
    public FXIntegerProperty IDProperty(){
        return ID;
    }

    public Integer getID(){
        return IDProperty().get();
    }

    public void setID(int ID){
        IDProperty().set(ID);
    }
    public FXStringProperty TitleProperty(){
        return Title;
    }

    public String getTitle(){
        return TitleProperty().get();
    }
    public void setTitle(String Title){
        TitleProperty().set(Title);
    }

    public FXIntegerProperty YearProperty(){
        return Year;
    }

    public Integer getYear(){
        return YearProperty().get();
    }

    public void setYear(int Year){
        YearProperty().set(Year);
    }

    public FXIntegerProperty MonthProperty(){
        return Month;
    }

    public Integer getMonth(){
        return MonthProperty().get();
    }

    public void setMonth(int Month){
        MonthProperty().set(Month);
    }

    public FXObjectProperty<Degree>DegIDProperty(){
        return DegID;
    }

    public Degree getDegID(){
        return DegIDProperty().get();
    }

    public void setDegID(Degree DegID){
        DegIDProperty().set(DegID);
    }

    @Override
    public FXModel clone() {
        Tblthesis tblthesis = new Tblthesis(getID(),
            getTitle(),
            getYear(),
            getMonth(),
            getDegID());
        return tblthesis; 
    }

    @Override
    public void copy(FXModel arg0) {
        
       Tblthesis c = (Tblthesis) arg0;

       setID(c.getID());
       setTitle(c.getTitle());
       setYear(c.getYear());
       setMonth(c.getMonth());
       setDegID(c.getDegID());

    }
    
}
