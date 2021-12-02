/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package refugio.controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import refugio.dao.DAO;
import refugio.model.Animal;

/**
 * FXML Controller class
 *
 * @author rodri
 */
public class RefugioFXMLController implements Initializable {

    @FXML
    private TableView<Animal> tablaAnimal;

    private DAO dao;
    @FXML
    private TableColumn<Animal, Integer> animalId;
    @FXML
    private TableColumn<Animal, String> animalNombre;
    @FXML
    private TableColumn<Animal, String> animalSexo;
    @FXML
    private TableColumn<Animal, Date> animalFecha_nac;
    @FXML
    private TableColumn<Animal, String> animalColor;
    @FXML
    private TableColumn<Animal, Double> animalPeso;
    @FXML
    private TableColumn<Animal, Date> animalFecha_alta;
    @FXML
    private TableColumn<Animal, Date> animalFecha_adopcion;
    @FXML
    private TableColumn<Animal, String> animalCaracteristicas;
    @FXML
    private TableColumn<Animal, String> animalRaza;
    @FXML
    private TableColumn<Animal, String> animalEspecie;
    
    @FXML
    private MFXTextField filtrarTextField;
    
    private ObservableList<Animal> masterData = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new DAO();
        Collection<Animal> animales = dao.getAll();
        animalId.setCellValueFactory(new PropertyValueFactory<>("id"));
        animalNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        animalSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        animalFecha_nac.setCellValueFactory(new PropertyValueFactory<>("fecha_nac"));
        animalColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        animalRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        animalEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        animalPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        animalFecha_alta.setCellValueFactory(new PropertyValueFactory<>("fecha_alta"));
        animalFecha_adopcion.setCellValueFactory(new PropertyValueFactory<>("fecha_adp"));
        animalCaracteristicas.setCellValueFactory(new PropertyValueFactory<>("caracteristicas"));
        tablaAnimal.getItems().addAll(animales);
        //tablaAnimal.getItems().filtered((Predicate<Animal>) animalFecha_adopcion);
        
        masterData.setAll(animales);
        
        FilteredList<Animal> filterData = new FilteredList<>(masterData,p -> true);
        
        filtrarTextField.textProperty().addListener((masterData,oldValue,newValue)-> {
            filterData.setPredicate(animal -> {
                if (newValue == null || newValue.isEmpty()) {
					return true;
                    }

                String lowerCaseFilter = newValue.toLowerCase();
                if (animal.getEspecie().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                } else if (animal.getRaza().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                } else if (animal.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                }
                return false;
            });
        });
        
        SortedList<Animal> sortedData = new SortedList<>(filterData);
		
		sortedData.comparatorProperty().bind(tablaAnimal.comparatorProperty());
		
		tablaAnimal.setItems(sortedData);
        
    }    
    @FXML
    private void prueba(ActionEvent event) {
        System.out.println(tablaAnimal.getItems().get(0).getNombre());
    }
}
