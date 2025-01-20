// package dev.jun.data;

// import java.sql.SQLException;

// import javax.sql.rowset.CachedRowSet;

// import dev.jun.App;
// import dev.jun.enums.Role;
// import dev.jun.models.tbl.Tblthesisresearchers;
// import dev.sol.core.application.FXModel;
// import dev.sol.db.DBService;

// public class TblthesisresearchersDAO {
//     public static final String TABLE = "Tblthesisresearchers";
//     public final static DBService DB = App.DB_THESIS;


//     public static Tblthesisresearchers data(CachedRowSet crs){
//         try {
//             Object tid = crs.getObject("Tid");
//             Object rid = crs.getObject("Rid");
//             Role role = Role.values()[crs.getObject("Role")];

//             return new Tblthesisresearchers(tid, rid, role);
           
//         } catch (SQLException e) {
//             e.printStackTrace();
           
//         }

//         return null;
//     }

// }
   

