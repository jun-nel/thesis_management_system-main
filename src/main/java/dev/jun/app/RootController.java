package dev.jun.app;

import dev.jun.App;
import dev.jun.data.TblthesisDAO;
import dev.jun.enums.Month;
import dev.jun.models.degree.Degree;
import dev.jun.models.tbl.Tblstudent;
import dev.jun.models.tbl.Tblthesis;
import dev.jun.models.tbl.Tblthesisresearchers;
import dev.sol.core.application.FXController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RootController extends FXController {
     // Thesis Table
    @FXML
    private TableView<Tblthesis> thesisTable;
    @FXML
    private TableColumn<Tblthesis, Integer> thesisIDColumn;
    @FXML
    private TableColumn<Tblthesis, String> thesisTitleColumn;
    @FXML
    private TableColumn<Tblthesis, Integer> thesisYearColumn;
    @FXML
    private TextField searchfield;
    @FXML
    private TableView<Tblthesisresearchers> authorTable;
    @FXML
    private TableColumn<Tblthesisresearchers, Integer> researchIDColumn;
    @FXML
    private TableColumn<Tblthesisresearchers, Tblstudent> researcherNameColumn;
    @FXML
    private TableColumn<Tblthesisresearchers, String> roleColumn;
    @FXML
    private ComboBox<String> researcherBox;
    @FXML
    private ComboBox<String> roleBox;
    @FXML
    private Button deleteAuthorButton;
    @FXML
    private Button updateAuthorButton;
    @FXML
    private TextField idfield;
    @FXML
    private TextArea thesisTitleField;
    @FXML
    private ComboBox<String> degreeField;
    @FXML
    private ComboBox<Month> monthSubmittedField;
    @FXML
    private ComboBox<Integer> yearSubmittedField;

    private Scene scene;
     @FXML
    private void handleDeteThesis() {
        Tblthesis selectedThesis = thesisTable.getSelectionModel().getSelectedItem();
        if (selectedThesis == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thesis Delete Error");
            alert.setHeaderText("Null selection Error!");
            alert.setContentText("No selected Thesis form the table");
            alert.initOwner(scene.getWindow());
            alert.show();
            return;
        }

        thesisMasterList.remove(selectedThesis);
        TblthesisDAO.delete(selectedThesis);
    }

    @FXML
    private void handleAdd() {
        
        Tblthesis selectedThesis = thesisTable.getSelectionModel().getSelectedItem();
        Tblthesis newThesis = new Tblthesis(selectedThesis.getiD() -1, "------> Click Here To Add New Thesis <------",
                2000,
                1, 1);

        selectedThesis. setTitle(thesisTitleField.getText());
        selectedThesis.setDegID(degreeField.getSelectionModel().getSelectedIndex() + 1);
        selectedThesis.setMonth(monthSubmittedField.getSelectionModel().getSelectedIndex() + 1);
        selectedThesis.setYear(years.get(yearSubmittedField.getSelectionModel().getSelectedIndex()));

        thesisMasterList.add(newThesis);
        TblthesisDAO.update(selectedThesis);
        TblthesisDAO.insert(newThesis);

    }
    @FXML
    private void handlEdit() {
       
        Tblthesis selectedThesis = thesisTable.getSelectionModel().getSelectedItem();
        resetValue();
        deleteAuthorButton.setVisible(true);
        updateAuthorButton.setVisible(true);
        degreeList.clear();
        researcherList.clear();
        roleList.clear();
        listContent();

        // Combobox Items
        yearSubmittedField.setItems(years);
        degreeField.setItems(degreeList);
        monthSubmittedField.setItems(months);
        researcherBox.setItems(researcherList);
        researcherBox.setValue(researcherList.get(0));
        roleBox.setItems(roleList);
        roleBox.setValue(roleList.get(0));

        if (thesisTable.getSelectionModel().getSelectedIndex() == thesisMasterList.size() - 1) {
            // default values for click to add
            yearSubmittedField.setValue(years.get(0));
            degreeField.setValue(degreeList.get(0));
            monthSubmittedField.setValue(months.get(0));
        } else {
            // For editing exisiting thesis
            yearSubmittedField.setValue(selectedThesis.getYear());
            degreeField.setValue(degreeList.get(selectedThesis.getDegiD() - 1));
            monthSubmittedField.setValue(months.get(selectedThesis.getMonth() - 1));
        }

    }
    private ObservableList<Tblthesis> thesisMasterList;
    private ObservableList<Tblthesisresearchers> authorMasterList;
    private ObservableList<Degree> degreeMasterList;
    private ObservableList<Tblstudent> studentMasterList;
    private FilteredList<Tblthesisresearchers> authorFilteredList;
    private FilteredList<Tblthesis> thesisFilteredList;
    private ObservableList<String> degreeList;
    private ObservableList<Month> months;
    private ObservableList<Integer> years;
    private ObservableList<String> researcherList;
    private ObservableList<String> roleList;

    @Override
    protected void load_bindings() {
        
        thesisMasterList = App.COLLECTIONS_REGISTER.getList("THESIS");
        authorMasterList = App.COLLECTIONS_REGISTER.getList("RESEARCHER");
        degreeMasterList = App.COLLECTIONS_REGISTER.getList("DEGREE");
        studentMasterList = App.COLLECTIONS_REGISTER.getList("STUDENT");

        authorFilteredList = new FilteredList<>(authorMasterList);
        thesisFilteredList = new FilteredList<>(thesisMasterList);
        degreeList = FXCollections.observableArrayList();
        months = FXCollections.observableArrayList();
        years = FXCollections.observableArrayList();
        researcherList = FXCollections.observableArrayList();
        roleList = FXCollections.observableArrayList();

        scene = (Scene) getParameter("SCENE");

        // Thesis Table
        thesisIDColumn.setCellValueFactory(cell -> cell.getValue().IDProperty().asObject());
        thesisTitleColumn.setCellValueFactory(cell -> cell.getValue().TitleProperty());
        thesisYearColumn.setCellValueFactory(cell -> cell.getValue().YearProperty().asObject());

        thesisTable.setItems(thesisFilteredList);

        // Thesis Selected Item
        thesisTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {

            if (thesisTable.getSelectionModel().getSelectedIndex() == thesisMasterList.size() - 1) {
              //  handlEdit();

            }

            if (nv != null && thesisTable.getSelectionModel().getSelectedIndex() < thesisMasterList.size() - 1) {

                yearSubmittedField.setItems(null);
                monthSubmittedField.setItems(null);
                degreeField.setItems(null);
 
                researchIDColumn
                        .setCellValueFactory(cell -> cell.getValue().getTid().IDProperty().asObject());
                roleColumn.setCellValueFactory(cell -> cell.getValue().roleProperty());
                researcherNameColumn.setCellValueFactory(cell -> cell.getValue().ridProperty());
                authorFilteredList.setPredicate(author -> author.getTid().getId() == nv.getId());
                authorTable.setItems(authorFilteredList);
               
                researcherBox.setValue(null);
                roleBox.setValue(null);

                degreeField.setValue(degreeMasterList.get(nv.getDegiD() - 1).getDegree());
                idfield.setText(String.valueOf(nv.getiD()));
                thesisTitleField.setText(nv.getTitle());
                yearSubmittedField.setValue(nv.getYear());
                monthSubmittedField.setValue(Month.fromMonth(nv.getMonth()));

            } else {

                authorFilteredList.setPredicate(author -> author.getTid().getiD() == 0);
                authorTable.setItems(authorFilteredList);
            }

        });
        
    }

    @Override
    protected void load_fields() {
         researcherNameColumn.setCellFactory(cell -> {
            TableCell<Tblthesisresearchers, Tblstudent> tableCell = new TableCell<>() {
                @Override
                protected void updateItem(Tblstudent item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                        return;
                    }

                    setGraphic(new Label(item.getFullname()));
                }

            };
            return tableCell;
        });

        // Author selected Items
        authorTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {

            if (authorTable.getSelectionModel().isEmpty()) {
                deleteAuthorButton.setVisible(false);
                updateAuthorButton.setVisible(false);
                researcherBox.setValue(null);
                roleBox.setValue(null);
            } else {
                deleteAuthorButton.setVisible(true);
                updateAuthorButton.setVisible(true);
                roleBox.setValue(nv.getRole());
                researcherBox.setValue(nv.getRid().getFullname());

            }

        });

       
    }

    @Override
    protected void load_listeners() {
        deleteAuthorButton.setVisible(false);
        updateAuthorButton.setVisible(false);

        searchfield.textProperty().addListener((o, ov, nv) -> {
            if (nv == null) {
                thesisFilteredList.setPredicate(p -> true);
            } else {
                thesisFilteredList.setPredicate(thesis -> {

                    String filter = searchfield.getText().toUpperCase();

                    if (Integer.toString(thesis.getiD()).contains(filter))
                        return true;

                    if (thesis.getTitle().toUpperCase().contains(filter))
                        return true;

                    return Integer.toString(thesis.getYear()).contains(filter);

                });
            }
        });
      
    }
    public void resetValue() {
        thesisTitleField.setPromptText(null);
        degreeField.setValue(null);
        yearSubmittedField.setValue(null);
        monthSubmittedField.setValue(null);
    
}
public void listContent() {
    // Year
    for (int i = 0; i < 50; i++) {
        years.add(2000 + i);
    }
    // Degree
    for (int i = 0; i < degreeMasterList.size(); i++) {
        degreeList.add(degreeMasterList.get(i).getDegree());
    }
    // Months
    for (int i = 1; i <= 12; i++) {
        months.add(Month.fromMonth(i));
    }

    // Researcher
    for (int i = 0; i <= studentMasterList.size() - 1; i++) {
        researcherList.add(studentMasterList.get(i).getFullname());
    }

    // Role
    for (int i = 0; i <= 1; i++) {
        roleList.add(authorMasterList.get(i).getRole());
    }

}
}
