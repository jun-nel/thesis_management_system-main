package dev.jun;

import dev.jun.app.RootLoader;
import dev.jun.data.DegreeDAO;
import dev.jun.data.TblstudentDAO;
import dev.jun.data.TblthesisDAO;
import dev.sol.core.application.FXApplication;
import dev.sol.core.application.loader.FXLoaderFactory;
import dev.sol.core.registry.FXCollectionsRegister;
import dev.sol.core.registry.FXControllerRegister;
import dev.sol.core.registry.FXNodeRegister;
import dev.sol.core.scene.FXSkin;
import dev.sol.db.DBService;
import javafx.collections.FXCollections;

public class App extends FXApplication {
    public static final FXControllerRegister CONTROLLER_REGISTER = FXControllerRegister.INSTANCE;
    public static final FXCollectionsRegister COLLECTIONS_REGISTER = FXCollectionsRegister.INSTANCE;
    public static final FXNodeRegister NODE_REGISTER = FXNodeRegister.INSTANCE;

    public static final DBService DB_THESIS = DBService.INSTANCE
            .initialize("jdbc:mysql://localhost/db_thesis?user=root&password=");

    @Override
    public void initialize() throws Exception  {
        setTitle("Thesis Main");
        setSkin(FXSkin.CUPERTINO_LIGHT);
       // applicationStage.setResizable(false);

        initialize_dataset();
        initialize_application();

    }

    public void initialize_dataset() {

       COLLECTIONS_REGISTER.register("TBLSTUDENT", FXCollections.observableArrayList(TblstudentDAO.getTblstudentlist()));

        COLLECTIONS_REGISTER.register("DEGREE", FXCollections.observableArrayList(DegreeDAO.getDegreelist()));
        
        COLLECTIONS_REGISTER.register("TBLTHESIS", FXCollections.observableList(TblthesisDAO.getTblthesislist()));
    }

    public void initialize_application() {

        RootLoader rootLoader = (RootLoader) FXLoaderFactory
                .createInstance(RootLoader.class, App.class.getResource("/dev/jun/app/ROOT.fxml"))
                .addParameter("scene", applicationScene)
                .initialize();
        rootLoader.load();
    }

}