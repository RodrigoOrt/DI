package refugio.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    private TableColumn<Animal, String> animalFecha_nac;
    @FXML
    private TableColumn<Animal, String> animalColor;
    @FXML
    private TableColumn<Animal, Double> animalPeso;
    @FXML
    private TableColumn<Animal, String> animalFecha_alta;
    @FXML
    private TableColumn<Animal, String> animalFecha_adopcion;
    @FXML
    private TableColumn<Animal, String> animalCaracteristicas;
    @FXML
    private TableColumn<Animal, String> animalRaza;
    @FXML
    private TableColumn<Animal, String> animalEspecie;
    
    @FXML
    private MFXTextField filtrarTextField;
    
    private ObservableList<Animal> masterData = FXCollections.observableArrayList();
    
    @FXML
    private MFXLegacyComboBox<String> adpComboBox;
    @FXML
    private MFXButton btnAdoptar;
    @FXML
    private MFXButton btnListaAdopcion;
    @FXML
    private MFXButton btnVacunacion;
    @FXML
    private GridPane panelVacunacion;
    @FXML
    private Pane panelAdopcion;
    @FXML
    private MFXTextField nombre_alta;
    @FXML
    private MFXLegacyComboBox<String> sexo_alta;
    @FXML
    private ColorPicker color_alta;
    @FXML
    private MFXDatePicker fecha_nac_alta;
    @FXML
    private MFXLegacyComboBox<String> especie_alta;

    @FXML
    private MFXTextField peso_alta;
    @FXML
    private TextArea caracteristicas_alta;
    @FXML
    private MFXButton btn_alta;
    @FXML
    private MFXListView<Animal> listaAdopcion;
    
    LocalDate fechaActual = LocalDate.now();
    @FXML
    private MFXLegacyComboBox<String> gato_raza_alta;
    @FXML
    private MFXLegacyComboBox<String> perro_raza_alta;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new DAO();

        //Rellenar tabla animales
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
        
        //Rellenar lista adopcion.
        listaAdopcion.getItems().addAll(animales);
        
        //Impedimos que se pueda realizar ninguna accion en la lista.
        listaAdopcion.setMouseTransparent( true );//La lista y sus items seran transparentes para los mouse events.
        listaAdopcion.setFocusTraversable( false );//Impide usar teclas para movernos por el componente como puede ser TAB.
        
        //Estudiar metodo.
        listaAdopcion.setCellFactory(param -> new ListCell<Animal>() {
            @Override
            protected void updateItem(Animal animal, boolean vacio) {
                super.updateItem(animal, vacio);
                if (vacio || animal == null || animal.getFecha_adp()==null) {
                    setText(null);  
                }else{
                    setText(animal.getNombre() + " " + animal.getColor() + " " + 
                    animal.getRaza() + " " + animal.getSexo() + " " + 
                    animal.getFecha_adp());
                }
            }
        });
        
        
        
        //Busqueda de datos en tabla.
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

        //Rellenar combobox
        adpComboBox.getItems().addAll("Animal","Gato","Perro");
        sexo_alta.getItems().addAll("H","M");
        especie_alta.getItems().addAll("Gato","Perro");
        gato_raza_alta.getItems().addAll(dao.getRaza("Gato"));
        perro_raza_alta.getItems().addAll(dao.getRaza("Perro"));
        
        
    }    
    @FXML
    private void prueba(ActionEvent event) {
        
    }

    @FXML
    private void habilitarAdopcion(ActionEvent event) {
        if (!adpComboBox.getValue().equals("")) {
            btnAdoptar.setDisable(false);
        }
    }

    @FXML
    private void adoptarAnimal(ActionEvent event) {
        
        switch(adpComboBox.getValue()){
            case "Animal":
                for (int i = 0; i < tablaAnimal.getItems().size(); i++) {
                    if (tablaAnimal.getItems().get(i).getFecha_adp()==null) {
                        if(dao.adoptar(tablaAnimal.getItems().get(i).getId())==1){
                            tablaAnimal.getItems().get(i).setFecha_adp(fechaActual.toString());
                            listaAdopcion.getItems().add(tablaAnimal.getItems().get(i));
                            i = tablaAnimal.getItems().size();
                        }
                        
                    }
                }
            break;
            case "Gato":
                for (int i = 0; i < tablaAnimal.getItems().size(); i++) {
                    if (tablaAnimal.getItems().get(i).getFecha_adp()==null && 
                        tablaAnimal.getItems().get(i).getEspecie().equals("Gato")) {
                        if(dao.adoptar(tablaAnimal.getItems().get(i).getId())==1){
                            tablaAnimal.getItems().get(i).setFecha_adp(fechaActual.toString());
                            listaAdopcion.getItems().add(tablaAnimal.getItems().get(i));
                            i = tablaAnimal.getItems().size();
                        }
                        
                    }
                }
            break;
            case "Perro":
                for (int i = 0; i < tablaAnimal.getItems().size(); i++) {
                    if (tablaAnimal.getItems().get(i).getFecha_adp()==null && 
                        tablaAnimal.getItems().get(i).getEspecie().equals("Perro")) {
                        if(dao.adoptar(tablaAnimal.getItems().get(i).getId())==1){
                            tablaAnimal.getItems().get(i).setFecha_adp(fechaActual.toString());
                            listaAdopcion.getItems().add(tablaAnimal.getItems().get(i));
                            i = tablaAnimal.getItems().size();
                        }
                    }
                }
            break;
        }
        
        tablaAnimal.refresh();
    }

    @FXML
    private void mostrarListaAdopcion(ActionEvent event) {
        panelAdopcion.toFront();
    }

    @FXML
    private void mostrarVacunacion(ActionEvent event) {
        panelVacunacion.toFront();
    }

    @FXML
    private void introducir_animal(ActionEvent event) {
        Animal animal = null;
        
        if (this.comrpobarEspecie(event)&&dao.getIdRaza(gato_raza_alta.getValue())!=-1) {
            animal = new Animal(0,nombre_alta.getText(),sexo_alta.getValue(),
            fecha_nac_alta.getDate().toString(),color_alta.getValue().toString()
            ,dao.getIdRaza(gato_raza_alta.getValue()),gato_raza_alta.getValue()
            ,especie_alta.getValue(),Double.parseDouble(peso_alta.getText()),fechaActual.toString()
            ,"",caracteristicas_alta.getText());
        }else if(dao.getIdRaza(perro_raza_alta.getValue())!=-1){
            animal = new Animal(0,nombre_alta.getText(),sexo_alta.getValue(),
            fecha_nac_alta.getDate().toString(),color_alta.getValue().toString()
            ,dao.getIdRaza(perro_raza_alta.getValue()),perro_raza_alta.getValue()
            ,especie_alta.getValue(),Double.parseDouble(peso_alta.getText()),fechaActual.toString()
            ,"",caracteristicas_alta.getText());
        }
        
        int num = dao.insert(animal);
        if (num!=-1) {
            animal.setId(num);
            masterData.add(animal);
        }
    }

    @FXML
    private boolean comrpobarEspecie(ActionEvent event) {
        boolean x;
        if (especie_alta.getValue().equals("Gato")) {
            gato_raza_alta.setDisable(false);
            perro_raza_alta.setDisable(true);
            x = true;
        }else{
            gato_raza_alta.setDisable(true);
            perro_raza_alta.setDisable(false);
            x = false;
        }
        return x;
        
    }

    @FXML
    private void comrprobar_alta(MouseEvent event) {
        
    }
}
