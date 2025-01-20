package dev.jun.data;

import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.jun.App;
import dev.jun.models.tbl.Tblstudent;
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;

public class TblstudentDAO {
 public static final String TABLE = "tblstudents";
 public static final DBService DB = App.DB_THESIS;   

 public static Tblstudent data(CachedRowSet crs){
    try{

        Integer ID = crs.getInt("ID");
        String Surname = crs.getString("Surname");
        String FirstName  = crs.getString("FirstName");
        String MI = crs.getString("MI");

        return new Tblstudent(ID, Surname, Surname, MI);

    } catch (Exception e){
        e.printStackTrace();
    }
    return null;
 }

 private static DBParam[] paramlist(Tblstudent tblstudent){
    List<DBParam> paramlist = new LinkedList<>();

    paramlist.add(new DBParam(DBType.NUMERIC, "ID", tblstudent.getID()));
    paramlist.add(new DBParam(DBType.TEXT, "Surname", tblstudent.getSurname()));
    paramlist.add(new DBParam(DBType.TEXT, "Fname", tblstudent.getFname()));
    paramlist.add(new DBParam(DBType.TEXT, "MI", tblstudent.getMI()));

    return paramlist.toArray(new DBParam[0]);
 }

 public static List<Tblstudent> getTblstudentlist(){
    CachedRowSet crs = DB.select(TABLE);
    List<Tblstudent> list = new LinkedList<>();
    try{
        while (crs.next()){
            Tblstudent tblstudent = data(crs);
            if (tblstudent != null){
                          list.add(tblstudent);
            }
        }

    } catch (Exception e){
        e.printStackTrace();
    }
    return list;
 }

 }

    

