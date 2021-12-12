package refugio.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXLabel;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import refugio.dao.animalDAO;
import refugio.dao.vacunaDAO;
import refugio.model.Animal;
import refugio.model.Vacuna;

/**
 * FXML Controller class
 * @descripcion
 * Se podría hacer un animalController y un vacunaController para que el codigo
 * sea mas entendible.
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
    private SortedList<Animal> sortedData;
    FilteredList<Animal> filterData;
            
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
    @FXML
    private MFXButton btn_dosis;
    
    private String directorioUsuario;
    @FXML
    private MFXTextField nombre_mod;
    @FXML
    private MFXTextField peso_mod;
    @FXML
    private MFXButton btn_mod;
    @FXML
    private TextArea caracteristicas_mod;
    @FXML
    private MFXLabel label_mod;
    @FXML
    private MFXLegacyComboBox<String> sexo_buscar;
    @FXML
    private MFXLegacyComboBox<String> especie_buscar;
    @FXML
    private MFXButton btn_restaurar;
    @FXML
    private MFXButton btn_buscar;
    
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
        
        /**
         * Si un animal va a entrar en la lista de adopcion este sera nulo si
         * su fecha de adopcion es nula
         */
        listaAdopcion.setCellFactory(param -> new ListCell<Animal>() {
            @Override
            protected void updateItem(Animal animal, boolean vacio) {
                super.updateItem(animal, vacio);
                if (vacio || animal == null || animal.getFecha_adp()==null) {
                    setText(null);
                    //listaAdopcion.getItems().remove(animal);
                }else{
                    setText(animal.getNombre() + " " + animal.getRaza() + " " + 
                    animal.getSexo() + " " + animal.getFecha_adp());
                }
            }
        });
        
        
        
        //Busqueda de datos en tabla.
        datosAnimal.setAll(animales);
        
        filterData = new FilteredList<>(datosAnimal,p -> true);
        
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
        
        sortedData = new SortedList<>(filterData);
		
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
        sexo_buscar.getItems().addAll("H","M");
        especie_buscar.getItems().addAll("Gato","Perro");
        
        //directorioUsuario obtiene la ruta del usuario como valor
        directorioUsuario = System.getProperty("user.dir");
        
    }  
    
    //Habilita el boton de adopcion si se ha seleccionado un valor en el comboBox
    @FXML
    private void habilitarAdopcion(ActionEvent event) {
        if (!adpComboBox.getValue().equals("")) {
            btnAdoptar.setDisable(false);
        }
    }
    //Adopta un animal segun su antiguedad y opcionalmente por especie.
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

    //Metodos para cambiar de panel
    @FXML
    private void mostrarListaAdopcion(ActionEvent event) {
        panelAdopcion.toFront();
    }
    @FXML
    private void mostrarVacunacion(ActionEvent event) {
        panelVacunacion.toFront();
    }

    //Metodo que comprueba todos los campos de alta para que no sean nulos y 
    //despues insertar un animal en la Base de Datos.
    @FXML
    private void introducir_animal(ActionEvent event) {
        //Comprobar Campos
        if (!nombre_alta.getText().isEmpty()&&!sexo_alta.getSelectionModel().isEmpty()&&
            !especie_alta.getSelectionModel().isEmpty()&&
            (!gato_raza_alta.getSelectionModel().isEmpty()||
            !perro_raza_alta.getSelectionModel().isEmpty())
            && fecha_nac_alta.getDate() != null
            && !peso_alta.getText().isEmpty()&&peso_alta.getText().matches("^[0-9]+(\\.[0-9]+)?$")) {
            
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
            
            if (fecha_nac_alta.getDate()!=null) {
                if (fecha_nac_alta.getDate().isAfter(fechaActual)) {
                    animal.setFecha_nac(fechaActual.toString());
                }
            }
            
            //Insertamos animal
            int num = animalDAO.insert(animal);
            
            //Si el insert tuvo exito num !=-1
            if (num!=-1) {
                
                animal.setId(num);
                vacunaDAO.suministrarDosisEscencial(animal);
                datosAnimal.add(animal);
                label_alta.setTextFill(Color.GREEN);
                label_alta.setText("Correcto");
            }
        }
        else{
            label_alta.setTextFill(Color.RED);
            label_alta.setText("Error");
        }
    }

    //Comprobar la especie para desactivar o activar su comboBox correspondiente
    //y devolver un booleano para saber la especie.
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

    //Este metodo se activa al seleccionar un animal de la tabla y tiene varias
    //funciones.
    @FXML
    private void obtenerAnimalTabla(MouseEvent event) {
        //La pestaña de modificacion recibe los datos del animal seleccionado
        //para ser modificado.
        nombre_mod.setText(tablaAnimal.getSelectionModel().getSelectedItem().getNombre());
        peso_mod.setText(String.valueOf(tablaAnimal.getSelectionModel().getSelectedItem().getPeso()));
        caracteristicas_mod.setText(tablaAnimal.getSelectionModel().getSelectedItem().getCaracteristicas());
        
        //Hace la imagen visible y comprueba si el animal seleccionado tiene imagen,
        //si no tiene se utiliza una predefinida. El label obtiene el nombre del 
        //animal selecionado.
        animalImagen.setVisible(true);
        animalVacuna.setText(tablaAnimal.getSelectionModel().getSelectedItem().getNombre());
        if (tablaAnimal.getSelectionModel().getSelectedItem().getFoto()!=null) {
            animalImagen.setImage(tablaAnimal.getSelectionModel().getSelectedItem().getFoto());
        }else{
            String directorioImagen = directorioUsuario + "" + "\\src\\refugio\\image\\drag-and-drop-image.png";  
            animalImagen.setImage(new Image(directorioImagen));
        }
        
        //La tabla obtiene las vacunas del animal seleccionado.
        tablaVacuna.getItems().clear();
        Collection<Vacuna> vacunas = vacunaDAO.getAll(
                tablaAnimal.getSelectionModel().getSelectedItem().getId());
        tablaVacuna.getItems().addAll(vacunas);
        datosVacuna.setAll(vacunas);
        tablaVacuna.setItems(datosVacuna);
        
        //Comprobamos la especie para activar su comboBox con las dosis 
        //correspondientes.
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
        
        comprobarDosis();
    }

    //Metodo que activa el boton dosis para insertar la dosis seleccionada.
    @FXML
    private void insertarDosis(ActionEvent event) {
        int i = -1;
        if (!gato_vacuna.getSelectionModel().isEmpty() && gato_vacuna.isVisible()&& 
            tablaAnimal.getSelectionModel().getSelectedItem().getEspecie().equals("Gato")){
                
                i = vacunaDAO.suministrarDosis(
                    tablaAnimal.getSelectionModel().getSelectedItem().getId(),
                    gato_vacuna.getValue());
                if (i!=-1) {
                    Vacuna vacuna = vacunaDAO.getVacuna(gato_vacuna.getValue());
                    datosVacuna.add(vacuna);
                }
            }
        
        else if (!perro_vacuna.getSelectionModel().isEmpty() && perro_vacuna.isVisible()&& 
            tablaAnimal.getSelectionModel().getSelectedItem().getEspecie().equals("Perro")){
                
                i = vacunaDAO.suministrarDosis(
                    tablaAnimal.getSelectionModel().getSelectedItem().getId(),
                    perro_vacuna.getValue());
                if (i!=-1) {
                    Vacuna vacuna = vacunaDAO.getVacuna(perro_vacuna.getValue());
                    datosVacuna.add(vacuna);
                }
            }
        }
    
    //Metodo que se activa al seleccionar un combobox de vacuna.
    @FXML
    private void activarDosis(ActionEvent event) {
        comprobarDosis();
    }
    
    //Comprueba si el combobox corresponde al de la especie seleccionada.
    private void comprobarDosis(){
        if ((!perro_vacuna.getSelectionModel().isEmpty()) && perro_vacuna.isVisible()) {
            btn_dosis.setDisable(false);
        } 
        else if((!gato_vacuna.getSelectionModel().isEmpty()) && gato_vacuna.isVisible()){
            btn_dosis.setDisable(false);
        }
        else{
            btn_dosis.setDisable(true);
        }
    }

    //Drag&Drop para darle una foto a el animal seleccionado.
    @FXML
    private void arrastrarImagen(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        animalImagen.setImage(img);
                    int key = animalDAO.updateImagen(
                    tablaAnimal.getSelectionModel().getSelectedItem().getId()
                    , event.getDragboard().getUrl());
        if (key != -1) {
            tablaAnimal.getSelectionModel().getSelectedItem().setFoto(new Image(
            event.getDragboard().getUrl()));
        }
    }

    @FXML
    private void terminarArrastrarImagen(DragEvent event){
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);

        }
    }

    //Metodo activado por el boton modificar que sirve para modificar el animal
    //seleccionado.
    @FXML
    private void modificarAnimal(ActionEvent event) {
        int i = -1;
        if (!nombre_mod.getText().isEmpty() && !peso_mod.getText().isEmpty()&&
            peso_mod.getText().matches("^[0-9]+(\\.[0-9]+)?$")) {
            i = animalDAO.update(tablaAnimal.getSelectionModel().getSelectedItem().getId(),
                nombre_mod.getText(), peso_mod.getText(), caracteristicas_mod.getText());
            if (i != -1) {
                tablaAnimal.getSelectionModel().getSelectedItem().setNombre(nombre_mod.getText());
                tablaAnimal.getSelectionModel().getSelectedItem().setPeso(Double.parseDouble(peso_mod.getText()));
                tablaAnimal.getSelectionModel().getSelectedItem().setCaracteristicas(caracteristicas_mod.getText());
                tablaAnimal.refresh();
                label_mod.setText("Modificado");
                label_mod.setTextFill(Color.GREEN);
            }
            else{
                label_mod.setText("Error");
                label_mod.setTextFill(Color.RED);
            }
        }
        else{
            label_mod.setText("Error");
            label_mod.setTextFill(Color.RED);
        }
    }

    //boton restaurar para devolver a la tabla todos los animales de la base de 
    //datos.
    @FXML
    private void restaurarTablaAnimal(ActionEvent event) {
        datosAnimal.removeAll();
        Collection<Animal> animales = animalDAO.getAll();
        datosAnimal.addAll(animales);
        datosAnimal.setAll(animales);
        tablaAnimal.setItems(datosAnimal);
        tablaAnimal.setItems(sortedData);
    }

    //boton para buscar en la base de datos segun dos parametros.
    @FXML
    private void busquedaTablaAnimal(ActionEvent event) {
        if (!sexo_buscar.getSelectionModel().isEmpty() &&
            !especie_buscar.getSelectionModel().isEmpty()) {
            
            datosAnimal.removeAll();
            Collection<Animal> animales = animalDAO.getBusqueda(
                    sexo_buscar.getValue()
                    , especie_buscar.getValue());
            datosAnimal.addAll(animales);
            datosAnimal.setAll(animales);
            tablaAnimal.setItems(datosAnimal);
            tablaAnimal.setItems(sortedData);
            
        }
    }

}
