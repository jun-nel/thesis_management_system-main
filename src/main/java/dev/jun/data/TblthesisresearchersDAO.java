package dev.jun.data;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;
import dev.jun.App;
import dev.jun.models.tbl.Tblstudent;
import dev.jun.models.tbl.Tblthesis;
import dev.jun.models.tbl.Tblthesisresearchers;
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;
import javafx.collections.ObservableList;

public class TblthesisresearchersDAO {
    public static final String TABLE = "tblthesisresearchers";
    public final static DBService DB = App.DB_THESIS;
    public final static ObservableList<Tblstudent>tblstudent =App.COLLECTIONS_REGISTER.getList("STUDENT");
    public final static ObservableList<Tblthesis>tblthesis =App.COLLECTIONS_REGISTER.getList("THESIS");

    public static Tblthesisresearchers data(CachedRowSet crs){
        try {
            String Role = crs.getString("Role");
            Tblthesis tid = tblthesis.stream().filter(o -> {
                try {
                    return o.getiD().equals(crs.getObject("TID"));
    
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;         
                }
    
             }).findFirst().get();
    
             Tblstudent rid = tblstudent.stream().filter(o -> {
                try {
                    return o.getID().equals(crs.getObject("RID"));
    
                } catch (SQLException e) {
                   e.printStackTrace();
                   return false;
                }
             }).findFirst().get();
            return new Tblthesisresearchers(tid, rid, Role);
    
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
    private static DBParam[] paramlist(Tblthesisresearchers tblthesisresearchers) {
        List<DBParam> paramlist = new LinkedList<>();

        paramlist.add(new DBParam(DBType.NUMERIC, "TID", tblthesisresearchers.getTid()));
        paramlist.add(new DBParam(DBType.NUMERIC, "RID", tblthesisresearchers.getRid()));
        paramlist.add(new DBParam(DBType.TEXT, "Role", tblthesisresearchers.getRole()));

        return paramlist.toArray(new DBParam[0]);


}

            public static List<Tblthesisresearchers> getTblthesisresearcherslList() {
               CachedRowSet crs = DB.select(TABLE);
                 List<Tblthesisresearchers> list = new LinkedList<>();
           try {
                 while (crs.next()) {
                      Tblthesisresearchers tblthesisresearchers = data(crs);
                   if (tblthesis != null) {
                       list.add(tblthesisresearchers);
            }
        }
              } catch (SQLException e) {
                  e.printStackTrace();
           }
                return list;
}    
}    
        
    



    

   

