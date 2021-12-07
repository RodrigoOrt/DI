package refugio.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXLabel;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import refugio.dao.animalDAO;
import refugio.dao.vacunaDAO;
import refugio.model.Animal;
import refugio.model.Vacuna;

/**
 * FXML Controller class
 *
 * @author rodri
 */
public class RefugioFXMLController implements Initializable {

    @FXML
    private TableView<Animal> tablaAnimal;

    private animalDAO animalDAO;
    private vacunaDAO vacunaDAO;
    
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
    
    private ObservableList<Animal> datosAnimal = FXCollections.observableArrayList();
    private ObservableList<Vacuna> datosVacuna = FXCollections.observableArrayList();
    
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
    @FXML
    private MFXLabel label_alta;
    @FXML
    private ImageView animalImagen;
    @FXML
    private MFXLabel animalVacuna;
    @FXML
    private MFXLegacyComboBox<String> perro_vacuna;
    @FXML
    private MFXLegacyComboBox<String> gato_vacuna;
    @FXML
    private TableView<Vacuna> tablaVacuna;
    @FXML
    private TableColumn<Vacuna, String> vacunaNombre;
    @FXML
    private TableColumn<Vacuna, String> vacunaDescripcion;
    @FXML
    private TableColumn<Vacuna, Boolean> vacunaEscencial;
    @FXML
    private TableColumn<Vacuna, Integer> vacunaRevacunacion;
    @FXML
    private TableColumn<Vacuna, String> vacunaFecha;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vacunaDAO = new vacunaDAO();
        
        vacunaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        vacunaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        vacunaEscencial.setCellValueFactory(new PropertyValueFactory<>("escencial"));
        vacunaRevacunacion.setCellValueFactory(new PropertyValueFactory<>("revacunacion"));
        vacunaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha_dosis"));
        
        animalDAO = new animalDAO();
        
        //Rellenar tabla animales
        Collection<Animal> animales = animalDAO.getAll();
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
        datosAnimal.setAll(animales);
        
        FilteredList<Animal> filterData = new FilteredList<>(datosAnimal,p -> true);
        
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
        gato_raza_alta.getItems().addAll(animalDAO.getValorPorEspecie("Gato"));
        perro_raza_alta.getItems().addAll(animalDAO.getValorPorEspecie("Perro"));
        gato_vacuna.getItems().addAll(vacunaDAO.getValorPorEspecie("Gato"));
        perro_vacuna.getItems().addAll(vacunaDAO.getValorPorEspecie("Perro"));
        
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
                        if(animalDAO.adoptar(tablaAnimal.getItems().get(i).getId())==1){
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
                        if(animalDAO.adoptar(tablaAnimal.getItems().get(i).getId())==1){
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
                        if(animalDAO.adoptar(tablaAnimal.getItems().get(i).getId())==1){
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
        //Comprobar Campos
        if (!nombre_alta.getText().isEmpty()&&!sexo_alta.getSelectionModel().isEmpty()&&
            !especie_alta.getSelectionModel().isEmpty()&&
            (!gato_raza_alta.getSelectionModel().isEmpty()||
            !perro_raza_alta.getSelectionModel().isEmpty())
            && !peso_alta.getText().isEmpty()&&peso_alta.getText().matches("[0-9]*")) {
            
            Animal animal = null;
            //Crear Gato o Perro
            if (this.comrpobarEspecie(event)&&animalDAO.getIdRaza(gato_raza_alta.getValue())!=-1) {
                animal = new Animal(0,nombre_alta.getText(),sexo_alta.getValue(),
                fecha_nac_alta.getDate().toString(),color_alta.getValue().toString()
                ,animalDAO.getIdRaza(gato_raza_alta.getValue()),gato_raza_alta.getValue()
                ,especie_alta.getValue(),Double.parseDouble(peso_alta.getText()),
                fechaActual.toString(),"",caracteristicas_alta.getText());
            }
            else if(animalDAO.getIdRaza(perro_raza_alta.getValue())!=-1){
                animal = new Animal(0,nombre_alta.getText(),sexo_alta.getValue(),
                fecha_nac_alta.getDate().toString(),color_alta.getValue().toString()
                ,animalDAO.getIdRaza(perro_raza_alta.getValue()),perro_raza_alta.getValue()
                ,especie_alta.getValue(),Double.parseDouble(peso_alta.getText()),
                fechaActual.toString() ,"",caracteristicas_alta.getText());
            }
            
            //Insertamos animal
            int num = animalDAO.insert(animal);
            
            //Si el insert tuvo exito num !=-1
            if (num!=-1) {
                if (fecha_nac_alta.getDate()!=null) {
                    if (fecha_nac_alta.getDate().isAfter(fechaActual)) {
                        animal.setFecha_nac(fechaActual.toString());
                    }
                }
                animal.setId(num);
                datosAnimal.add(animal);
                label_alta.setTextFill(Color.GREEN);
                label_alta.setText("Correcto");
            }
        }
        else{
        
        }
    }

    @FXML
    private boolean comrpobarEspecie(ActionEvent event) {
        boolean x;
        if (especie_alta.getValue().equals("Gato")) {
            gato_raza_alta.setDisable(false);
            perro_raza_alta.setDisable(true);
            gato_raza_alta.setVisible(true);
            perro_raza_alta.setVisible(false);
            x = true;
        }else{
            gato_raza_alta.setDisable(true);
            perro_raza_alta.setDisable(false);
            gato_raza_alta.setVisible(false);
            perro_raza_alta.setVisible(true);
            x = false;
        }
        return x;
    }

    @FXML
    private void obtenerAnimalTabla(MouseEvent event) {
        animalImagen.setVisible(true);
        animalVacuna.setText(tablaAnimal.getSelectionModel().getSelectedItem().getNombre());
        if (tablaAnimal.getSelectionModel().getSelectedItem().getFoto()!=null) {
            animalImagen.setImage(tablaAnimal.getSelectionModel().getSelectedItem().getFoto());
        }
        
        tablaVacuna.getItems().clear();
        Collection<Vacuna> vacunas = vacunaDAO.getAll(
                tablaAnimal.getSelectionModel().getSelectedItem().getId());
        tablaVacuna.getItems().addAll(vacunas);
        datosVacuna.setAll(vacunas);
        
        if (tablaAnimal.getSelectionModel().getSelectedItem().getEspecie().equals("Gato")) {
            gato_vacuna.setDisable(false);
            perro_vacuna.setDisable(true);
            gato_vacuna.setVisible(true);
            perro_vacuna.setVisible(false);
        
        }else{
            gato_vacuna.setDisable(true);
            perro_vacuna.setDisable(false);
            gato_vacuna.setVisible(false);
            perro_vacuna.setVisible(true);
        }
    }

}
