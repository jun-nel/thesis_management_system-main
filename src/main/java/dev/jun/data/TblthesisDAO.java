package dev.jun.data;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.jun.App;
import dev.jun.models.degree.Degree;
import dev.jun.models.tbl.Tblthesis;
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;
import javafx.collections.ObservableList;

public class TblthesisDAO {
    public static final String TABLE = "tblthesis";
    public final static DBService DB = App.DB_THESIS;
    public static ObservableList<Degree>DEGREE_LIST =App.COLLECTIONS_REGISTER.getList("DEGREE");
    
    

    public static Tblthesis data(CachedRowSet crs) {
        try {
            Integer ID = crs.getInt("ID");
            String Title = crs.getString("Title");
            Integer Year = crs.getInt("Year");
           //Integer Month = crs.getInt("Month");
            Integer Month = crs.getInt("Month");

            Degree DegID  = DEGREE_LIST.stream().filter(o -> {
                try {
                    return o.getDegreeID().equals(crs.getObject("DegID"));
                    
                } catch (SQLException e) {
                  e.printStackTrace();
                  return false;
                }
            }).findFirst().get();
           

            return new Tblthesis(ID,
                    Title,
                    Year,
                    Month,
                    DegID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    private static DBParam[] paramlist(Tblthesis tblthesis) {
        List<DBParam> paramlist = new LinkedList<>();

        paramlist.add(new DBParam(DBType.NUMERIC, "ID", tblthesis.getID()));
        paramlist.add(new DBParam(DBType.TEXT, "Title", tblthesis.getTitle()));
        paramlist.add(new DBParam(DBType.NUMERIC, "Year", tblthesis.getYear()));
        paramlist.add(new DBParam(DBType.NUMERIC, "Month", tblthesis.getMonth()));
        paramlist.add(new DBParam(DBType.NUMERIC, "DegID", tblthesis.getDegID()));

        return paramlist.toArray(new DBParam[0]);
    }

    public static List<Tblthesis> getTblthesislist() {
        CachedRowSet crs = DB.select(TABLE);
        List<Tblthesis> list = new LinkedList<>();
        try {
            while (crs.next()) {
                Tblthesis tblthesis = data(crs);
                if (tblthesis != null) {
                    list.add(tblthesis);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void insert(Tblthesis tblthesis) {
        DB.insert(TABLE, paramlist(tblthesis));
    }

    public static void delete(Tblthesis tblthesis) {
        DB.delete(TABLE, new DBParam(DBType.NUMERIC, "ID", tblthesis.getID()));
    }

    public static void update(Tblthesis tblthesis) {

        DBParam[] params = paramlist(tblthesis);

        for (int i = 0; i <= 17; i++) {
            DB.update(TABLE, new DBParam(DBType.NUMERIC, "ID",
                    tblthesis.getID()), params[i]);
        }

    }
}

