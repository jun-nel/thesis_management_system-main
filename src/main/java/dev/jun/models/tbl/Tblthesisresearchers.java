package dev.jun.models.tbl;

import javax.management.relation.Role;

import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class Tblthesisresearchers extends FXModel {
    //   public static class LIST_CELL extends ListCell<Tblthesisresearchers> {
    //     @Override
    //     protected void updateItem(Tblthesisresearchers tblthesisresearchers, boolean empty){
    //         super.updateItem(tblthesisresearchers, empty);

    //         if (tblthesisresearchers == null || empty){
    //             setText(null);
    //             setGraphic(null);
    //             return;
    //         }

    //         setGraphic(new Label(tblthesisresearchers.getRole()));
           
    //     }
    // }

    private FXObjectProperty<Tblthesis> tid;
    private FXObjectProperty<Tblstudent> rid;
    private FXObjectProperty<Role> role;


    public Tblthesisresearchers(Tblthesis tid, Tblstudent rid, Role role){
        this.tid = new FXObjectProperty<>(tid);
        this.rid = new FXObjectProperty<>(rid);
        this.role = new FXObjectProperty<>(role);

        track_properties(this.tid, this.rid, this.role);
    }
    // tid
    public FXObjectProperty<Tblthesis> tidProperty(){
        return tid;
    }
    public Tblthesis getTid(){
        return tidProperty().get();
    }
    public void setTid(Tblthesis tid){
        tidProperty().set(tid);
    }

    // rid
    public FXObjectProperty<Tblstudent> ridProperty(){
        return rid;
    }
    public Tblstudent getRid(){
        return ridProperty().get();
    }
    public void setRid(Tblstudent rid){
        ridProperty().set(rid);
    }

    //role
    public FXObjectProperty<Role> roleProperty(){
        return role;
    }
    public Role getRole(){
        return roleProperty().get();
    }
    public void setRole(Role role){
        roleProperty().set(role);
    }
 
    @Override
    public FXModel clone() {
       Tblthesisresearchers tblthesisresearchers = new Tblthesisresearchers(getTid(), getRid(), getRole());

       return tblthesisresearchers;
    }

    @Override
    public void copy(FXModel arg0) {
       Tblthesisresearchers c = (Tblthesisresearchers) arg0;

        setTid(c.getTid());
        setRid(c.getRid());
        setRole(c.getRole());
    }

}
