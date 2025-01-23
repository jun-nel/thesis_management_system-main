package dev.jun.app;

import dev.jun.enums.Month;

import javax.management.relation.Role;

import dev.jun.App;
import dev.jun.data.TblthesisDAO;
import dev.jun.models.degree.Degree;
import dev.jun.models.tbl.Tblstudent;
import dev.jun.models.tbl.Tblthesis;
import dev.jun.models.tbl.Tblthesisresearchers;
import dev.sol.core.application.FXController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RootController extends FXController {

    @FXML
    private TextField searchField;
    @FXML
    private Button goButton;
    @FXML
    private TableView<Tblthesis> ThesisTableView;
    @FXML
    private TableColumn<Tblthesis, Integer> IDColumn;
    @FXML
    private TableColumn<Tblthesis, String> TitleColumn;
    @FXML
    private TableColumn<Tblthesis, Integer> YearColumn;
    @FXML
    private TextField IDField;
    @FXML
    private TextArea ThesisTitleArea;
    @FXML
    private ComboBox<Degree> DegreeComboBox;
    @FXML
    private ComboBox<Month> MonthComboBox;
    @FXML
    private TextField YearField;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button SaveButton;
    @FXML
    private TableView<Tblstudent> AuthorTableView;
    @FXML
    private TableColumn<Tblstudent, Integer> RIDColumn;
    @FXML
    private TableColumn<Tblstudent, String> AuthorColumn;
    @FXML
    private TableColumn<Tblthesisresearchers, Role> roleColumn;
    @FXML
    private ComboBox<Tblstudent> AuthorComboBox;
    @FXML
    private ComboBox<Role> roleComboBox;


    private Scene scene;
    private ObservableList<Tblthesis> thesis_marterlist;
    private Tblthesis selectedthesis;
    private ObservableList<Tblstudent> student_masterlist;
    //private ObservableList<Degree> degree_masterlist;

    @Override
    protected void load_fields() {
        scene = (Scene) getParameter("Scene");
        student_masterlist = App.COLLECTIONS_REGISTER.getList("TBLSTUDENT");
        thesis_marterlist = App.COLLECTIONS_REGISTER.getList("TBLTHESIS");
        //degree_masterlist= App.COLLECTIONS_REGISTER.getList("DEEGREE");

        RIDColumn.setCellValueFactory(cell ->cell.getValue().IDProperty().asObject());    
        AuthorColumn.setCellValueFactory(cell -> cell.getValue().SurnameProperty());  
        IDColumn.setCellValueFactory(cell -> cell.getValue().IDProperty().asObject());
        TitleColumn.setCellValueFactory(cell -> cell.getValue().TitleProperty());
        YearColumn.setCellValueFactory(cell -> cell.getValue().YearProperty().asObject());

        DegreeComboBox.setItems(App.COLLECTIONS_REGISTER.getList("DEGREE"));
        DegreeComboBox.setCellFactory(cell -> new Degree.LIST_CELL());
        DegreeComboBox.setButtonCell(new Degree.LIST_CELL()); 

        AuthorComboBox.setItems(App.COLLECTIONS_REGISTER.getList("TBLSTUDENT"));
        AuthorComboBox.setCellFactory(cell -> new Tblstudent.LIST_CELL());
        AuthorComboBox.setButtonCell(new Tblstudent.LIST_CELL());
       
        
        //roleComboBox.setItems(App.COLLECTIONS_REGISTER.getList("TBLTHESISRESEARCHER"));
        
       
        ObservableList<Month> joblist = FXCollections.observableArrayList(Month.values());
        if (thesis_marterlist.stream().anyMatch(e -> e.getMonth().equals(Month.December))) {
            MonthComboBox.setItems(FXCollections.observableArrayList(joblist.subList(0, 0)));
            joblist.size();
        } else
            MonthComboBox.setItems(joblist);
            
            
        AuthorTableView.setItems(student_masterlist);
        ThesisTableView.setItems(thesis_marterlist);

        ThesisTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {

            if (nv != null) {
                IDField.setText(String.valueOf(nv.getID()));
                ThesisTitleArea.setText(nv.getTitle());
                YearField.setText(String.valueOf(nv.getYear()));                

            } else {
                IDField.setText("");
                ThesisTitleArea.setText("");
                YearField.setText("");
            }

            MonthComboBox.setValue(Month.fromMonth(nv.getMonth()));
        });

        // AuthorTableView.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
        //     if (nv != null){
        //         AuthorComboBox.setPromptText(nv.getfullName());

        //     }
        // });

    }

    @Override
    protected void load_bindings() {

    }

    @Override
    protected void load_listeners() {
        reset_combobox();
        ThesisTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> {
            selectedthesis = newValue;
            _bind_labelProperties();

        });

    }

    private void _bind_labelProperties() {
        if (selectedthesis != null) {
         //   DegreeComboBox.valueProperty().bindBidirectional(selectedthesis.DegIDProperty());

        }
    }

    private void reset_combobox() {
       // DegreeComboBox.getSelectionModel().selectFirst();
    }

}
